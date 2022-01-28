package RequestPojo.TermDrugsFromDrugGroup;


import java.util.ArrayList;
import java.util.List;

public class TermDrugsFromDrugGroupPojoClass {
    private int drugGroupRowKey;
    private String drugListId;
    public List<NdcDetail> ndcDetails=new ArrayList<NdcDetail>();
    private String endDate;
    private String instanceKey;

    public String getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(String instanceKey) {
        this.instanceKey = instanceKey;
    }





//    public TermDrugsFromDrugGroupPojoClass(String drugRowKey, String drugListId, String endDate, List<NdcDetail> ndcDetails){
//    this.drugRowKey=drugRowKey;
//    this.drugListId=drugListId;
//    this.endDate=endDate;
//    this.ndcDetails=ndcDetails;
//    }


    public int getDrugGroupRowKey() {
        return drugGroupRowKey;
    }

    public void setDrugGroupRowKey(int drugRowKey) {
        this.drugGroupRowKey = drugRowKey;
    }

    public String getDrugListId() {
        return drugListId;
    }

    public void setDrugListId(String drugListId) {
        this.drugListId = drugListId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<NdcDetail> getNdcDetails() {
        return ndcDetails;
    }

    public void setNdcDetails(NdcDetail ndcDetails) {
        this.ndcDetails.add(ndcDetails);
    }

}