package RequestPojo.CreateNewRebateOptionPojo;

public class FormularyRequirementJson {
    private int xvalue;
    private String yvalue;
    private String prodOrMfr;
    private boolean paAndSt;
    UmRule UmRuleObject;
    Formulary FormularyObject;
    private String compFormularyPosition=null;
    private String compDrugTier = null;
    CompRestrictions CompRestrictionsObject;

    public FormularyRequirementJson( int xvalue, String yvalue, String prodOrMfr, boolean paAndSt, UmRule umRuleObject, Formulary formularyObject, String compFormularyPosition, String compDrugTier, CompRestrictions compRestrictionsObject) {
        this.xvalue = xvalue;
        this.yvalue = yvalue;
        this.prodOrMfr = prodOrMfr;
        this.paAndSt = paAndSt;
        UmRuleObject = umRuleObject;
        FormularyObject = formularyObject;
        this.compFormularyPosition = compFormularyPosition;
        this.compDrugTier = compDrugTier;
        CompRestrictionsObject = compRestrictionsObject;
    }




    // Getter Methods



    public int getXvalue() {
        return xvalue;
    }

    public String getYvalue() {
        return yvalue;
    }

    public String getProdOrMfr() {
        return prodOrMfr;
    }

    public boolean getPaAndSt() {
        return paAndSt;
    }

    public UmRule getUmRule() {
        return UmRuleObject;
    }

    public Formulary getFormulary() {
        return FormularyObject;
    }

    public String getCompFormularyPosition() {
        return compFormularyPosition;
    }

    public String getCompDrugTier() {
        return compDrugTier;
    }

    public CompRestrictions getCompRestrictions() {
        return CompRestrictionsObject;
    }

    // Setter Methods


    public void setXvalue(int xvalue) {
        this.xvalue = xvalue;
    }

    public void setYvalue(String yvalue) {
        this.yvalue = yvalue;
    }

    public void setProdOrMfr(String prodOrMfr) {
        this.prodOrMfr = prodOrMfr;
    }

    public void setPaAndSt(boolean paAndSt) {
        this.paAndSt = paAndSt;
    }

    public void setUmRule(UmRule umRuleObject) {
        this.UmRuleObject = umRuleObject;
    }

    public void setFormulary(Formulary formularyObject) {
        this.FormularyObject = formularyObject;
    }

    public void setCompFormularyPosition(String compFormularyPosition) {
        this.compFormularyPosition = compFormularyPosition;
    }

    public void setCompDrugTier(String compDrugTier) {
        this.compDrugTier = compDrugTier;
    }

    public void setCompRestrictions(CompRestrictions compRestrictionsObject) {
        this.CompRestrictionsObject = compRestrictionsObject;
    }
}

