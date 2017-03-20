package edu.asu.diging.gilesecosystem.september.core.service.impl;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.diging.gilesecosystem.september.core.db.IMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;
import edu.asu.diging.gilesecosystem.september.core.service.IMessageManager;

@Transactional
@Service
public class MessageManager implements IMessageManager {

    @Autowired
    private IMessageDbClient dbClient;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.gilesecosystem.september.core.service.impl.IMessageManager#getAllMessages()
     */
    @Override
    public List<IMessage> getAllMessages() {
        List<Message> results = dbClient.getMessages();
        List<IMessage> messages = new ArrayList<IMessage>();
        results.forEach(new Consumer<IMessage>() {

            @Override
            public void accept(IMessage m) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E yyyy MM dd, HH:mm:ss");
                m.setExceptionDateTime(ZonedDateTime.parse(m.getExceptionTime()));
                m.setExceptionTimePrint(formatter.format(m.getExceptionDateTime()));
                messages.add(m);
            }
            
        });
        return messages;
    }
}
