package edu.asu.diging.gilesecosystem.september.core.model.impl;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import edu.asu.diging.gilesecosystem.september.core.model.IArchiveMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;

public class ArchiveMessage implements IArchiveMessage {
    @Id
    private String id;
    private MessageType type;
    private String title;
    private String message;
    @Column(columnDefinition = "clob")
    @Lob
    private String stackTrace;
    private String exceptionTime;
    private String applicationId;

    @Transient
    private ZonedDateTime exceptionDateTime;
    @Transient
    private String exceptionTimePrint;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getStackTrace() {
        return stackTrace;
    }

    @Override
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String getExceptionTime() {
        return exceptionTime;
    }

    @Override
    public void setExceptionTime(String exceptionTime) {
        this.exceptionTime = exceptionTime;
    }

    @Override
    public String getApplicationId() {
        return applicationId;
    }

    @Override
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public ZonedDateTime getExceptionDateTime() {
        return exceptionDateTime;
    }

    @Override
    public void setExceptionDateTime(ZonedDateTime exceptionDateTime) {
        this.exceptionDateTime = exceptionDateTime;
    }

    @Override
    public String getExceptionTimePrint() {
        return exceptionTimePrint;
    }

    @Override
    public void setExceptionTimePrint(String exceptionTimePrint) {
        this.exceptionTimePrint = exceptionTimePrint;
    }
}
