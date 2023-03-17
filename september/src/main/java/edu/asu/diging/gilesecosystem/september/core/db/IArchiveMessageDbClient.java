package edu.asu.diging.gilesecosystem.september.core.db;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IArchiveMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.ArchiveMessage;
import edu.asu.diging.gilesecosystem.util.store.IDatabaseClient;

public interface IArchiveMessageDbClient extends IDatabaseClient<IArchiveMessage> {
    
    public abstract List<ArchiveMessage> getMessages();

    public abstract List<ArchiveMessage> getMessages(int offset, int pageSize, String sortField);

    public abstract List<ArchiveMessage> getFilteredMessages(int offset, int pageSize, List<MessageType> filterType,
            String sortField);

    public abstract int getNumberOfMessages();

    public abstract int getNumberOfFilteredMessages(List<MessageType> types);
}
