package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.util.ConnectionUtil;

public class PriorityDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	
	public Integer getPriorityId(String priName)
	{
		final String sql="select id from tbl_priorities where name=?";
		final Object[] params={priName};
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
	}
}
