package RequestPojo;

public class CreateNewRebateProgramPojo {

    Program ProgramObject;

    public CreateNewRebateProgramPojo(Program programObject) {
        ProgramObject = programObject;
    }

    // Getter Methods

    public Program getProgram() {
        return ProgramObject;
    }

    // Setter Methods

    public void setProgram(Program programObject) {
        this.ProgramObject = programObject;
    }
}