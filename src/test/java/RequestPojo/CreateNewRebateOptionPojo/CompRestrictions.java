package RequestPojo.CreateNewRebateOptionPojo;

public class CompRestrictions {
    private String firstRule=null;
    private String firstCondition=null;
    private String secondRule=null;
    private String secondCondition=null;
    private String thirdRule=null;
    private String thirdCondition=null;
    private String fourthRule=null;
    private String fourthCondition=null;
    private String fifthRule=null;


    public CompRestrictions(String firstRule, String firstCondition, String secondRule, String secondCondition, String thirdRule, String thirdCondition, String fourthRule, String fourthCondition, String fifthRule) {
        this.firstRule = firstRule;
        this.firstCondition = firstCondition;
        this.secondRule = secondRule;
        this.secondCondition = secondCondition;
        this.thirdRule = thirdRule;
        this.thirdCondition = thirdCondition;
        this.fourthRule = fourthRule;
        this.fourthCondition = fourthCondition;
        this.fifthRule = fifthRule;
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

    public String getThirdCondition() {
        return thirdCondition;
    }

    public String getFourthRule() {
        return fourthRule;
    }

    public String getFourthCondition() {
        return fourthCondition;
    }

    public String getFifthRule() {
        return fifthRule;
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

    public void setThirdCondition(String thirdCondition) {
        this.thirdCondition = thirdCondition;
    }

    public void setFourthRule(String fourthRule) {
        this.fourthRule = fourthRule;
    }

    public void setFourthCondition(String fourthCondition) {
        this.fourthCondition = fourthCondition;
    }

    public void setFifthRule(String fifthRule) {
        this.fifthRule = fifthRule;
    }
}

