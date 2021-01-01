package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.SaleOrder;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.objects.SaleOrderFilter;
import be.thomasmore.graduaten.diceroll.repository.SaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderService {
    @Autowired
    SaleOrderRepository repository;

    public SaleOrder getSaleOrder(Integer id){
        return repository.getOne(id);
    }

    public List<SaleOrder> findAll() { return repository.findAll(); }

    public Page<SaleOrder> findAll(SaleOrderFilter filter, Pageable pageable) { return repository.findAll(filter, pageable); }

    public List<SaleOrder>  findAllByUser(User user) { return repository.findAllByUser(user); }

    public void save(SaleOrder saleOrder){
        repository.save(saleOrder);
    }
}
