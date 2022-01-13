package RequestPojo.discardDrugGroup;

public class DiscardDrugGroupPojo {
    private String drugGroupRowKey;
    private String instanceKey;
    private String taskId;
    public DiscardDrugGroupPojo(String drugGroupRowKey, String instanceKey,String taskId){
        this.drugGroupRowKey=drugGroupRowKey;
        this.taskId=taskId;
        this.instanceKey=instanceKey;
    }
    public String getDrugGroupRowKey() {
        return drugGroupRowKey;
    }

    public void setDrugGroupRowKey(String drugGroupRowKey) {
        this.drugGroupRowKey = drugGroupRowKey;
    }

    public String getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(String instanceKey) {
        this.instanceKey = instanceKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }



}
