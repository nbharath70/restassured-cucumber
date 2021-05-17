package RequestPojo;

public class Payment {
    private int disputeDays;
    private String lateFee;
    private String lateFeeFixed = null;
    private String lateFeePct = null;
    private boolean paymentBackup;
    private String NCPDPReconFile = null;

    public Payment(int disputeDays, String lateFee, String lateFeeFixed, String lateFeePct, boolean paymentBackup, String NCPDPReconFile) {
        this.disputeDays = disputeDays;
        this.lateFee = lateFee;
        this.lateFeeFixed = lateFeeFixed;
        this.lateFeePct = lateFeePct;
        this.paymentBackup = paymentBackup;
        this.NCPDPReconFile = NCPDPReconFile;
    }
// Getter Methods

    public int getDisputeDays() {
        return disputeDays;
    }

    public String getLateFee() {
        return lateFee;
    }

    public String getLateFeeFixed() {
        return lateFeeFixed;
    }

    public String getLateFeePct() {
        return lateFeePct;
    }

    public boolean getPaymentBackup() {
        return paymentBackup;
    }

    public String getNCPDPReconFile() {
        return NCPDPReconFile;
    }

    // Setter Methods

    public void setDisputeDays(int disputeDays) {
        this.disputeDays = disputeDays;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public void setLateFeeFixed(String lateFeeFixed) {
        this.lateFeeFixed = lateFeeFixed;
    }

    public void setLateFeePct(String lateFeePct) {
        this.lateFeePct = lateFeePct;
    }

    public void setPaymentBackup(boolean paymentBackup) {
        this.paymentBackup = paymentBackup;
    }

    public void setNCPDPReconFile(String NCPDPReconFile) {
        this.NCPDPReconFile = NCPDPReconFile;
    }
}

