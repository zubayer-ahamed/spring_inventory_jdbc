package com.coderslab.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    
    public String getOrderNo(){
        String sql = "select max(oid) from orders";
        String orderId = jdbcTemplate.queryForObject(sql, String.class);
        if(orderId == null){
            orderId = "1";
        }else{
            orderId = String.valueOf(Integer.parseInt(orderId) + 1);
        }
        
        return orderId;
    }

}
