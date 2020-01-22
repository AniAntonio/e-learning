package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import domain.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(rs.getString("CUSTOMER_ID"));
		customer.setAddress(rs.getString("ADDRESS"));
		customer.setName(rs.getString("NAME"));
		customer.setNoOfOrdersMade(rs.getLong("NUMBER_OF_ORDERS"));
		return customer;
	}

}
