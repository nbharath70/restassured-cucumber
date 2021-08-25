package RequestPojo;

public class UpdateDrugGroupPojo {
    private int drugListRowKey;
    private int mfrDrugListId;
    private String mfrId;
    private String drugGroupName;
    private String drugGroupType;

    public UpdateDrugGroupPojo(int drugListRowKey, int mfrDrugListId, String mfrId, String drugGroupName, String drugGroupType) {
        this.drugListRowKey = drugListRowKey;
        this.mfrDrugListId = mfrDrugListId;
        this.mfrId = mfrId;
        this.drugGroupName = drugGroupName;
        this.drugGroupType = drugGroupType;
    }
// Getter Methods

    public int getdrugListRowKey() {
        return drugListRowKey;
    }

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
    public void setdrugListRowKey(int drugListRowKey) {
        this.drugListRowKey = drugListRowKey;
    }

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
