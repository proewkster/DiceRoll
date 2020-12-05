package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.SaleOrder;
import be.thomasmore.graduaten.diceroll.repository.SaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleOrderService {
    @Autowired
    SaleOrderRepository repository;

    public SaleOrder GetSaleOder(Integer id){
        return repository.getOne(id);
    }
    public void Save(SaleOrder saleOrder){
        repository.save(saleOrder);
    }
}
