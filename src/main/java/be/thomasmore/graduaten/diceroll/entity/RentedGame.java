package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RentedGames")
public class RentedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int RentedGameID;
    @ManyToOne
    @JoinColumn(name = "RentOrderID")
    private RentOrder rentOrder;
    @ManyToOne
    @JoinColumn(name = "GameID")
    private Game game;
    private double PricePaid;
    private double Discount;
    private boolean Delivered;
    private boolean Returned;
    private Date StartDate;
    private Date EndDate;

    public RentedGame() {
    }

    public RentedGame(RentOrder rentOrder, Game game, double pricePaid, double discount, boolean delivered, boolean returned, Date startDate, Date endDate) {
        this.rentOrder = rentOrder;
        this.game = game;
        PricePaid = pricePaid;
        Discount = discount;
        Delivered = delivered;
        Returned = returned;
        StartDate = startDate;
        EndDate = endDate;
    }

    public int getRentedGameID() {
        return RentedGameID;
    }

    public void setRentedGameID(int rentedGameID) {
        RentedGameID = rentedGameID;
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

    public boolean isDelivered() {
        return Delivered;
    }

    public void setDelivered(boolean delivered) {
        Delivered = delivered;
    }

    public boolean isReturned() {
        return Returned;
    }

    public void setReturned(boolean returned) {
        Returned = returned;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
}
