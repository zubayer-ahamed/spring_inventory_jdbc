package com.coderslab.dao;

import com.coderslab.entity.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

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

    public String getOrderNo() {
        String sql = "select max(oid) from orders";
        String orderId = jdbcTemplate.queryForObject(sql, String.class);
        if (orderId == null) {
            orderId = "1";
        } else {
            orderId = String.valueOf(Integer.parseInt(orderId) + 1);
        }

        return orderId;
    }

    public List<Orders> getAllOrders() {
        String sql = "select * from orders";
        return jdbcTemplate.query(sql, new OrderMapper());
    }

    private static class OrderMapper implements RowMapper<Orders> {

        @Override
        public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
            Orders o = new Orders();
            o.setOid(rs.getInt("oid"));
            o.setCid(rs.getInt("cid"));
            o.setTotal(rs.getDouble("total"));
            o.setOrderType(rs.getString("orderType"));
            o.setOrderDate(rs.getDate("orderDate"));
            return o;
        }

    }

    public boolean saveOrder(Orders orders) {
        String sql = "insert into orders (cid, total, orderType, orderDate) values (?, ?, ?, ?)";
        int value = jdbcTemplate.update(sql, new Object[]{orders.getCid(), orders.getTotal(), orders.getOrderType(), orders.getOrderDate()});
        if (value > 0) {
            return true;
        } else {
            return false;
        }
    }

}
