package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name= "RentOrders")
public class RentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int RentOrderID;
    @OneToMany(mappedBy = "rentOrder")
    Set<RentedGame> rentedGames = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "UserID",nullable = false)
    private User user;
    private boolean Paid;

    public RentOrder() {
    }

    public RentOrder(User user, boolean paid) {
        this.user = user;
        Paid = paid;
    }

    public int getRentOrderID() {
        return RentOrderID;
    }

    public void setRentOrderID(int rentOrderID) {
        RentOrderID = rentOrderID;
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

    public Set<RentedGame> getRentedGames() {
        return rentedGames;
    }

    public void setRentedGames(Set<RentedGame> rentedGames) {
        this.rentedGames = rentedGames;
    }
}
