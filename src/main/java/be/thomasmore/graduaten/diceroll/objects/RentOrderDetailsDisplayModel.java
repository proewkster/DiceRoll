package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.User;

import java.util.ArrayList;
import java.util.List;

public class RentOrderDetailsDisplayModel {

    // Attributes
    private int rentOrderID;
    List<RentedGameDTO> rentedGames = new ArrayList<>();
    private User user;
    private boolean paid;

    // Getters and Setters

    public int getRentOrderID() {
        return rentOrderID;
    }

    public void setRentOrderID(int rentOrderID) {
        this.rentOrderID = rentOrderID;
    }

    public List<RentedGameDTO> getRentedGames() {
        return rentedGames;
    }

    public void setRentedGames(List<RentedGameDTO> rentedGames) {
        this.rentedGames = rentedGames;
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

    public double getTotal() { return calculateTotal(); }

    public String getTotalAsString() { return "€ " + String.format("%.2f", getTotal()); }

    // Helper Methods
    private double calculateTotal() {
        double total = 0;

        for (RentedGameDTO rentedGame : rentedGames) {
            total += rentedGame.getSubTotal();
        }

        return total;
    }
}
