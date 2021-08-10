package RequestPojo.CreateNewRebateOptionPojo;

public class UmRule {
    private String firstRule;
    private String firstCondition;
    private String secondRule;
    private String secondCondition=null;
    private String thirdRule=null;

    public UmRule(String firstRule, String firstCondition, String secondRule, String secondCondition, String thirdRule) {
        this.firstRule = firstRule;
        this.firstCondition = firstCondition;
        this.secondRule = secondRule;
        this.secondCondition = secondCondition;
        this.thirdRule = thirdRule;
    }




    // Getter Methods

    public String getFirstRule() {
        return firstRule;
    }

    public String getFirstCondition() {
        return firstCondition;
    }

    public String getSecondRule() {
        return secondRule;
    }

    public String getSecondCondition() {
        return secondCondition;
    }

    public String getThirdRule() {
        return thirdRule;
    }

    // Setter Methods

    public void setFirstRule(String firstRule) {
        this.firstRule = firstRule;
    }

    public void setFirstCondition(String firstCondition) {
        this.firstCondition = firstCondition;
    }

    public void setSecondRule(String secondRule) {
        this.secondRule = secondRule;
    }

    public void setSecondCondition(String secondCondition) {
        this.secondCondition = secondCondition;
    }

    public void setThirdRule(String thirdRule) {
        this.thirdRule = thirdRule;
    }
}
