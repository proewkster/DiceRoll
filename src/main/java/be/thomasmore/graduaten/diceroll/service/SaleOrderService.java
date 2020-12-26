package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.SaleOrder;
import be.thomasmore.graduaten.diceroll.repository.SaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderService {
    @Autowired
    SaleOrderRepository repository;

    public SaleOrder getSaleOder(Integer id){
        return repository.getOne(id);
    }

    public List<SaleOrder> findAll() { return repository.findAll(); }

    public void save(SaleOrder saleOrder){
        repository.save(saleOrder);
    }
}
