package com.ticket.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.IssueModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;

public class EmployeeTasks {
	int empId;
	int ticId;
	TicketDetailsModel tic=new TicketDetailsModel();
	EmployeeDAO eDAO=new EmployeeDAO();
	TicketDetailsDAO ticDAO=new TicketDetailsDAO();
	IssueDAO iss=new IssueDAO();
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public Boolean logIn(String emailId,String pwd ) throws PersistenceException
	{
		
		
		String check=eDAO.getPassword(emailId);
				if(check.equals(pwd))
				{
					return true;
				}
				return false;
	}
	public void assignTicket(String emailId,int ticId) throws PersistenceException{
		empId=eDAO.getId(emailId);
		final String sql="update tbl_ticket_details set employee_id=?,updated_timestamp=now() where id=?";
		final Object[] params={empId,ticId};
		jdbcTemplate.update(sql,params);
	}
	public void reassignTicket(String emailId,int emp2Id,int ticId) throws PersistenceException{
		empId=eDAO.getId(emailId);
		if(ticDAO.checkEmployeeTicket(empId, ticId)){
		final String sql="update tbl_ticket_details set employee_id=?,updated_timestamp=now() where id=?";
		final Object[] params={emp2Id,ticId};
		jdbcTemplate.update(sql,params);}
	}
	public List<TicketDetailsModel> viewAssignedTicket(int empId) throws PersistenceException{
		
		return ticDAO.listByEmployeeId(empId);
	}
public void replyTicket(String emailId,IssueModel issue) throws PersistenceException{
		empId=eDAO.getId(emailId);
		ticId=issue.getTic().getId();
		if(ticDAO.checkEmployeeTicket(empId, ticId)){
			iss.save(issue);
			tic.setStatus("RESOLVED");
			tic.setId(issue.getTic().getId());
			ticDAO.updateTicketStatus(tic);
		}
		
	}
	
}
