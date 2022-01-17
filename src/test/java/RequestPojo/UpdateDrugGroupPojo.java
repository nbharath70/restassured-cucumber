package RequestPojo;

public class UpdateDrugGroupPojo {
    private int drugGroupRowKey;
    private String mfrDrugListId;
    private String mfrId;
    private String drugGroupName;
    private String drugGroupType;
    private String opsAssignee;
    private String opsQc;
    private String instanceKey;

    public UpdateDrugGroupPojo(int drugGroupRowKey, String mfrDrugListId, String mfrId, String drugGroupName, String drugGroupType,String instanceKey,String opsAssignee,String opsQc) {
        this.drugGroupRowKey = drugGroupRowKey;
        this.mfrDrugListId = mfrDrugListId;
        this.mfrId = mfrId;
        this.drugGroupName = drugGroupName;
        this.drugGroupType = drugGroupType;
        this.instanceKey=instanceKey;
        this.opsAssignee=opsAssignee;
        this.opsQc=opsQc;
    }
// Getter Methods

    public int getdrugGroupRowKey() {
        return drugGroupRowKey;
    }

    public String getMfrDrugListId() {
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
    public void setdrugGroupRowKey(int drugListRowKey) {
        this.drugGroupRowKey = drugListRowKey;
    }

    public void setMfrDrugListId(String mfrDrugListId) {
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
    public String getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(String instanceKey) {
        this.instanceKey = instanceKey;
    }

    public String getOpsAssignee() {
        return opsAssignee;
    }

    public void setOpsAssignee(String opsAssignee) {
        this.opsAssignee = opsAssignee;
    }

    public String getOpsQc() {
        return opsQc;
    }

    public void setOpsQc(String opsQc) {
        this.opsQc = opsQc;
    }

}
