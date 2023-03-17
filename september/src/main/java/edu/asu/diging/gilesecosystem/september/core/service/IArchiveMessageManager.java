package edu.asu.diging.gilesecosystem.september.core.service;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IArchiveMessage;

public interface IArchiveMessageManager {
    public abstract List<IArchiveMessage> getAllMessages();
    
    public abstract int getNumberOfPages();

    public abstract List<IArchiveMessage> getMessages(int page);

    public abstract List<IArchiveMessage> getMessages(int offset, String type);

    public abstract int getNumberOfFilteredMessages(String type);

    int getDefaultPageSize();
}
