package RequestPojo.DisContractPojo;

public class DiscardContractPojo {
    private String taskId=null;
    private String contractId;
    private int contractDetailRowKey;

    public DiscardContractPojo(String taskId, String contractId, int contractDetailRowKey) {
        this.taskId = taskId;
        this.contractId = contractId;
        this.contractDetailRowKey = contractDetailRowKey;
    }



    // Getter Methods

    public String getTaskId() {
        return taskId;
    }

    public String getContractId() {
        return contractId;
    }

    public int getContractDetailRowKey() {
        return contractDetailRowKey;
    }

    // Setter Methods

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public void setContractDetailRowKey(int contractDetailRowKey) {
        this.contractDetailRowKey = contractDetailRowKey;
    }
}