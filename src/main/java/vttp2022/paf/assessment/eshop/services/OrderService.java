package vttp2022.paf.assessment.eshop.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.exception.OrderException;
import vttp2022.paf.assessment.eshop.models.PurchaseOrder;
import vttp2022.paf.assessment.eshop.repository.LineItemRepository;
import vttp2022.paf.assessment.eshop.repository.PurchaseOrderRepository;

@Service
public class OrderService {
    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private LineItemRepository liRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder(PurchaseOrder po) throws OrderException {

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        System.out.printf(">>>> OrderId: %s\n", orderId);

        po.setOrderId(orderId);

        // Create the purchaseOrder
        poRepo.insertPurchaseOrder(po);
        System.out.printf(">>>> order quantity: %s\n", po.getLineItems().size());
        if (po.getLineItems().size() > 5)
            throw new OrderException("Cannot order more than 5 items");
        // Create the associated line items
        liRepo.addLineItems(po.getLineItems(), orderId);

    }
}
