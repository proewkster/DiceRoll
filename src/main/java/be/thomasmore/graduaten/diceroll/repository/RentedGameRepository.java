package be.thomasmore.graduaten.diceroll.repository;

import be.thomasmore.graduaten.diceroll.entity.RentedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentedGameRepository extends JpaRepository<RentedGame,Integer> {
    @Query("SELECT rg FROM RentedGame rg INNER JOIN RentOrder r ON r.rentOrderID=rg.rentOrder.rentOrderID WHERE r.rentOrderID = ?1")
    public List<RentedGame> getRentedGamesByRentOrder(int id);
}
