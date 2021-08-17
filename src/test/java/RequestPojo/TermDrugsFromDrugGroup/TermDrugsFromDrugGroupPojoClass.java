package RequestPojo.TermDrugsFromDrugGroup;


import java.util.ArrayList;
import java.util.List;

public class TermDrugsFromDrugGroupPojoClass {
    private int drugRowKey;
    private String drugListId;
    public List<NdcDetail> ndcDetails=new ArrayList<NdcDetail>();
    private String endDate;



//    public TermDrugsFromDrugGroupPojoClass(String drugRowKey, String drugListId, String endDate, List<NdcDetail> ndcDetails){
//    this.drugRowKey=drugRowKey;
//    this.drugListId=drugListId;
//    this.endDate=endDate;
//    this.ndcDetails=ndcDetails;
//    }


    public int getDrugRowKey() {
        return drugRowKey;
    }

    public void setDrugRowKey(int drugRowKey) {
        this.drugRowKey = drugRowKey;
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