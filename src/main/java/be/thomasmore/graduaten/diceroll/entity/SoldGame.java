package be.thomasmore.graduaten.diceroll.entity;


import javax.persistence.*;

@Entity
@Table(name = "SoldGames")
public class SoldGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int soldGameID;

    @ManyToOne
    @JoinColumn(name = "saleOrderID")
    private SaleOrder saleOrder;

    @ManyToOne
    @JoinColumn(name = "GameID")
    private Game game;

    private double pricePaid;
    private double discount;
    private int amount;

    public SoldGame() {
    }

    public SoldGame(SaleOrder saleOrder, Game game, double pricePaid, double discount, int amount) {
        this.saleOrder = saleOrder;
        this.game = game;
        this.pricePaid = pricePaid;
        this.discount = discount;
        this.amount = amount;
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
}
