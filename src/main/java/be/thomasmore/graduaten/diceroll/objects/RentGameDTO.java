package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.Game;

import java.util.Objects;

public class RentGameDTO {
    //attributes
    private String id;
    private Game game;
    private String title;
    private double price;
    private double discount=0;
    private int amount;
    //constructor
    public RentGameDTO() {
    }
    //properties
    public int getAantal() {
        return amount;
    }

    public void setAantal(int aantal) {
        amount = aantal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public RentGameDTO(String id, String title, double price, int aantal) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.amount = aantal;
    }

    public double getSubTotal() {
        return price * ( (100 - discount) / 100) * amount;
    }

    public String getPricePaidAsString() {
        return "€ " + String.format("%.2f", getPrice());
    }

    public String getDiscountAsString() {
        return String.format("%.2f", getDiscount()) + " %";
    }

    public String getSubTotalAsString() {
        return "€ " + String.format("%.2f", getSubTotal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentGameDTO)) return false;
        RentGameDTO that = (RentGameDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
