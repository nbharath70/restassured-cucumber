package RequestPojo.CreateNewRebateOptionPojo;

public class CreateNewRebateOptions {
    private String mfrRebateOptionName;
    private int mfrProgramId;
    private String startDate;
    private String endDate;
    FormularyRequirementJson FormularyRequirementJsonObject;
    RebateTermJson RebateTermJsonObject;

    public CreateNewRebateOptions(String mfrRebateOptionName, int mfrProgramId, String startDate, String endDate, FormularyRequirementJson formularyRequirementJsonObject, RebateTermJson rebateTermJsonObject) {
        this.mfrRebateOptionName = mfrRebateOptionName;
        this.mfrProgramId = mfrProgramId;
        this.startDate = startDate;
        this.endDate = endDate;
        FormularyRequirementJsonObject = formularyRequirementJsonObject;
        RebateTermJsonObject = rebateTermJsonObject;
    }




    // Getter Methods


    public String getMfrRebateOptionName() {
        return mfrRebateOptionName;
    }

    public int getMfrProgramId() {
        return mfrProgramId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public FormularyRequirementJson getFormularyRequirementJson() {
        return FormularyRequirementJsonObject;
    }

    public RebateTermJson getRebateTermJson() {
        return RebateTermJsonObject;
    }

    // Setter Methods


    public void setMfrRebateOptionName(String mfrRebateOptionName) {
        this.mfrRebateOptionName = mfrRebateOptionName;
    }

    public void setMfrProgramId(int mfrProgramId) {
        this.mfrProgramId = mfrProgramId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setFormularyRequirementJson(FormularyRequirementJson formularyRequirementJsonObject) {
        this.FormularyRequirementJsonObject = formularyRequirementJsonObject;
    }

    public void setRebateTermJson(RebateTermJson rebateTermJsonObject) {
        this.RebateTermJsonObject = rebateTermJsonObject;
    }
}

