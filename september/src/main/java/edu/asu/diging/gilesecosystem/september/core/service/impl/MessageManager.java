package edu.asu.diging.gilesecosystem.september.core.service.impl;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.diging.gilesecosystem.september.core.db.IMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;
import edu.asu.diging.gilesecosystem.september.core.service.IMessageManager;

@PropertySource("classpath:/config.properties")
@Transactional
@Service
public class MessageManager implements IMessageManager {

    private final String SORT_FIELD_EXCEPTION_TIME = "exceptionTime";

    @Value("${db_page_size}")
    private int pageSize;

    @Autowired
    private IMessageDbClient dbClient;

    /*
     * (non-Javadoc)
     * 
     * @see edu.asu.diging.gilesecosystem.september.core.service.impl.
     * IMessageManager# getAllMessages()
     */
    @Override
    public List<IMessage> getAllMessages() {
        List<Message> results = dbClient.getMessages(0, 100, SORT_FIELD_EXCEPTION_TIME);
        return convertToIMessages(results);
    }

    /**
     * Method to get a specific page of messages.
     * 
     * @param page
     *            The page to retrieve starting at 0 to retrieve the first page.
     * @return Messages retrieved from the database backend.
     */
    @Override
    public List<IMessage> getMessages(int page) {
        List<Message> results = dbClient.getMessages(page * pageSize, pageSize,
                SORT_FIELD_EXCEPTION_TIME);
        return convertToIMessages(results);
    }

    private List<IMessage> convertToIMessages(List<Message> results) {
        List<IMessage> messages = new ArrayList<IMessage>();

        results.forEach(new Consumer<IMessage>() {
            @Override
            public void accept(IMessage m) {
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("E yyyy MM dd, HH:mm:ss");
                m.setExceptionDateTime(ZonedDateTime.parse(m.getExceptionTime()));
                m.setExceptionTimePrint(formatter.format(m.getExceptionDateTime()));
                messages.add(m);
            }

        });
        return messages;
    }

    @Override
    public int getNumberOfPages() {
        int totalNr = dbClient.getNumberOfMessages();
        return (int) Math.ceil(new Double(totalNr) / new Double(pageSize));
    }

    @Override
    public int getDefaultPageSize() {
        return pageSize;
    }

    @Override
    public List<IMessage> getMessages(int page, String type) {
        List<MessageType> filterType = filterStringToList(type);
        List<Message> results = dbClient.getFilteredMessages(page * pageSize, pageSize,
                filterType, SORT_FIELD_EXCEPTION_TIME);
        return convertToIMessages(results);
    }

    @Override
    public int getNumberOfFilteredMessages(String messageType) {
        return dbClient.getNumberOfFilteredMessages(filterStringToList(messageType));
    }

    private List<MessageType> filterStringToList(String filterTypes) {
        return Arrays.asList(filterTypes.split("\\|")).stream()
                .map(m -> MessageType.valueOf(m)).collect(Collectors.toList());

    }
}
