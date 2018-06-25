package edu.asu.diging.gilesecosystem.september.core.model.impl;

import edu.asu.diging.gilesecosystem.september.core.model.AppStatus;
import edu.asu.diging.gilesecosystem.september.core.model.IAppInstance;

public class AppInstance implements IAppInstance {

    private String instanceName;
    private String instanceUrl;
    private AppStatus status;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.gilesecosystem.september.core.model.impl.IAppInstance#getInstanceName()
     */
    @Override
    public String getInstanceName() {
        return instanceName;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.gilesecosystem.september.core.model.impl.IAppInstance#setInstanceName(java.lang.String)
     */
    @Override
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.gilesecosystem.september.core.model.impl.IAppInstance#getInstanceUrl()
     */
    @Override
    public String getInstanceUrl() {
        return instanceUrl;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.gilesecosystem.september.core.model.impl.IAppInstance#setInstanceUrl(java.lang.String)
     */
    @Override
    public void setInstanceUrl(String instanceUrl) {
        this.instanceUrl = instanceUrl;
    }
    @Override
    public AppStatus getStatus() {
        return status;
    }
    @Override
    public void setStatus(AppStatus status) {
        this.status = status;
    }
}
