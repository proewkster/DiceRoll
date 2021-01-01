package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.User;

public class RentOrderDisplayModel {

    // Attributes
    private int rentOrderId;
    private User user;
    private boolean paid;
    private String userSummary;

    //Getters and Setters

    public int getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(int rentOrderId) {
        this.rentOrderId = rentOrderId;
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

    public String getUserSummary() {
        return this.user.getUserId() + " - " + this.user.getEmail() + " (" + this.user.getFirstName() + " " + this.user.getLastName() + ")";
    }
}
