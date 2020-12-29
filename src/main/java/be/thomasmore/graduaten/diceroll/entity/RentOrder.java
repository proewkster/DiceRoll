package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name= "RentOrders")
public class RentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentOrderID;

    @OneToMany(mappedBy = "rentOrder")
    Set<RentedGame> rentedGames = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "UserID",nullable = false)
    private User user;

    private boolean paid;

    public RentOrder() {
    }

    public RentOrder(User user, boolean paid) {
        this.user = user;
        this.paid = paid;
    }

    public int getRentOrderID() {
        return rentOrderID;
    }

    public void setRentOrderID(int rentOrderID) {
        this.rentOrderID = rentOrderID;
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

    public Set<RentedGame> getRentedGames() {
        return rentedGames;
    }

    public void setRentedGames(Set<RentedGame> rentedGames) {
        this.rentedGames = rentedGames;
    }
}
