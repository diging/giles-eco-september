package edu.asu.diging.gilesecosystem.september.core.model.impl;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IDataTableData;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;

public class DataTableData implements IDataTableData {

    int draw;
    int recordsTotal;
    int recordsFiltered;
    List<IMessage> data;

    @Override
    public int getdraw() {
        // TODO Auto-generated method stub
        return draw;
    }

    @Override
    public int getrecordsTotal() {
        // TODO Auto-generated method stub
        return recordsTotal;
    }

    @Override
    public int getrecordsFiltered() {
        // TODO Auto-generated method stub
        return recordsFiltered;
    }

    @Override
    public List<IMessage> getdata() {
        return data;
    }

    @Override
    public void setdraw(int draw) {
        // TODO Auto-generated method stub
        this.draw = draw;

    }

    @Override
    public void setrecordsTotal(int recordsTotal) {
        // TODO Auto-generated method stub
        this.recordsTotal = recordsTotal;

    }

    @Override
    public void setrecordsFiltered(int recordsFiltered) {
        // TODO Auto-generated method stub
        this.recordsFiltered = recordsFiltered;

    }

    @Override
    public void setdata(List<IMessage> data) {
        this.data = data;
    }

}