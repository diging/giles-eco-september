package edu.asu.diging.gilesecosystem.september.core.model;

public interface IAppInstance {

    String getInstanceName();

    void setInstanceName(String instanceName);

    String getInstanceUrl();

    void setInstanceUrl(String instanceUrl);

    void setStatus(AppStatus status);

    AppStatus getStatus();

}