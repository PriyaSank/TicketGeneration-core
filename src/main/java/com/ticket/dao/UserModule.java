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
 public String closeTicket(String emailId,Integer ticketId) throws DataAccessException, PersistenceException{


	
		
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
public String updateTicket(String emailId,Integer ticketId,String ticketStatus) throws DataAccessException, PersistenceException{
	

	
		if("CLOSED".equals(tic.getStatus(ticketId).getStatus())){
		return "Ticket is already closed";
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
	

}
