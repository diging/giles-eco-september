package edu.asu.diging.gilesecosystem.september.core.datatable.impl;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.datatable.IDataTableData;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;

/**
 * DataTableData class is for setting and retrieving various 
 * attributes required for Data Table plug-in.
 *
 * @author Abhijith Krishnan Radhakrishna Kurup
 */

public class DataTableData implements IDataTableData {

    int draw;
    int recordsTotal;
    int recordsFiltered;
    List<IMessage> data;

    @Override
    public int getDraw() {
        // TODO Auto-generated method stub
        return draw;
    }

    @Override
    public int getRecordsTotal() {
        // TODO Auto-generated method stub
        return recordsTotal;
    }

    @Override
    public int getRecordsFiltered() {
        // TODO Auto-generated method stub
        return recordsFiltered;
    }

    @Override
    public List<IMessage> getData() {
        return data;
    }

    @Override
    public void setDraw(int draw) {
        // TODO Auto-generated method stub
        this.draw = draw;

    }

    @Override
    public void setRecordsTotal(int recordsTotal) {
        // TODO Auto-generated method stub
        this.recordsTotal = recordsTotal;

    }

    @Override
    public void setRecordsFiltered(int recordsFiltered) {
        // TODO Auto-generated method stub
        this.recordsFiltered = recordsFiltered;

    }

    @Override
    public void setData(List<IMessage> data) {
        this.data = data;
    }

}