package stepdefs;

import baseSteps.SendToOpsAssigneeDrugGroup;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class SendToOpsAssigneeDrugGroupStepDef {
        SendToOpsAssigneeDrugGroup sendToOpsAssigneeDrugGroup=new SendToOpsAssigneeDrugGroup();
     @Given("^User creates the DrugGroupUsing this data$")
    public void user_creates_the_druggroupusing_this_data(DataTable dataTable) {
         sendToOpsAssigneeDrugGroup.createNewDrugGroup(dataTable);

     }

    @And("^User Add Some Drugs to created DruGroup$")
    public void userAddSomeDrugsToCreatedDruGroup() {

    }
}


