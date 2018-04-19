package edu.asu.diging.gilesecosystem.september.core.datatable;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;

public interface IDataTableData {

    public abstract int getDraw();

    public abstract int getRecordsTotal();

    public abstract int getRecordsFiltered();

    public abstract List<IMessage> getData();

    public abstract void setDraw(int draw);

    public abstract void setRecordsTotal(int recordsTotal);

    public abstract void setRecordsFiltered(int recordsFiltered);

    public abstract void setData(List<IMessage> data);

}