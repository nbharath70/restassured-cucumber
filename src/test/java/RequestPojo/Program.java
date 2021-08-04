package RequestPojo;

public class Program {
    private String contractId;
    private String startDate;
    private String endDate;
    ProgramDetailJson ProgramDetailJsonObject;

    public Program(String contractId, String startDate, String endDate, ProgramDetailJson programDetailJsonObject) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        ProgramDetailJsonObject = programDetailJsonObject;
    }

    public Program(String contractId, String startDate, ProgramDetailJson programDetailJsonObject) {
        this.contractId = contractId;
        this.startDate = startDate;
        ProgramDetailJsonObject = programDetailJsonObject;
    }



    // Getter Methods


    public String getContractId() {
        return contractId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public ProgramDetailJson getProgramDetailJson() {
        return ProgramDetailJsonObject;
    }

    // Setter Methods

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setProgramDetailJson(ProgramDetailJson programDetailJsonObject) {
        this.ProgramDetailJsonObject = programDetailJsonObject;
    }
}
