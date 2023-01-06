package vttp2022.paf.assessment.eshop.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.exception.OrderException;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.DispatchOrder;
import vttp2022.paf.assessment.eshop.respositories.*;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private DispatchRepository disRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder(Order o) throws OrderException {

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        System.out.printf(">>>> OrderId: %s\n", orderId);

        o.setOrderId(orderId);

        // Create the purchaseOrder
        orderRepo.insertOrder(o);
        System.out.printf(">>>> order quantity: %s\n", o.getLineItems().size());
        if (o.getLineItems().size() > 10)
            throw new OrderException("Cannot order more than 10 items");
        // Create the associated line items
        orderRepo.addLineItems(o.getLineItems(), orderId);

    }

    public List<DispatchOrder> aggregateOrder() {
        return disRepo.aggregateOrder();
        
    }
}
