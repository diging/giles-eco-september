package edu.asu.diging.gilesecosystem.september.core.model.impl;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IAppGroup;
import edu.asu.diging.gilesecosystem.september.core.model.IAppInstance;

public class AppGroup implements IAppGroup {

    private String groupName;
    private List<IAppInstance> instances;

    /* (non-Javadoc)
     * @see edu.asu.diging.gilesecosystem.september.core.model.impl.IAppGroup#getGroupName()
     */
    @Override
    public String getGroupName() {
        return groupName;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.gilesecosystem.september.core.model.impl.IAppGroup#setGroupName(java.lang.String)
     */
    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public List<IAppInstance> getInstances() {
        return instances;
    }

    @Override
    public void setInstances(List<IAppInstance> instances) {
        this.instances = instances;
    }
    
}
