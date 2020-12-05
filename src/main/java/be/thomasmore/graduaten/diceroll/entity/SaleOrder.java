package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "SaleOrders")
public class SaleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SaleOrderID;

    @OneToMany(mappedBy = "saleOrder")
    Set<SoldGame> soldGames = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "UserID",nullable = false)
    private User user;
    private boolean Paid;
    private boolean Delivered;
    private Date OrderDate;

    public Set<SoldGame> getSoldGames() {
        return soldGames;
    }

    public void setSoldGames(Set<SoldGame> soldGames) {
        this.soldGames = soldGames;
    }

    public SaleOrder() {
    }

    public int getSaleOrderID() {
        return SaleOrderID;
    }

    public void setSaleOrderID(int saleOrderID) {
        SaleOrderID = saleOrderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return Paid;
    }

    public void setPaid(boolean paid) {
        Paid = paid;
    }

    public boolean isDelivered() {
        return Delivered;
    }

    public void setDelivered(boolean delivered) {
        Delivered = delivered;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }
}
