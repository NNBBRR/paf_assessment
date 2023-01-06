package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.Queries.*;

public class OrderRepository {
	// TODO: Task 3

	@Autowired
    private JdbcTemplate template;

	public boolean insertOrder(Order o) {
        return  template.update(SQL_INSERT_PURCHASE_ORDER, 
            o.getOrderId(), o.getName(), o.getOrderDate()) > 0;
    }

}
