package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.util.ConnectionUtil;

public class UserModules {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
public String closeTicket(String emailId,String pwd,String ticketId){
	UserLoginDAO log=new UserLoginDAO();
	if(log.logIn(emailId,pwd))
	{
		final String sql="update tbl_ticket_details set status=? where id=?";
		final Object[] params={"CLOSED",ticketId};
		jdbcTemplate.update(sql,params);
		return "Ticket Closed";
	}
	return "Enter proper username and password";
}
public String updateTicket(String emailId,String pwd,String ticketId,String ticketStatus){
	UserLoginDAO log=new UserLoginDAO();
	if(log.logIn(emailId,pwd))
	{
		final String sql="update tbl_ticket_details set status=? where id=?";
		final Object[] params={ticketStatus,ticketId};
		jdbcTemplate.update(sql,params);
		return "Ticket status updated";
	}
	return "Enter proper username and password";
}

}
