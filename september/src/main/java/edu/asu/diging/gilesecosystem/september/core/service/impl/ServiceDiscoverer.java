package edu.asu.diging.gilesecosystem.september.core.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import edu.asu.diging.gilesecosystem.september.core.model.AppStatus;
import edu.asu.diging.gilesecosystem.september.core.model.IAppGroup;
import edu.asu.diging.gilesecosystem.september.core.model.IAppInstance;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.AppGroup;
import edu.asu.diging.gilesecosystem.september.core.model.impl.AppInstance;
import edu.asu.diging.gilesecosystem.september.core.service.IServiceDiscoverer;
import edu.asu.diging.gilesecosystem.september.core.util.Properties;
import edu.asu.diging.gilesecosystem.util.properties.IPropertiesManager;

@Service
public class ServiceDiscoverer implements IServiceDiscoverer {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String znode;

    private CuratorFramework curatorFramework;
    
    @Autowired
    private IPropertiesManager propertiesManager;
    
    @PostConstruct
    public void init() {
        curatorFramework = CuratorFrameworkFactory
                .newClient(propertiesManager.getProperty(Properties.ZOOKEEPER_HOST) 
                        + ":" 
                        + propertiesManager.getProperty(Properties.ZOOKEEPER_PORT) , new RetryNTimes(5, 1000));
        curatorFramework.start();
        
        znode = propertiesManager.getProperty(Properties.ZOOKEEPER_SERVICE_ROOT);
        checkServices();
    }
    
    @Override
    public List<IAppGroup> checkServices() {
        List<String> uris;
        try {
            uris = curatorFramework.getChildren().forPath(znode);
        } catch (Exception e) {
            // this is actually throwing Exception
            logger.error("Could not get Nepomuk entries in Zookeeper.", e);
            return null;
        }
        
        List<IAppGroup> groups = new ArrayList<>();
        
        for (String uri : uris) {
            logger.debug("Found ZNode: " + uri);
            
            IAppGroup group = new AppGroup();
            group.setGroupName(uri);
            group.setInstances(new ArrayList<>());
            groups.add(group);
            
            String groupPath = ZKPaths.makePath(znode, uri);
            List<String> instances = null;
            try {
                instances = curatorFramework.getChildren().forPath(groupPath);
            } catch (Exception e1) {
                logger.error("Could not get Nepomuk entries in Zookeeper.", e1);
            }
            
            if (instances != null) {
                for (String instance : instances) {
                    byte[] url = null;
                    try {
                        url = curatorFramework.getData().forPath(ZKPaths.makePath(groupPath, instance));
                    } catch (Exception e) {
                        // method is actually throwing exception
                        logger.error("Could not get Nepomuk URL from Zookeeper.", e);
                    }
                    if (url != null) {
                        logger.info("AppGroup found. Giles found a ZNode " + url);
                        IAppInstance appInstance = new AppInstance();
                        appInstance.setInstanceName(uri);
                        appInstance.setInstanceUrl(new String(url));
                        group.getInstances().add(appInstance);
                        try {
                            if (validateUrl(appInstance.getInstanceUrl())) {
                                appInstance.setStatus(AppStatus.OK);
                            } else {
                                appInstance.setStatus(AppStatus.ERROR);
                            }
                        } catch (IOException e) {
                            logger.error("Could not validate URL.", e);
                        }
                    } else {
                        logger.debug("There was no URL registered with the ZNode " + groupPath);
                    }  
                }
            }           
        }      
        return groups; 
    }
    
    
    private boolean validateUrl(String url) throws IOException {
        URL myURL = new URL(url);
        HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();
        
        try {
            if (myConnection.getResponseCode()==HttpStatus.OK.value()) {
                return true;
            } else {
                return false;
            }
        } finally {
            myConnection.disconnect();
        }
    }
}
