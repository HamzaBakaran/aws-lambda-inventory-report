package ba.edu.ibu.awslambdainventoryreport.dto;




import java.util.List;

public class OrderCustomDTO {
    private String id;
    private String orderDate;
    private List<OrderItemCustomDTO> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemCustomDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItemCustomDTO> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private double total;

    public OrderCustomDTO(String id, String orderDate, List<OrderItemCustomDTO> products, double total) {
        this.id = id;
        this.orderDate = orderDate;
        this.products = products;
        this.total = total;
    }
}
