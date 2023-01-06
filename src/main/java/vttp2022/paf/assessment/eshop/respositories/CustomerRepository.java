package vttp2022.paf.assessment.eshop.respositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.CustomerRowMapper;
import vttp2022.paf.assessment.eshop.respositories.Queries.*;

public class CustomerRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public Optional<Customer> findCustomerByName(String name) {
		// TODO: Task 3 

		Customer cust = jdbcTemplate.query(SQL_SELECT_CUSTOMERS_BY_NAME, new CustomerRowMapper(),
		new Object[] { name });

		Optional<Customer> opt = Optional.ofNullable(cust);
		return opt;
		

			}

}
