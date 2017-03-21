package edu.asu.diging.gilesecosystem.september.core.service;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;

public interface IMessageManager {

    public abstract List<IMessage> getAllMessages();

    public abstract List<IMessage> getMessages(int page);

    public abstract int getNumberOfPages();

}