package RequestPojo;

public class ContractDetail {
    private float rowKey;
    private String contractId;
    private float amendmentNumber;
    private String amendmentName;
    private String lifecycleStatus;
    private String startDate;
    private String endDate;
    private float versionNumber;
    private String recCreatedBy;
    private String recCreatedDate;
    private String recUpdatedBy;
    private String recUpdatedDate;
    ContractDetailJson ContractDetailJsonObject;

    public ContractDetail(float rowKey, String contractId, float amendmentNumber, String amendmentName, String lifecycleStatus, String startDate, String endDate, float versionNumber, String recCreatedBy, String recCreatedDate, String recUpdatedBy, String recUpdatedDate, ContractDetailJson contractDetailJsonObject) {
        this.rowKey = rowKey;
        this.contractId = contractId;
        this.amendmentNumber = amendmentNumber;
        this.amendmentName = amendmentName;
        this.lifecycleStatus = lifecycleStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.versionNumber = versionNumber;
        this.recCreatedBy = recCreatedBy;
        this.recCreatedDate = recCreatedDate;
        this.recUpdatedBy = recUpdatedBy;
        this.recUpdatedDate = recUpdatedDate;
        ContractDetailJsonObject = contractDetailJsonObject;
    }


    // Getter Methods

    public float getRowKey() {
        return rowKey;
    }

    public String getContractId() {
        return contractId;
    }

    public float getAmendmentNumber() {
        return amendmentNumber;
    }

    public String getAmendmentName() {
        return amendmentName;
    }

    public String getLifecycleStatus() {
        return lifecycleStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public float getVersionNumber() {
        return versionNumber;
    }

    public String getRecCreatedBy() {
        return recCreatedBy;
    }

    public String getRecCreatedDate() {
        return recCreatedDate;
    }

    public String getRecUpdatedBy() {
        return recUpdatedBy;
    }

    public String getRecUpdatedDate() {
        return recUpdatedDate;
    }

    public ContractDetailJson getContractDetailJson() {
        return ContractDetailJsonObject;
    }

    // Setter Methods

    public void setRowKey(float rowKey) {
        this.rowKey = rowKey;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public void setAmendmentNumber(float amendmentNumber) {
        this.amendmentNumber = amendmentNumber;
    }

    public void setAmendmentName(String amendmentName) {
        this.amendmentName = amendmentName;
    }

    public void setLifecycleStatus(String lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setVersionNumber(float versionNumber) {
        this.versionNumber = versionNumber;
    }

    public void setRecCreatedBy(String recCreatedBy) {
        this.recCreatedBy = recCreatedBy;
    }

    public void setRecCreatedDate(String recCreatedDate) {
        this.recCreatedDate = recCreatedDate;
    }

    public void setRecUpdatedBy(String recUpdatedBy) {
        this.recUpdatedBy = recUpdatedBy;
    }

    public void setRecUpdatedDate(String recUpdatedDate) {
        this.recUpdatedDate = recUpdatedDate;
    }

    public void setContractDetailJson(ContractDetailJson contractDetailJsonObject) {
        this.ContractDetailJsonObject = contractDetailJsonObject;
    }
}
