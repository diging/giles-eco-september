package edu.asu.diging.gilesecosystem.september.core.db;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.ISystemMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.SystemMessage;
import edu.asu.diging.gilesecosystem.util.store.IDatabaseClient;

public interface ISystemMessageDbClient extends IDatabaseClient<ISystemMessage> {

    public abstract List<SystemMessage> getMessages();

    public abstract List<SystemMessage> getMessages(int offset, int pageSize, String sortField);

    public abstract List<SystemMessage> getFilteredMessages(int offset, int pageSize, List<MessageType> filterType,
            String sortField);

    public abstract int getNumberOfMessages();

    public abstract int getNumberOfFilteredMessages(List<MessageType> types);

}