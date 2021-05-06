package RequestPojo;

public class CreateDrugGroupPojo {
    private String mfrId;
    private String drugGroupName;
    private String drugGroupType;

    public CreateDrugGroupPojo(String mfrId, String drugGroupName, String drugGroupType) {
        this.mfrId = mfrId;
        this.drugGroupName = drugGroupName;
        this.drugGroupType = drugGroupType;
    }
// Getter Methods

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
