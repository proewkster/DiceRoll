package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.SoldGame;
import be.thomasmore.graduaten.diceroll.entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SaleOrderDetailsDisplayModel {

    // Attributes
    private int saleOrderID;
    List<SoldGameDTO> soldGames = new ArrayList<>();
    private User user;
    private boolean paid;
    private boolean delivered;
    private Date orderDate;

    // Getters and Setters
    public int getSaleOrderID() {
        return saleOrderID;
    }

    public void setSaleOrderID(int saleOrderID) {
        this.saleOrderID = saleOrderID;
    }

    public List<SoldGameDTO> getSoldGames() {
        return soldGames;
    }

    public void setSoldGames(List<SoldGameDTO> soldGames) {
        this.soldGames = soldGames;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return calculateTotal();
    }

    public String getTotalAsString() {
        return "€ " + String.format("%.2f", getTotal());
    }

    // Helper Methods
    private double calculateTotal() {
        double total = 0;

        for (SoldGameDTO soldGame : soldGames) {
            total += soldGame.getSubTotal();
        }

        return total;
    }
}
