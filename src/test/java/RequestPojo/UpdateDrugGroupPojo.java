package RequestPojo;

public class UpdateDrugGroupPojo {
    private int mfrDrugListId;
    private String mfrId;
    private String drugGroupName;
    private String drugGroupType;

    public UpdateDrugGroupPojo(int mfrDrugListId, String mfrId, String drugGroupName, String drugGroupType) {
        this.mfrDrugListId = mfrDrugListId;
        this.mfrId = mfrId;
        this.drugGroupName = drugGroupName;
        this.drugGroupType = drugGroupType;
    }
// Getter Methods

    public int getMfrDrugListId() {
        return mfrDrugListId;
    }

    public String getMfrId() {
        return mfrId;
    }

    public String getDrugGroupName() {
        return drugGroupName;
    }

    public String getDrugGroupType() {
        return drugGroupType;
    }

    // Setter Methods 

    public void setMfrDrugListId(int mfrDrugListId) {
        this.mfrDrugListId = mfrDrugListId;
    }

    public void setMfrId(String mfrId) {
        this.mfrId = mfrId;
    }

    public void setDrugGroupName(String drugGroupName) {
        this.drugGroupName = drugGroupName;
    }

    public void setDrugGroupType(String drugGroupType) {
        this.drugGroupType = drugGroupType;
    }
}
