
package RequestPojo.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lineOfBusiness",
        "manufacturerName",
        "manufacturerId",
        "commentCount",
        "qcAssignee",
        "opsAssignee",
        "taskArea",
        "contractId",
        "actionRequired",
        "contractName",
        "assignee",
        "taskId",
        "contractDetailRowKey"
})
@Generated("jsonschema2pojo")
public class TaskResponsePojo {

    @JsonProperty("lineOfBusiness")
    private List<String> lineOfBusiness = null;
    @JsonProperty("manufacturerName")
    private String manufacturerName;
    @JsonProperty("manufacturerId")
    private String manufacturerId;
    @JsonProperty("commentCount")
    private Integer commentCount;
    @JsonProperty("qcAssignee")
    private Object qcAssignee;
    @JsonProperty("opsAssignee")
    private Object opsAssignee;
    @JsonProperty("taskArea")
    private String taskArea;
    @JsonProperty("contractId")
    private String contractId;
    @JsonProperty("actionRequired")
    private String actionRequired;
    @JsonProperty("contractName")
    private String contractName;
    @JsonProperty("assignee")
    private Object assignee;
    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("contractDetailRowKey")
    private Integer contractDetailRowKey;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lineOfBusiness")
    public List<String> getLineOfBusiness() {
        return lineOfBusiness;
    }

    @JsonProperty("lineOfBusiness")
    public void setLineOfBusiness(List<String> lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    @JsonProperty("manufacturerName")
    public String getManufacturerName() {
        return manufacturerName;
    }

    @JsonProperty("manufacturerName")
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @JsonProperty("manufacturerId")
    public String getManufacturerId() {
        return manufacturerId;
    }

    @JsonProperty("manufacturerId")
    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @JsonProperty("commentCount")
    public Integer getCommentCount() {
        return commentCount;
    }

    @JsonProperty("commentCount")
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @JsonProperty("qcAssignee")
    public Object getQcAssignee() {
        return qcAssignee;
    }

    @JsonProperty("qcAssignee")
    public void setQcAssignee(Object qcAssignee) {
        this.qcAssignee = qcAssignee;
    }

    @JsonProperty("opsAssignee")
    public Object getOpsAssignee() {
        return opsAssignee;
    }

    @JsonProperty("opsAssignee")
    public void setOpsAssignee(Object opsAssignee) {
        this.opsAssignee = opsAssignee;
    }

    @JsonProperty("taskArea")
    public String getTaskArea() {
        return taskArea;
    }

    @JsonProperty("taskArea")
    public void setTaskArea(String taskArea) {
        this.taskArea = taskArea;
    }

    @JsonProperty("contractId")
    public String getContractId() {
        return contractId;
    }

    @JsonProperty("contractId")
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @JsonProperty("actionRequired")
    public String getActionRequired() {
        return actionRequired;
    }

    @JsonProperty("actionRequired")
    public void setActionRequired(String actionRequired) {
        this.actionRequired = actionRequired;
    }

    @JsonProperty("contractName")
    public String getContractName() {
        return contractName;
    }

    @JsonProperty("contractName")
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    @JsonProperty("assignee")
    public Object getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("taskId")
    public String getTaskId() {
        return taskId;
    }

    @JsonProperty("taskId")
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @JsonProperty("contractDetailRowKey")
    public Integer getContractDetailRowKey() {
        return contractDetailRowKey;
    }

    @JsonProperty("contractDetailRowKey")
    public void setContractDetailRowKey(Integer contractDetailRowKey) {
        this.contractDetailRowKey = contractDetailRowKey;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
