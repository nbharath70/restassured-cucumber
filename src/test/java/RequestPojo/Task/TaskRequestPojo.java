package RequestPojo.Task;

import RequestPojo.TermDrugsFromDrugGroup.NdcDetail;

import java.util.ArrayList;
import java.util.List;

public class TaskRequestPojo {


    private String assignee;
    private ArrayList groups;

    public TaskRequestPojo()
    {

    }

    public TaskRequestPojo(String assignee, ArrayList groups) {
        this.assignee = assignee;
        this.groups = groups;
    }

    // Getter Methods

    public String getAssignee() {
        return assignee;
    }

    // Setter Methods

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public ArrayList getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        ArrayList<String> arr=new ArrayList<String>();
            arr.add(groups);
        this.groups = arr;
    }

}
