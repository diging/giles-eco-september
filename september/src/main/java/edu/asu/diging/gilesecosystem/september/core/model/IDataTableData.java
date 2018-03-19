package edu.asu.diging.gilesecosystem.september.core.model;

import java.util.List;

public interface IDataTableData {

    public abstract int getdraw();

    public abstract int getrecordsTotal();

    public abstract int getrecordsFiltered();

    public abstract List<IMessage> getdata();

    public abstract void setdraw(int draw);

    public abstract void setrecordsTotal(int recordsTotal);

    public abstract void setrecordsFiltered(int recordsFiltered);

    public abstract void setdata(List<IMessage> data);

}