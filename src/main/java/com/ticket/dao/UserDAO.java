package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.UserModel;
import com.ticket.util.ConnectionUtil;

public class UserDAO implements InterfaceDAO<UserModel> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(UserModel user) {
		final String sql = "insert into tbl_users(name,email_id,password) values (?,?,?)";
		final Object[] params = { user.getName(), user.getEmailId(), user.getPassword() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void update(UserModel user) {
		final String sql = "update tbl_users set password=? where email_id=?";
		final Object[] params = { user.getPassword(), user.getEmailId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(UserModel user) {

		final String sql = "update tbl_users set active=? where email_id=?";
		final Object[] params = { user.getActive(), user.getEmailId() };
		jdbcTemplate.update(sql, params);
	}
	public UserModel getPassword(String emailId)
	{
		final String sql="select password from tbl_users where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,(rs,rowNo)->{
			UserModel user=new UserModel();
			user.setPassword(rs.getString("password"));
			return user;
		});
	}
	public UserModel getUserId(String emailId)
	{
		final String sql="select id from tbl_users where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,(rs,rowNo)->{
			UserModel user=new UserModel();
			user.setId(rs.getInt("id"));
			return user;
		});
	}
	@Override
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

	@Override
	public UserModel listById(int id) {

		final String sql = "select id,name,email_id,password,active from tbl_users where id=?";
		final Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rownum) -> convert(rs));
	}

}
