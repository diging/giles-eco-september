package edu.asu.diging.gilesecosystem.september.web;

import java.util.List;

import edu.asu.diging.gilesecosystem.september.core.model.IArchiveMessage;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;

/**
 * DataTableData class is for setting and retrieving various 
 * attributes required for JS DataTable plug-in which can be used to
 * display the kind of messages and it can be filtered by the message type
 * @author Abhijith Krishnan Radhakrishna Kurup
 */

public class DataTableData {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<IMessage> data;
    private List<IArchiveMessage> archivedData;
    private int pageSize;

    public int getDraw() {
        return draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public List<IMessage> getData() {
        return data;
    }
    
    public List<IArchiveMessage> getArchiveData() {
        return archivedData;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;

    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;

    }

    public void setData(List<IMessage> data) {
        this.data = data;
    }
    
    public void setArchivedData(List<IArchiveMessage> archivedData) {
        this.archivedData = archivedData;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}