package com.coderslab.dao;

import com.coderslab.entity.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    public Users getUser(String userName, String password) {
        String sql = "select * from users where userName=? and password=?";
        try {
            Users u = jdbcTemplate.queryForObject(sql, new Object[]{userName, password}, new UsersMapper());
            if (u != null) {
                return u;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static class UsersMapper implements RowMapper<Users> {

        @Override
        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

            Users u = new Users();
            u.setUserId(rs.getInt("userId"));
            u.setUserName(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            return u;
        }

    }

}
