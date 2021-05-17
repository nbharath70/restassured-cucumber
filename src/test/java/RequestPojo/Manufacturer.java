package RequestPojo;

public class Manufacturer {
    private String manufacturerId;
    private String name;
    private boolean currentFlag;

    public Manufacturer(String manufacturerId, String name) {
        this.manufacturerId = manufacturerId;
        this.name = name;
    }

    public Manufacturer(String manufacturerId, String name, boolean currentFlag) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.currentFlag = currentFlag;
    }


    // Getter Methods

    public String getManufacturerId() {
        return manufacturerId;
    }

    public String getName() {
        return name;
    }

    public boolean getCurrentFlag() {
        return currentFlag;
    }

    // Setter Methods

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentFlag(boolean currentFlag) {
        this.currentFlag = currentFlag;
    }
}
