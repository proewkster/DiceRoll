package be.thomasmore.graduaten.diceroll.objects;

public class TestDTO {
    private String id;
    private String Title;
    private int Aantal;
    private double price;

    public TestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getAantal() {
        return Aantal;
    }

    public void setAantal(int aantal) {
        Aantal = aantal;
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

