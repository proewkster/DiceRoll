package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RentedGames")
public class RentedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentedGameID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentOrderID")
    private RentOrder rentOrder;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GameID")
    private Game game;
    private double pricePaid;
    private double discount;
    private boolean delivered;
    private boolean returned;
    private Date startDate;
    private Date endDate;

    public RentedGame() {
    }

    public RentedGame(RentOrder rentOrder, Game game, double pricePaid, double discount, boolean delivered, boolean returned, Date startDate, Date endDate) {
        this.rentOrder = rentOrder;
        this.game = game;
        this.pricePaid = pricePaid;
        this.discount = discount;
        this.delivered = delivered;
        this.returned = returned;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRentedGameID() {
        return rentedGameID;
    }

    public void setRentedGameID(int rentedGameID) {
        this.rentedGameID = rentedGameID;
    }

    public RentOrder getRentOrder() {
        return rentOrder;
    }

    public void setRentOrder(RentOrder rentOrder) {
        this.rentOrder = rentOrder;
    }

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

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
