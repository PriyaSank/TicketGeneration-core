package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.UserModel;
import com.ticket.util.ConnectionUtil;

public class UserDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();


	public void save(UserModel user)  throws PersistenceException{
		try{
		final String sql = "insert into tbl_users(name,email_id,password) values (?,?,?)";
		final Object[] params = { user.getName(), user.getEmailId(), user.getPassword() };
		jdbcTemplate.update(sql, params);
		}
		catch(DuplicateKeyException e){
			throw new PersistenceException("Email Id already exists",e);
		}
	}

	
	public void update(UserModel user) {
		final String sql = "update tbl_users set password=? where email_id=?";
		final Object[] params = { user.getPassword(), user.getEmailId() };
		jdbcTemplate.update(sql, params);

	}

	
	public void updateAsInactive(UserModel user) {

		final String sql = "update tbl_users set active=? where email_id=?";
		final Object[] params = { user.getActive(), user.getEmailId() };
		jdbcTemplate.update(sql, params);
	}
	public String getPassword(String emailId) throws PersistenceException
	{
		try{
			
		
		final String sql="select password from tbl_users where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,String.class);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new PersistenceException("Invalid Id",e);
		}
		
	}
	public Integer getUserId(String emailId)
	{
		final String sql="select id from tbl_users where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
	}

	public List<UserModel> listAll() {

		final String sql = "select id,name,email_id,password,active from tbl_users";
		return jdbcTemplate.query(sql, (rs, rownum) -> convert(rs));
	}

	private UserModel convert(ResultSet rs) throws SQLException  {
		
			UserModel user = new UserModel();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));

			user.setEmailId(rs.getString("email_id"));

			user.setPassword(rs.getString("password"));
			user.setActive(rs.getBoolean("active"));
			return user;
		
	}

	
	public UserModel listById(int id) {

		final String sql = "select id,name,email_id,password,active from tbl_users where id=?";
		final Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rownum) -> convert(rs));
	}

}
