package RequestPojo.CreateNewRebateOptionPojo;

public class Formulary {
    private String position;
    RebateableDrug RebateableDrugObject;

    public Formulary(String position, RebateableDrug rebateableDrugObject) {
        this.position = position;
        RebateableDrugObject = rebateableDrugObject;
    }




    // Getter Methods

    public String getPosition() {
        return position;
    }

    public RebateableDrug getRebateableDrug() {
        return RebateableDrugObject;
    }

    // Setter Methods

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRebateableDrug(RebateableDrug rebateableDrugObject) {
        this.RebateableDrugObject = rebateableDrugObject;
    }
}

