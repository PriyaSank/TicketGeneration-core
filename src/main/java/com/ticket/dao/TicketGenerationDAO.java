package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;

public class TicketGenerationDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public String ticketGenerate(TicketDetailsModel tic,int userId){
		
		
		final String sql="insert into tbl_ticket_details(user_id,department_id,subject,description,priority_id)values(?,?,?,?,?)";
		final Object[] params={userId,tic.getDept().getId(),tic.getSubject(),tic.getDescription(),tic.getPrior().getId()};
		jdbcTemplate.update(sql,params);
		return "Ticket generated successfully";
	
		
	}
}
