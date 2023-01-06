package vttp2022.paf.assessment.eshop.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer c = new Customer();
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("address"));
        c.setEmail(rs.getString("email"));
        return c;
    }
}

// maps a row of a result-set to the Customer record