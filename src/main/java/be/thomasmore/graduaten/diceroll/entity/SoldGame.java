package be.thomasmore.graduaten.diceroll.entity;


import javax.persistence.*;

@Entity
@Table(name = "SoldGames")
public class SoldGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SoldGameID;

    @ManyToOne
    @JoinColumn(name = "SaleOrderID")
    private SaleOrder saleOrder;

    @ManyToOne
    @JoinColumn(name = "GameID")
    private Game game;

    private double PricePaid;
    private double Discount;
    private int Amount;

    public SoldGame() {
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }

    public double getPricePaid() {
        return PricePaid;
    }

    public void setPricePaid(double pricePaid) {
        PricePaid = pricePaid;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
