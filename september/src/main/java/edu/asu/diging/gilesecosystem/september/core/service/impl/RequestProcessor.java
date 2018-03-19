package edu.asu.diging.gilesecosystem.september.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.diging.gilesecosystem.requests.ISystemMessageRequest;
import edu.asu.diging.gilesecosystem.september.core.db.IMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;
import edu.asu.diging.gilesecosystem.september.core.service.IRequestProcessor;
import edu.asu.diging.gilesecosystem.util.exceptions.UnstorableObjectException;

@Transactional
@Service
public class RequestProcessor implements IRequestProcessor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IMessageDbClient dbClient;

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.asu.diging.gilesecosystem.september.core.service.impl.IRequestProcessor#
     * processRequest(edu.asu.diging.gilesecosystem.requests.impl.
     * ISystemMessageRequest)
     */
    @Override
    public void processRequest(ISystemMessageRequest request) {
        IMessage msg = new Message();
        msg.setId(dbClient.generateId());
        msg.setMessage(request.getMessage());
        msg.setTitle(request.getTitle());
        msg.setStackTrace(request.getStackTrace());
        msg.setType(MessageType.getByValue(request.getMessageType()));
        msg.setExceptionTime(request.getMessageTime());
        msg.setApplicationId(request.getApplicationId());

        try {
            dbClient.store(msg);
        } catch (UnstorableObjectException e) {
            // should never happen, we set the id above
            logger.error("Could not store message.", e);
        }
    }
}
