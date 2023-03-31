package edu.asu.diging.gilesecosystem.september.core.service;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.ISystemMessage;

/**
  The interface for accessing and getting the system messages.
*/
public interface ISystemMessageManager {
    /**
    * Returns a list of all system messages.
    * @return the list of system messages.
    */
    public abstract List<ISystemMessage> getAllMessages();
    
    /**
    * Returns the total number of pages of system messages.
    * @return the number of pages.
    */
    public abstract int getNumberOfPages();

    /**
    Returns a list of system messages for a specific page based on the default page size.
    * @param page the page number.
    * @return the list of system messages for the specified page.
    */
    public abstract List<ISystemMessage> getMessages(int page);
    
    /**
    * Returns a list of system messages for a specific page and message type based on the default page size.
    * @param offset the number of messages to skip before returning the results.
    * @param type the message type to filter by.
    * @return the list of system messages for the specified page and type.
    */
    public abstract List<ISystemMessage> getMessages(int offset, String type);

    /**
    * Returns the total number of filtered system messages based on message type.
    * @param type the message type to filter by.
    * @return the number of filtered system messages.
    */
    public abstract int getNumberOfFilteredMessages(String type);

    /**
    * Returns the default page size for system messages.
    * @return the default page size.
    */
    int getDefaultPageSize();
}
