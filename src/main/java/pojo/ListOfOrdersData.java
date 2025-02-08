package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOfOrdersData {
    private List<Order> orders;

    public ListOfOrdersData(List<Order> orders) {
        this.orders = orders;
    }

    public ListOfOrdersData() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
