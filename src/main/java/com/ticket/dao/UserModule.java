package com.ticket.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.IssueModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;

public class UserModule {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	UserDAO uDAO=new UserDAOImpl();
	IssueModel iss=new IssueModel();
	TicketDetailsModel ticMod=new TicketDetailsModel();
	TicketDetailsDAO tic=new TicketDetailsDAO();
	UserLoginDAO log=new UserLoginDAO();
//	
//	 public String viewTicket(String emailId) throws DataAccessException, PersistenceException{
//		Integer userId=uDAO.getUserId(emailId);
//		List<Integer> idList=tic.listAllTicketId(userId);
//		for(Integer l:idList){
//			ticMod=tic.listById(l);
//			
//			
//		}
//		return ;
//				
//		
//	}
	
		
 public boolean closeTicket(TicketDetailsModel tick) throws PersistenceException{


	
		int ticId=tick.getId();
		if("CLOSED".equals(tic.getStatus(ticId))){
			
			return false;
		}
			else
			{
		

		
		final String sql2="update tbl_ticket_details set status=? where id=?";
		final Object[] params2={"CLOSED",ticId};
		jdbcTemplate.update(sql2,params2);
		return true;
			}
	
}
public boolean reopenTicket(Integer ticketId) throws PersistenceException{
	

	
		if("CLOSED".equals(tic.getStatus(ticketId).getStatus())){
		return false;
	}
		else
		{
			
			
			final String sql="update tbl_ticket_details set status=? where id=?";
			final Object[] params={"REOPEN",ticketId};
			jdbcTemplate.update(sql,params);
			return true;
		}
	}
	

}
