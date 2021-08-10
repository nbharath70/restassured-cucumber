package RequestPojo.SaveDrugGroupDetailPojo;

import HelperClass.DataBaseHelper;

import java.util.ArrayList;

public class SaveDrugGroupDetailPojoClass{
    private int drugListId;
    private ArrayList ndcs;
    private String startDate;
    private String endDate;

    public SaveDrugGroupDetailPojoClass(int drugListId, ArrayList ndcs,String startDate, String endDate) {
        this.drugListId = drugListId;
        this.ndcs = ndcs;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SaveDrugGroupDetailPojoClass()
    {

    }

    // Getter Methods
    public int getDrugListId() {
        return drugListId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // Setter Methods

    public void setDrugListId(int drugListId) {
        this.drugListId = drugListId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList getNdcs() {
        return ndcs;
    }

    public void setNdcs(String query,String columName) {
        DataBaseHelper dataBaseHelper=new DataBaseHelper();
        ArrayList<String> arrNdcs = dataBaseHelper.getDataColumnArrayListValueDB(query, columName);
        this.ndcs = arrNdcs;
    }
}
