package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.UserModel;
import com.ticket.util.ConnectionUtil;

public class UserModule {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	UserDAO uDAO=new UserDAO();
 String closeTicket(String emailId,String pwd,String ticketId){
	UserLoginDAO log=new UserLoginDAO();
	if(log.logIn(emailId,pwd))
	{

		
		UserModel user=uDAO.getUserId(emailId);
		
		final String sql2="update tbl_ticket_details set status=? where id=?,user_id=?";
		final Object[] params2={"CLOSED",ticketId,user.getId()};
		jdbcTemplate.update(sql2,params2);
		return "Ticket Closed";
	}
	return "Enter proper username and password";
}
public String updateTicket(String emailId,String pwd,String ticketId,String ticketStatus){
	UserLoginDAO log=new UserLoginDAO();
	if(log.logIn(emailId,pwd))
	{
		UserModel user=uDAO.getUserId(emailId);
		final String sql="update tbl_ticket_details set status=? where id=?,user_id=?";
		final Object[] params={ticketStatus,ticketId,user.getId()};
		jdbcTemplate.update(sql,params);
		return "Ticket status updated";
	}
	return "Enter proper username and password";
}

}
