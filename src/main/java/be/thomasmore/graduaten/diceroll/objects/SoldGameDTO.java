package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.Game;

public class SoldGameDTO {

    // Attributes
    private Game game;
    private double pricePaid;
    private double discount;
    private int amount;

    // Getters and Setters
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getSubTotal() {
        return pricePaid * ( (100 - discount) / 100) * amount;
    }

    // String Getters
    public String getPricePaidAsString() {
        return "€ " + String.format("%.2f", getPricePaid());
    }

    public String getDiscountAsString() {
        return String.format("%.2f", getDiscount()) + " %";
    }

    public String getSubTotalAsString() {
        return "€ " + String.format("%.2f", getSubTotal());
    }
}
