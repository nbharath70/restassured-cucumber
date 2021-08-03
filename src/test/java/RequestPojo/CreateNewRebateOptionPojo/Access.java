package RequestPojo.CreateNewRebateOptionPojo;

public class Access {
    private String pricingMethod;

    public Access(String pricingMethod, int price) {
        this.pricingMethod = pricingMethod;
        this.price = price;
    }

    private int price;


    // Getter Methods

    public String getPricingMethod() {
        return pricingMethod;
    }

    public int getPrice() {
        return price;
    }

    // Setter Methods

    public void setPricingMethod(String pricingMethod) {
        this.pricingMethod = pricingMethod;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
