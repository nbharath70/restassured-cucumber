package RequestPojo;

import java.util.ArrayList;

public class ContractDetailJson {
    private int schemaVersion;
    private ArrayList lineOfBusiness;
    private ArrayList locations;
    private String billingCycle;
    private int submissionWindow;
    private int resubmissionWindow;
    private int paymentTerms;
    private boolean thirdPartyAuth;
    private String opsAssignee = null;
    private String opsQCer = null;
    Payment PaymentObject;
    Audit AuditObject;

    public ContractDetailJson()
    {

    }

    public ContractDetailJson(ArrayList lineOfBusiness) {
        this.lineOfBusiness=lineOfBusiness;
    }

    public ContractDetailJson(int schemaVersion,ArrayList lineOfBusiness, ArrayList locations, String billingCycle, int submissionWindow, int resubmissionWindow, int paymentTerms, boolean thirdPartyAuth, String opsAssignee, String opsQCer, Payment paymentObject, Audit auditObject) {
        this.schemaVersion = schemaVersion;
        this.lineOfBusiness=lineOfBusiness;
        this.locations = locations;
        this.billingCycle = billingCycle;
        this.submissionWindow = submissionWindow;
        this.resubmissionWindow = resubmissionWindow;
        this.paymentTerms = paymentTerms;
        this.thirdPartyAuth = thirdPartyAuth;
        this.opsAssignee = opsAssignee;
        this.opsQCer = opsQCer;
        PaymentObject = paymentObject;
        AuditObject = auditObject;
    }


    // Getter Methods

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public int getSubmissionWindow() {
        return submissionWindow;
    }

    public int getResubmissionWindow() {
        return resubmissionWindow;
    }

    public int getPaymentTerms() {
        return paymentTerms;
    }

    public boolean getThirdPartyAuth() {
        return thirdPartyAuth;
    }

    public String getOpsAssignee() {
        return opsAssignee;
    }

    public String getOpsQCer() {
        return opsQCer;
    }

    public Payment getPayment() {
        return PaymentObject;
    }

    public Audit getAudit() {
        return AuditObject;
    }

    // Setter Methods

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }


    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    public void setSubmissionWindow(int submissionWindow) {
        this.submissionWindow = submissionWindow;
    }

    public void setResubmissionWindow(int resubmissionWindow) {
        this.resubmissionWindow = resubmissionWindow;
    }

    public void setPaymentTerms(int paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public void setThirdPartyAuth(boolean thirdPartyAuth) {
        this.thirdPartyAuth = thirdPartyAuth;
    }

    public void setOpsAssignee(String opsAssignee) {
        this.opsAssignee = opsAssignee;
    }

    public void setOpsQCer(String opsQCer) {
        this.opsQCer = opsQCer;
    }

    public void setPayment(Payment paymentObject) {
        this.PaymentObject = paymentObject;
    }

    public void setAudit(Audit auditObject) {
        this.AuditObject = auditObject;
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

    public ArrayList getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        String[] loc = locations.split(",");
        ArrayList<String> arr=new ArrayList<String>();
        for(String newLoc:loc)
        {
            arr.add(newLoc);
        }
        this.locations = arr;
    }
}