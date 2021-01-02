package be.thomasmore.graduaten.diceroll.objects;

import javax.validation.constraints.NotBlank;

public class OrderDTO {
    @NotBlank(message = "Gelieve een Afhaaldatum in te geven")
    private String orderDate;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public OrderDTO() {
    }
}
