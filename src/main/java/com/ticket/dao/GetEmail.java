package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.util.ConnectionUtil;

public class GetEmail {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
public String getAdminEmail(int id) {
		
		final String sql1="select department_id from tbl_ticket_details where id=?";
		final Object[] params1={id};
		
		int depId=jdbcTemplate.queryForObject(sql1,params1,Integer.class);
		
		final String sql2="select email_id from tbl_employees where department_id=? and role_id=1";
		final Object[] params2={depId};
		
		return jdbcTemplate.queryForObject(sql2,params2,String.class);
		
	}
public String getEmployeeEmail(int id) {
	
	final String sql1="select employee_id from tbl_ticket_details where id=?";
	final Object[] params1={id};
	
	int depId=jdbcTemplate.queryForObject(sql1,params1,Integer.class);
	
	final String sql2="select email_id from tbl_employees where id=?";
	final Object[] params2={depId};
	
	return jdbcTemplate.queryForObject(sql2,params2,String.class);
	
}
public String getUserEmail(int id) {
		
		final String sql1="select user_id from tbl_ticket_details where id=?";
		final Object[] params1={id};
		
		int userId=jdbcTemplate.queryForObject(sql1,params1,Integer.class);
		
		final String sql2="select email_id from tbl_users where id=?";
		final Object[] params2={userId};
		
		return jdbcTemplate.queryForObject(sql2,params2,String.class);
		
		
	}
}
