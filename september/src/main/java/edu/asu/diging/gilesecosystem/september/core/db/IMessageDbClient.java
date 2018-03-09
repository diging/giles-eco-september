package edu.asu.diging.gilesecosystem.september.core.db;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;
import edu.asu.diging.gilesecosystem.util.store.IDatabaseClient;

public interface IMessageDbClient extends IDatabaseClient<IMessage> {

    public abstract List<Message> getMessages();

    public abstract List<Message> getMessages(int offset, int pageSize, String sortField);
    
    public abstract List<Message> getFilteredMessages(int offset, int pageSize, String regex,String sortField);

    /**
     * This method returns how many messages there are total stored in the database.
     * 
     * @return
     *      The total number of messages stored in the DB
     */
    public abstract int getNumberOfMessages();
    
    // This method returns the number of messages after applying the filter selected by the user.
	public abstract int getNumberOfFilteredMessages(String regex);

}