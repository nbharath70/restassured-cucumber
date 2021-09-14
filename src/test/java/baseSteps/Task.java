package baseSteps;

import HelperClass.DataBaseHelper;
import HelperClass.ParseDynamicJson;
import HelperClass.VerificationHelperClass;
import RequestPojo.Task.TaskRequestPojo;
import TestBase.TestBase;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.util.ArrayList;


public class Task extends TestBase {
    Response response;
    DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public VerificationHelperClass verificationHelperClass = new VerificationHelperClass();
    public static Logger log = getMyLogger(Task.class);
    TaskRequestPojo taskRequestPojo;

    /**
     * taskData Method is used to create request payload detail data
     * @uthor Arun Kumar
     */
    public void taskData()
    {
                TaskRequestPojo taskRequestPojo1=new TaskRequestPojo();
                taskRequestPojo1.setGroups("OpsManagers");
                ArrayList groupName = taskRequestPojo1.getGroups();
                taskRequestPojo1.setAssignee("opsAssignee Bharath Narasimha");
                String assignee = taskRequestPojo1.getAssignee();
                taskRequestPojo=new TaskRequestPojo(assignee,groupName);
    }
    /**
     * This method is used to taskPostCall is used hit the POST API endpoint with the request payload
     * @uthor Arun Kumar
     * @param endPoint
     */
    public void taskPostCall(String endPoint)
    {
        response = postOperation(endPoint, taskRequestPojo);
    }

    public void verifyWorkFlowTaskAPIResponseFormatJSON()
    {
        verifyResponseFormatIsJSON();
    }

    /**
     * This method is used to validate the status code of workFlowTaskStatusCode
     * @uthor Arun Kumar
     * @param statusCode
     */
    public void verifyWorkFlowTaskStatusCode(int statusCode)
    {
        verificationHelperClass.verifyStatusCode(response, statusCode);
        log.info("WorkFlowTask StatusCode is " + statusCode + " and its Pass");
    }

    public void workFlowTaskResponseValidation()
    {
        ParseDynamicJson.responseKeyValidationfromArray(response,"taskArea");
        ParseDynamicJson.responseKeyValidationfromArray(response,"taskId");
        ParseDynamicJson.responseKeyValidationfromArray(response,"actionRequired");
        ParseDynamicJson.responseKeyValidationfromArray(response,"message");
        ParseDynamicJson.responseKeyValidationfromArray(response,"commentCount");
    }
}
