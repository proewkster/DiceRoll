package be.thomasmore.graduaten.diceroll.objects;

import java.util.Objects;

public class SessionGameDTO {
    private String id;
    private String title;
    private int amount;
    private double price;
    private double discount;

    public SessionGameDTO() {
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
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

    public SessionGameDTO(String id, String title, int amount, double price) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionGameDTO)) return false;
        SessionGameDTO sessionGameDTO = (SessionGameDTO) o;
        return getId().equals(sessionGameDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

