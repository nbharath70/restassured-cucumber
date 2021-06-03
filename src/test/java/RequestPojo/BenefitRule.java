package RequestPojo;
import java.util.ArrayList;
import java.util.List;
public class BenefitRule
{   private String contractId;
    private List benefitRules;
    public BenefitRule(String contractId, List benefitRules) {
        this.contractId = contractId;
        this.benefitRules = benefitRules;
    }
    public String getContractId() {
        return contractId;
    }
    public List getBenefitRules() {
        return benefitRules;
    }
    public static class BenefitRules1{
        private String benefitRuleName;
        private String formularyType;
        private int aboveValue;
        private int belowValue;
        private String aboveValueString;
        private String belowValueString;

        public void setBelowValue(int belowValue) {
            this.belowValue = belowValue;
        }
        public void setAboveValue(int aboveValue) {
            this.aboveValue = aboveValue;
        }
        public void setBelowValueString(String belowValueString) {
            this.belowValueString = belowValueString;
        }
        public void setAboveValueString(String aboveValueString) {
            this.aboveValueString = aboveValueString;
        }

        public void setFormularyType(String formularyType) {
            this.formularyType = formularyType;
        }
        public void setBenefitRuleName(String benefitRuleName) {
            this.benefitRuleName = benefitRuleName;
        }
        public Object getAboveValue() {

            if(aboveValue!=0)
            {
                return aboveValue;
            }
//            else{
//                return aboveValueString;
//            }
            return null;

        }
        public Object getBelowValue() {
            if(belowValue!=0)
            {

                return belowValue;
            }
//            else{

//                return belowValueString;
//            }
            return null;
        }
        public String getFormularyType() {
            return formularyType;
        }
        public String getBenefitRuleName() {
            return benefitRuleName;
        }

    }
}