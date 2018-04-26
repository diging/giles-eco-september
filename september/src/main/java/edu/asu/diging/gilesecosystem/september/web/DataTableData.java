package edu.asu.diging.gilesecosystem.september.web;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;

/**
 * DataTableData class is for setting and retrieving various 
 * attributes required for DataTable plug-in which can be used to
 * display the kind of messages and it can be filtered by the message type
 * @author Abhijith Krishnan Radhakrishna Kurup
 */

public class DataTableData {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<IMessage> data;

    public int getDraw() {
        // TODO Auto-generated method stub
        return draw;
    }

    public int getRecordsTotal() {
        // TODO Auto-generated method stub
        return recordsTotal;
    }

    public int getRecordsFiltered() {
        // TODO Auto-generated method stub
        return recordsFiltered;
    }

    public List<IMessage> getData() {
        return data;
    }

    public void setDraw(int draw) {
        // TODO Auto-generated method stub
        this.draw = draw;

    }

    public void setRecordsTotal(int recordsTotal) {
        // TODO Auto-generated method stub
        this.recordsTotal = recordsTotal;

    }

    public void setRecordsFiltered(int recordsFiltered) {
        // TODO Auto-generated method stub
        this.recordsFiltered = recordsFiltered;

    }

    public void setData(List<IMessage> data) {
        this.data = data;
    }

}