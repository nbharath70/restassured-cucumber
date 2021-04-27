package RequestPojo;

import java.util.ArrayList;

public class ContractDetailJson {
    private float schemaVersion;
    private ArrayList lineOfBusiness;
    private String locations;
    private String billingCycle;
    private float submissionWindow;
    private float resubmissionWindow;
    private float paymentTerms;
    private boolean thirdPartyAuth;
    private String opsAssignee = null;
    private String opsQCer = null;
    Payment PaymentObject;
    Audit AuditObject;

    public ContractDetailJson()
    {

    }

    public ContractDetailJson(float schemaVersion,ArrayList lineOfBusiness, String locations, String billingCycle, float submissionWindow, float resubmissionWindow, float paymentTerms, boolean thirdPartyAuth, String opsAssignee, String opsQCer, Payment paymentObject, Audit auditObject) {
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

    public float getSchemaVersion() {
        return schemaVersion;
    }

    public String getLocations() {
        return locations;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public float getSubmissionWindow() {
        return submissionWindow;
    }

    public float getResubmissionWindow() {
        return resubmissionWindow;
    }

    public float getPaymentTerms() {
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

    public void setSchemaVersion(float schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    public void setSubmissionWindow(float submissionWindow) {
        this.submissionWindow = submissionWindow;
    }

    public void setResubmissionWindow(float resubmissionWindow) {
        this.resubmissionWindow = resubmissionWindow;
    }

    public void setPaymentTerms(float paymentTerms) {
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
}