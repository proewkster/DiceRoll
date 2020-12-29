package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.Game;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentedGameDTO {

    // Attributes
    private Game game;
    private double pricePaid;
    private double discount;
    private boolean delivered;
    private boolean returned;
    private Date startDate;
    private Date endDate;

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

    public Date getStartDate() throws ParseException {
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

    public double getSubTotal() { return pricePaid * ((100 - discount) / 100); }

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

    public String getStartDateAsString() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.startDate);
    }

    public String getEndDateAsString() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.endDate);
    }
}
