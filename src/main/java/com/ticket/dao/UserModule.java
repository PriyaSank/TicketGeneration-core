package com.ticket.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.util.ConnectionUtil;

public class UserModule {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	UserDAO uDAO=new UserDAO();
	TicketDetailsDAO tic=new TicketDetailsDAO();
	UserLoginDAO log=new UserLoginDAO();
 public String closeTicket(String emailId,String pwd,Integer ticketId) throws DataAccessException, PersistenceException{


	if(log.logIn(emailId,pwd))
	{
		
		if("CLOSED".equals(tic.getStatus(ticketId).getStatus())){
			
			return "Status is already closed";
		}
			else
			{
		
	Integer id=uDAO.getUserId(emailId);
		
		final String sql2="update tbl_ticket_details set status=? where id=? and user_id=?";
		final Object[] params2={"CLOSED",ticketId,id};
		jdbcTemplate.update(sql2,params2);
		return "Ticket Closed";
			}
	}
	return "Enter proper username and password";
}
public String updateTicket(String emailId,String pwd,Integer ticketId,String ticketStatus) throws DataAccessException, PersistenceException{
	
	if(log.logIn(emailId,pwd))
	{
		if("CLOSED".equals(tic.getStatus(ticketId).getStatus())){
		return "Status is already closed";
	}
		else
		{
			Integer id=uDAO.getUserId(emailId);
			
			final String sql="update tbl_ticket_details set status=? where id=? and user_id=?";
			final Object[] params={ticketStatus,ticketId,id};
			jdbcTemplate.update(sql,params);
			return "Ticket status updated";
		}
	}
	return "Enter proper username and password";
}

}
