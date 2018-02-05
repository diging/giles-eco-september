package edu.asu.diging.gilesecosystem.september.core.model;

import edu.asu.diging.gilesecosystem.util.store.IStorableObject;

public interface IDataTableData extends IStorableObject {
    
	public abstract int getsEcho();
	
	public abstract int getiTotalRecords();

    public abstract int getiDisplayRecords();

    public abstract void setsEcho(int sEcho);

    public abstract void setiTotalRecords(int iTotalRecords);
    
    public abstract void setiDisplayRecords(int iDisplayRecords);

}