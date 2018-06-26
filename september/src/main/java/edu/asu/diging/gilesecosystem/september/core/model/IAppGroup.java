package edu.asu.diging.gilesecosystem.september.core.model;

import java.util.List;

public interface IAppGroup {

    String getGroupName();

    void setGroupName(String groupName);

    void setInstances(List<IAppInstance> instances);

    List<IAppInstance> getInstances();

}