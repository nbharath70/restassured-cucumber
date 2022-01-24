package RequestPojo.SaveDrugGroupDetailPojo;

import HelperClass.DataBaseHelper;

import java.util.ArrayList;

public class SaveDrugGroupDetailPojoClass{
    private int drugListId;
    private int drugGroupRowKey;
    private ArrayList ndcs;
    private String startDate;
    private String endDate;
    private String instanceKey;

    public SaveDrugGroupDetailPojoClass(int drugListId,int drugGroupRowKey, ArrayList ndcs,String startDate, String endDate,String instanceKey) {
        this.drugListId = drugListId;
        this.drugGroupRowKey = drugGroupRowKey;
        this.ndcs = ndcs;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instanceKey=instanceKey;
    }
    public SaveDrugGroupDetailPojoClass(int drugListId, ArrayList ndcs,String startDate, String endDate,String instanceKey) {
        this.drugListId = drugListId;
        this.ndcs = ndcs;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instanceKey=instanceKey;
    }

    public SaveDrugGroupDetailPojoClass()
    {

    }

    // Getter Methods
    public int getDrugListId() {
        return drugListId;
    }

    public int getDrugGroupRowKey() {
        return drugGroupRowKey;
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

    public void setDrugGroupRowKey(int drugRowKey) {
        this.drugGroupRowKey = drugRowKey;
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

    public String getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(String instancekey) {
        this.instanceKey = instancekey;
    }



    public void setNdcs(String query,String columName) {
        DataBaseHelper dataBaseHelper=new DataBaseHelper();
        ArrayList<String> arrNdcs = dataBaseHelper.getDataColumnArrayListValueDB(query, columName);
        this.ndcs = arrNdcs;
    }

    public void setNdcs(String ndcs) {
        String[] lb = ndcs.split(",");
        ArrayList<String> arr=new ArrayList<String>();
        for(String newNdcs:lb)
        {
            arr.add(newNdcs);
        }
        this.ndcs = arr;
    }
}
