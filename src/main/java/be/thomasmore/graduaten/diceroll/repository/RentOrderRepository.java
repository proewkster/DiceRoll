package be.thomasmore.graduaten.diceroll.repository;

import be.thomasmore.graduaten.diceroll.entity.RentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RentOrderRepository extends JpaRepository<RentOrder, Integer>, JpaSpecificationExecutor<RentOrder> {
}
