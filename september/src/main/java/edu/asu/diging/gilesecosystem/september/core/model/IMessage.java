package edu.asu.diging.gilesecosystem.september.core.model;

import java.time.ZonedDateTime;

import edu.asu.diging.gilesecosystem.util.store.IStorableObject;

public interface IMessage extends IStorableObject {

    public void setId(String id);

    public abstract MessageType getType();

    public abstract void setType(MessageType type);

    public abstract String getTitle();

    public abstract void setTitle(String title);

    public abstract String getMessage();

    public abstract void setMessage(String message);

    public abstract String getStackTrace();

    public abstract void setStackTrace(String stackTrace);

    public abstract void setExceptionTime(String exceptionTime);

    public abstract String getExceptionTime();

    public abstract void setExceptionDateTime(ZonedDateTime exceptionDateTime);

    public abstract ZonedDateTime getExceptionDateTime();

    public abstract void setExceptionTimePrint(String exceptionTimePrint);

    public abstract String getExceptionTimePrint();

    public abstract void setApplicationId(String applicationId);

    public abstract String getApplicationId();
}
