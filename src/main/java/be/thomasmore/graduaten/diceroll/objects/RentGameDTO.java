package be.thomasmore.graduaten.diceroll.objects;

import java.util.Objects;

public class RentGameDTO {
    private String id;
    private String title;
    private double price;
    private int amount;

    public int getAantal() {
        return amount;
    }

    public void setAantal(int aantal) {
        amount = aantal;
    }

    public RentGameDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public RentGameDTO(String id, String title, double price, int aantal) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.amount = aantal;
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
