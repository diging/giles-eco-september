package edu.asu.diging.gilesecosystem.september.core.service;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.ISystemMessage;

public interface ISystemMessageManager {
    public abstract List<ISystemMessage> getAllMessages();
    
    public abstract int getNumberOfPages();

    public abstract List<ISystemMessage> getMessages(int page);

    public abstract List<ISystemMessage> getMessages(int offset, String type);

    public abstract int getNumberOfFilteredMessages(String type);

    int getDefaultPageSize();
}
