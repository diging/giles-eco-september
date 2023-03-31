package edu.asu.diging.gilesecosystem.september.core.db;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.ISystemMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.SystemMessage;
import edu.asu.diging.gilesecosystem.util.store.IDatabaseClient;

/**
 The Database client interface to access system messages.
 */
public interface ISystemMessageDbClient extends IDatabaseClient<ISystemMessage> {
    /**
     * Get all system messages.
     * @return the list of system messages.
    */
    public abstract List<SystemMessage> getMessages();
    
    /**
     * Get system messages for a given page given the page size, page and sort parameter.
     * @param offset
     *     the offset from which we need the system messages
     * @param pageSize
     *     the page' size
     * @param sortField
     *     the field by which we want the messages to be sorted
     * @return the list of system messages.
    */

    public abstract List<SystemMessage> getMessages(int offset, int pageSize, String sortField);

    /**
     * Get system messages for a given page given the page, page size, filter and sort parameter.
     * @param offset
     *     the offset from which we need the system messages
     * @param pageSize
     *     the page' size
     * @param filterType
     *     the filter type to be applied to the system messages
     * @param sortField
     *     the field by which we want the messages to be sorted
     * @return the list of system messages.
    */
    public abstract List<SystemMessage> getFilteredMessages(int offset, int pageSize, List<MessageType> filterType,
            String sortField);
    
    
    /**
     * Get number of system messages.
     * @return the number of system messages.
    */
    public abstract int getNumberOfMessages();
    
    /**
     * Get number of filtered system messages for the given types.
     * @param types
     *     the different types of messages
     * @return the number of filtered system messages.
    */
    public abstract int getNumberOfFilteredMessages(List<MessageType> types);

}