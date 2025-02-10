package com.assignment4.assignment4_loginDemo.dao;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.assignment4.assignment4_loginDemo.model.User;

@Repository
public class UserDao {
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getInt("userId"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("type"),
            rs.getString("password")
    );
	
	public int saveUser(User user) {
        String sql = "INSERT INTO users (name, email, type, password) VALUES ( ?, ?, ?, ?)";
       return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getType(), user.getPassword());
    }
	
	public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, userRowMapper, email).stream().findFirst();
    }
	
}
