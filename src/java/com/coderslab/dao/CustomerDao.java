package com.coderslab.dao;

import com.coderslab.entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Customer> getAllCustomer() {
        String sql = "select * from customer";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    public Customer getCustomer(int id) {
        String sql = "select * from customer where cid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerMapper());
    }

    public static class CustomerMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCid(rs.getInt("cid"));
            customer.setCname(rs.getString("cname"));
            customer.setPhone(rs.getString("phone"));
            return customer;
        }

    }

    public boolean saveCustomer(Customer customer) {
        String sql = "insert into customer (cname, phone) values (?, ?)";
        int value = jdbcTemplate.update(sql, new Object[]{customer.getCname(), customer.getPhone()});
        if (value > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "update customer set cname=?, phone=? where cid=?";
        int value = jdbcTemplate.update(sql, new Object[]{customer.getCname(), customer.getPhone(), customer.getCid()});
        if (value > 0) {
            return true;
        }
        return false;
    }

    public boolean deleteCustomer(int id) {
        String sql = "delete from customer where cid=?";
        int value = jdbcTemplate.update(sql, new Object[]{id});
        if (value > 0) {
            return true;
        }
        return false;
    }

}
