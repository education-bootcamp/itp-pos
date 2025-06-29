package entity;

public class Product {
    private String id;
    private int qty;
    private double unitPrice;
    private String description;

    public Product() {

    }

    public Product(String id, int qty, double unitPrice, String description) {
        this.id = id;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
