package ba.edu.ibu.awslambdainventoryreport.dto;

public class OrderItemCustomDTO {
    private String productName;
    private int quantity;
    private double price;
    private double subtotal;

    // Constructors
    public OrderItemCustomDTO() {
        // Default constructor
    }

    public OrderItemCustomDTO(String productName, int quantity, double price, double subtotal) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    // Getters and setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}