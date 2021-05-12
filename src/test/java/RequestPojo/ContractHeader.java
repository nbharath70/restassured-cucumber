package RequestPojo;

public class ContractHeader {
    private int rowKey;
    private String contractId;
    private String manufacturerId;
    private String contractType;
    private String contractName;
    private String startDate;
    private String endDate;
    private String recCreatedDate;
    private String recCreatedBy;
    private String recUpdatedDate;
    private String recUpdatedBy;
    private String lifecycleStatus;
    private String contractDocReference = null;
    private String notes;

    public ContractHeader(String contractName,String contractType,  String startDate, String endDate,String notes,String lifecycleStatus) {
        this.contractName = contractName;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.lifecycleStatus = lifecycleStatus;
    }

    public ContractHeader(int rowKey, String contractId, String manufacturerId, String contractType, String contractName, String startDate, String endDate, String recCreatedDate, String recCreatedBy, String recUpdatedDate, String recUpdatedBy, String lifecycleStatus, String contractDocReference, String notes) {
        this.rowKey = rowKey;
        this.contractId = contractId;
        this.manufacturerId = manufacturerId;
        this.contractType = contractType;
        this.contractName = contractName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recCreatedDate = recCreatedDate;
        this.recCreatedBy = recCreatedBy;
        this.recUpdatedDate = recUpdatedDate;
        this.recUpdatedBy = recUpdatedBy;
        this.lifecycleStatus = lifecycleStatus;
        this.contractDocReference = contractDocReference;
        this.notes = notes;
    }


    // Getter Methods

    public int getRowKey() {
        return rowKey;
    }

    public String getContractId() {
        return contractId;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public String getContractType() {
        return contractType;
    }

    public String getContractName() {
        return contractName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getRecCreatedDate() {
        return recCreatedDate;
    }

    public String getRecCreatedBy() {
        return recCreatedBy;
    }

    public String getRecUpdatedDate() {
        return recUpdatedDate;
    }

    public String getRecUpdatedBy() {
        return recUpdatedBy;
    }

    public String getLifecycleStatus() {
        return lifecycleStatus;
    }

    public String getContractDocReference() {
        return contractDocReference;
    }

    public String getNotes() {
        return notes;
    }

    // Setter Methods

    public void setRowKey(int rowKey) {
        this.rowKey = rowKey;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRecCreatedDate(String recCreatedDate) {
        this.recCreatedDate = recCreatedDate;
    }

    public void setRecCreatedBy(String recCreatedBy) {
        this.recCreatedBy = recCreatedBy;
    }

    public void setRecUpdatedDate(String recUpdatedDate) {
        this.recUpdatedDate = recUpdatedDate;
    }

    public void setRecUpdatedBy(String recUpdatedBy) {
        this.recUpdatedBy = recUpdatedBy;
    }

    public void setLifecycleStatus(String lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    public void setContractDocReference(String contractDocReference) {
        this.contractDocReference = contractDocReference;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
