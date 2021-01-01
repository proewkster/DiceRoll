package be.thomasmore.graduaten.diceroll.repository;

import be.thomasmore.graduaten.diceroll.entity.SaleOrder;
import be.thomasmore.graduaten.diceroll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder,Integer>, JpaSpecificationExecutor<SaleOrder> {

    List<SaleOrder> findAllByUser(User user);
}
