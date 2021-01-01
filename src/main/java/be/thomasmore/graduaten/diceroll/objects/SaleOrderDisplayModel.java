package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.User;

import java.sql.Date;

public class SaleOrderDisplayModel {

    // Attributes
    private int saleOrderId;
    private Date orderDate;
    private User user;
    private boolean paid;
    private boolean delivered;
    private String userSummary;

    //Getters and Setters
    public int getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(int orderId) {
        this.saleOrderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String getUserSummary() {
        return this.user.getUserId() + " - " + this.user.getEmail() + " (" + this.user.getFirstName() + " " + this.user.getLastName() + ")";
    }
}
