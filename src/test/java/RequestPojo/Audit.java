package RequestPojo;

public class Audit {
    private String frequency;
    private int lookback;
    private String numScreenshots = null;
    private boolean allowThirdPartyAuditor;
    private boolean auditScreenshots;

    public Audit(String frequency, int lookback, String numScreenshots, boolean allowThirdPartyAuditor, boolean auditScreenshots) {
        this.frequency = frequency;
        this.lookback = lookback;
        this.numScreenshots = numScreenshots;
        this.allowThirdPartyAuditor = allowThirdPartyAuditor;
        this.auditScreenshots = auditScreenshots;
    }

    // Getter Methods

    public String getFrequency() {
        return frequency;
    }

    public int getLookback() {
        return lookback;
    }

    public String getNumScreenshots() {
        return numScreenshots;
    }

    public boolean getAllowThirdPartyAuditor() {
        return allowThirdPartyAuditor;
    }

    public boolean getAuditScreenshots() {
        return auditScreenshots;
    }

    // Setter Methods

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setLookback(int lookback) {
        this.lookback = lookback;
    }

    public void setNumScreenshots(String numScreenshots) {
        this.numScreenshots = numScreenshots;
    }

    public void setAllowThirdPartyAuditor(boolean allowThirdPartyAuditor) {
        this.allowThirdPartyAuditor = allowThirdPartyAuditor;
    }

    public void setAuditScreenshots(boolean auditScreenshots) {
        this.auditScreenshots = auditScreenshots;
    }
}
