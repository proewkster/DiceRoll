package be.thomasmore.graduaten.diceroll.repository;

import be.thomasmore.graduaten.diceroll.entity.RentOrder;
import be.thomasmore.graduaten.diceroll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentOrderRepository extends JpaRepository<RentOrder, Integer>, JpaSpecificationExecutor<RentOrder> {

    List<RentOrder> findAllByUser(User user);
}
