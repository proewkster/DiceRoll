package be.thomasmore.graduaten.diceroll.objects;

public class RentGameDTO {
    private String id;
    private String Title;
    private double price;

    public RentGameDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
