package vttp2022.paf.assessment.eshop.respositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation.AddFieldsOperationBuilder;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.jdbc.core.JdbcTemplate;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.Queries.*;
import vttp2022.paf.assessment.eshop.respositories.*;


public class OrderRepository {
	// TODO: Task 3

	@Autowired
    private JdbcTemplate template;

	public boolean insertOrder(Order o) {
        return  template.update(SQL_INSERT_PURCHASE_ORDER, 
            o.getOrderId(), o.getName(), o.getOrderDate()) > 0;
    }

    public void addLineItems(Order order) {
        addLineItems(order.getLineItems(), order.getOrderId());
    }

    public void addLineItems(List<LineItem> lineItems, String orderId) {
        List<Object[]> data = lineItems.stream()
            .map(li -> {
                Object[] l = new Object[3];
                l[0] = li.getItem();
                l[1] = li.getQuantity();
                l[2] = orderId;
                return l;
            })
            .toList();

        // Batch update
        template.batchUpdate(SQL_INSERT_PURCHASE_ORDER, data);
    }
    
}
