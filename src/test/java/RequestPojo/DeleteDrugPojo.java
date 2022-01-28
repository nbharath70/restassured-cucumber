package RequestPojo;

import java.util.List;

public class DeleteDrugPojo {

    private int drugGroupRowKey;
    private String instanceKey;
    private List<Integer> drugListDetailRowKeys=null;

    public List<Integer> getDrugListDetailRowKeys() {
        return drugListDetailRowKeys;
    }

    public void setDrugListDetailRowKeys(List<Integer> detailRowKeys) {
        this.drugListDetailRowKeys = detailRowKeys;
    }

    public int getDrugGroupRowKey() {
        return drugGroupRowKey;
    }

    public void setDrugGroupRowKey(int drugGroupRowKey) {
        this.drugGroupRowKey = drugGroupRowKey;
    }
    public String getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(String instanceKey) {
        this.instanceKey = instanceKey;
    }


}
