package RequestPojo.CreateNewRebateOptionPojo;

public class RebateableDrug {
    private String firstTier=null;
    private String condition=null;
    private String secondTier=null;

    public RebateableDrug(String firstTier, String condition, String secondTier) {
        this.firstTier = firstTier;
        this.condition = condition;
        this.secondTier = secondTier;
    }




    // Getter Methods

    public String getFirstTier() {
        return firstTier;
    }

    public String getCondition() {
        return condition;
    }

    public String getSecondTier() {
        return secondTier;
    }

    // Setter Methods

    public void setFirstTier(String firstTier) {
        this.firstTier = firstTier;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setSecondTier(String secondTier) {
        this.secondTier = secondTier;
    }
}

