package RequestPojo;

public class ProgramDetailJson<Int> {
    private String programName;
    private String legacyContractID;
    private int rebateableDrugGroupId;
    private int qualCompDrugGroupId;
    private int mktBasketDrugGroupId;
    private String pricingBasis;
    private String pricingRefDate;
    private Boolean psfApply;
    private float programLevelPsf;

    public ProgramDetailJson(String programName, String legacyContractID, int rebateableDrugGroupId, int qualCompDrugGroupId, int mktBasketDrugGroupId, String pricingBasis, String pricingRefDate, Boolean psfApply, float programLevelPsf) {
        this.programName = programName;
        this.legacyContractID = legacyContractID;
        this.rebateableDrugGroupId = rebateableDrugGroupId;
        this.qualCompDrugGroupId = qualCompDrugGroupId;
        this.mktBasketDrugGroupId = mktBasketDrugGroupId;
        this.pricingBasis = pricingBasis;
        this.pricingRefDate = pricingRefDate;
        this.psfApply = psfApply;
        this.programLevelPsf = programLevelPsf;
    }


    public ProgramDetailJson(String programName, int rebateableDrugGroupId, int qualCompDrugGroupId,int mktBasketDrugGroupId, String pricingBasis, String pricingRefDate, Boolean psfApply,float programLevelPsf) {
        this.programName = programName;
        this.rebateableDrugGroupId = rebateableDrugGroupId;
        this.qualCompDrugGroupId = qualCompDrugGroupId;
        this.mktBasketDrugGroupId = mktBasketDrugGroupId;
        this.pricingBasis = pricingBasis;
        this.pricingRefDate = pricingRefDate;
        this.psfApply = psfApply;
        this.programLevelPsf = programLevelPsf;
    }


    // Getter Methods

    public String getProgramName() {
        return programName;
    }

    public String getLegacyContractID() {
        return legacyContractID;
    }

    public int getRebateableDrugGroupId() {
        return rebateableDrugGroupId;
    }

    public int getQualCompDrugGroupId() {
        return qualCompDrugGroupId;
    }

    public int getMktBasketDrugGroupId() {
        return mktBasketDrugGroupId;
    }

    public String getPricingBasis() {
        return pricingBasis;
    }

    public String getPricingRefDate() {
        return pricingRefDate;
    }

    public Boolean getPsfApply() {
        return psfApply;
    }

    public float getProgramLevelPsf() {
        return programLevelPsf;
    }

    // Setter Methods

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setLegacyContractID(String legacyContractID) {
        this.legacyContractID = legacyContractID;
    }

    public void setRebateableDrugGroupId(int rebateableDrugGroupId) {
        this.rebateableDrugGroupId = rebateableDrugGroupId;
    }

    public void setQualCompDrugGroupId(int qualCompDrugGroupId) {
        this.qualCompDrugGroupId = qualCompDrugGroupId;
    }

    public void setMktBasketDrugGroupId(int mktBasketDrugGroupId) {
        this.mktBasketDrugGroupId = mktBasketDrugGroupId;
    }

    public void setPricingBasis(String pricingBasis) {
        this.pricingBasis = pricingBasis;
    }

    public void setPricingRefDate(String pricingRefDate) {
        this.pricingRefDate = pricingRefDate;
    }

    public void setPsfApply(Boolean psfApply) {
        this.psfApply = psfApply;
    }

    public void setProgramLevelPsf(float programLevelPsf) {
        this.programLevelPsf = programLevelPsf;
    }
}
