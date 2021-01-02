package be.thomasmore.graduaten.diceroll.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "SaleOrders")
public class SaleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleOrderID;

    @OneToMany(mappedBy = "saleOrder")
    Set<SoldGame> soldGames = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "UserID",nullable = false)
    private User user;

    private boolean paid;
    private boolean delivered;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Gelieve Afhaaldatum in te geven")
    private Date orderDate;

    public Set<SoldGame> getSoldGames() {
        return soldGames;
    }

    public void setSoldGames(Set<SoldGame> soldGames) {
        this.soldGames = soldGames;
    }

    public SaleOrder() {
    }

    public int getSaleOrderID() {
        return saleOrderID;
    }

    public void setSaleOrderID(int saleOrderID) {
        this.saleOrderID = saleOrderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
