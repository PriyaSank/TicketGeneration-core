package com.ticket.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.util.ConnectionUtil;

public class EmployeeModule {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public List<TicketDetailsDAO> viewAssignedTickets(){
	
		return null;
		
	}
}
