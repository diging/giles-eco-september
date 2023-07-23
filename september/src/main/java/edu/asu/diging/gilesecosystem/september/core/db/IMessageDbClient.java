package edu.asu.diging.gilesecosystem.september.core.db;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;
import edu.asu.diging.gilesecosystem.util.store.IDatabaseClient;

public interface IMessageDbClient extends IDatabaseClient<IMessage> {

    public abstract List<Message> getMessages();

    public abstract List<Message> getMessages(int offset, int pageSize, String sortField);

    public abstract List<Message> getFilteredMessages(int offset, int pageSize, List<MessageType> filterType,
            String sortField);

    public abstract int getNumberOfMessages();

    public abstract int getNumberOfFilteredMessages(List<MessageType> types);
}
