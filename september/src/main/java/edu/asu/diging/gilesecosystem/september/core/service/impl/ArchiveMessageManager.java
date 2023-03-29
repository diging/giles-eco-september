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

import edu.asu.diging.gilesecosystem.september.core.db.IArchiveMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.IArchiveMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.ArchiveMessage;
import edu.asu.diging.gilesecosystem.september.core.service.IArchiveMessageManager;

@PropertySource("classpath:/config.properties")
@Transactional("txmanager_data")
@Service
public class ArchiveMessageManager implements IArchiveMessageManager {
    
    private final String SORT_FIELD_EXCEPTION_TIME = "exceptionTime";

    @Value("${db_page_size}")
    private int pageSize;
    
    @Autowired
    private IArchiveMessageDbClient dbClient;

    @Override
    public List<IArchiveMessage> getAllMessages() {
        List<ArchiveMessage> results = dbClient.getMessages(0, 100, SORT_FIELD_EXCEPTION_TIME);
        return convertToIArchiveMessages(results);
    }

    @Override
    public int getNumberOfPages() {
        int totalNr = dbClient.getNumberOfMessages();
        return (int) Math.ceil(totalNr/pageSize);
    }

    @Override
    public List<IArchiveMessage> getMessages(int page) {
        System.out.println(page * pageSize);
        List<ArchiveMessage> results = dbClient.getMessages(page * pageSize, pageSize,
                SORT_FIELD_EXCEPTION_TIME);
        System.out.println(results);
        return convertToIArchiveMessages(results);
    }

    @Override
    public List<IArchiveMessage> getMessages(int offset, String type) {
        List<MessageType> filterType = filterStringToList(type);
        List<ArchiveMessage> results = dbClient.getFilteredMessages(offset * pageSize, pageSize, filterType, SORT_FIELD_EXCEPTION_TIME);
        return convertToIArchiveMessages(results);
    }

    @Override
    public int getNumberOfFilteredMessages(String type) {
        return dbClient.getNumberOfFilteredMessages(filterStringToList(type));
    }

    @Override
    public int getDefaultPageSize() {
        return pageSize;
    }
    
    private List<IArchiveMessage> convertToIArchiveMessages(List<ArchiveMessage> results) {
        List<IArchiveMessage> messages = new ArrayList<IArchiveMessage>();
        results.forEach(new Consumer<IArchiveMessage>() {
            @Override
            public void accept(IArchiveMessage m) {
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("E yyyy MM dd, HH:mm:ss");
                m.setExceptionDateTime(ZonedDateTime.parse(m.getExceptionTime()));
                m.setExceptionTimePrint(formatter.format(m.getExceptionDateTime()));
                messages.add(m);
                
            }
        });
        return messages;
    }
    
    private List<MessageType> filterStringToList(String filterTypes) {
        return Arrays.asList(filterTypes.split("\\|")).stream()
                .map(m -> MessageType.valueOf(m)).collect(Collectors.toList());

    }
}
