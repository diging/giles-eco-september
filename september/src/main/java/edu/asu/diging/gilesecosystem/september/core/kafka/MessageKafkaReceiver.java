package edu.asu.diging.gilesecosystem.september.core.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.asu.diging.gilesecosystem.requests.ISystemMessageRequest;
import edu.asu.diging.gilesecosystem.requests.impl.SystemMessageRequest;
import edu.asu.diging.gilesecosystem.september.core.service.IRequestProcessor;
import edu.asu.diging.gilesecosystem.util.properties.IPropertiesManager;

@PropertySource("classpath:/config.properties")
public class MessageKafkaReceiver {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IPropertiesManager propertiesManager;

    @Autowired
    private IRequestProcessor requestProcessor;

    @KafkaListener(topics = "${topic_system_message}")
    public void receiveMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        ISystemMessageRequest request = null;
        try {
            request = mapper.readValue(message, SystemMessageRequest.class);
        } catch (IOException e) {
            logger.error("Could not unmarshall request.", e);
            // FIXME: handel this case
            return;
        }

        requestProcessor.processRequest(request);
    }
}
