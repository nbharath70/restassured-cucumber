package RequestPojo.CreateNewRebateOptionPojo;

import java.util.ArrayList;

public class RebateTerms {
    private String billingCycle;
    private ArrayList lineOfBusiness;
    private int benefitRuleId;
    private String benefitRuleName;

    public RebateTerms(){

    }

    public RebateTerms(String billingCycle, ArrayList lineOfBusiness, int benefitRuleId, String benefitRuleName, int psfOptionLevel, int rebateableDrugGroupId, Access accessObject) {
        this.billingCycle = billingCycle;
        this.lineOfBusiness = lineOfBusiness;
        this.benefitRuleId = benefitRuleId;
        this.benefitRuleName = benefitRuleName;
        this.psfOptionLevel = psfOptionLevel;
        this.rebateableDrugGroupId = rebateableDrugGroupId;
        AccessObject = accessObject;
    }

    private int psfOptionLevel;
    private int rebateableDrugGroupId;
    Access AccessObject;


    // Getter Methods

    public String getBillingCycle() {
        return billingCycle;
    }

    public int getBenefitRuleId() {
        return benefitRuleId;
    }

    public String getBenefitRuleName() {
        return benefitRuleName;
    }

    public int getPsfOptionLevel() {
        return psfOptionLevel;
    }

    public int getRebateableDrugGroupId() {
        return rebateableDrugGroupId;
    }

    public Access getAccess() {
        return AccessObject;
    }

    // Setter Methods

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    public void setBenefitRuleId(int benefitRuleId) {
        this.benefitRuleId = benefitRuleId;
    }

    public void setBenefitRuleName(String benefitRuleName) {
        this.benefitRuleName = benefitRuleName;
    }

    public void setPsfOptionLevel(int psfOptionLevel) {
        this.psfOptionLevel = psfOptionLevel;
    }

    public void setRebateableDrugGroupId(int rebateableDrugGroupId) {
        this.rebateableDrugGroupId = rebateableDrugGroupId;
    }

    public void setAccess(Access accessObject) {
        this.AccessObject = accessObject;
    }

    public ArrayList getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        String[] lb = lineOfBusiness.split(",");
        ArrayList<String> arr=new ArrayList<String>();
        for(String newLob:lb)
        {
            arr.add(newLob);
        }
        this.lineOfBusiness = arr;
    }
}
