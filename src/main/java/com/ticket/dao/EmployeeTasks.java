package com.ticket.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.EmployeeModel;
import com.ticket.model.IssueModel;
import com.ticket.model.TicketAssignModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;

public class EmployeeTasks {
	int empId;
	int ticId;
	int roleId;
	int deptId;
	EmployeeModel emp1=new EmployeeModel();
	EmployeeModel emp2=new EmployeeModel();
	TicketDetailsModel tic=new TicketDetailsModel();
	EmployeeDAO eDAO=new EmployeeDAO();
	TicketDetailsDAO ticDAO=new TicketDetailsDAO();
	IssueDAO iss=new IssueDAO();
	RoleDAO roleDAO=new RoleDAO();
	TicketAssignDAO ticAssDAO=new TicketAssignDAO();
	TicketAssignModel ticAss=new TicketAssignModel();
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
	public String assignTicket(String emailId,int toEmpId,int ticId) throws PersistenceException{
		empId=eDAO.getId(emailId);
		roleId=eDAO.getRoleId(emailId);
		deptId=eDAO.getDepartmentId(emailId);
		if((ticDAO.getDeptId(ticId)==eDAO.getDepartmentIdByEmpId(toEmpId))&&(deptId==ticDAO.getDeptId(ticId))&&(roleId==roleDAO.getRoleId("Admin")))
		{
		final String sql="update tbl_ticket_details set employee_id=? where id=?";
		final Object[] params={toEmpId,ticId};
		jdbcTemplate.update(sql,params);
		tic.setId(ticId);
		emp1.setId(toEmpId);
		emp2.setId(empId);
		ticAss.setEmp1(emp1);
		ticAss.setEmp2(emp2);
		ticAss.setTic(tic);
		
		ticAssDAO.save(ticAss);
		return "Ticket assigned successfully";
		}
		return "Unsuccessful Assignment";
	}
	public String reassignTicket(String emailId,int emp2Id,int ticId) throws PersistenceException{
		empId=eDAO.getId(emailId);
		if((ticDAO.checkEmployeeTicket(empId, ticId))&&(ticDAO.getDeptId(ticId)==eDAO.getDepartmentIdByEmpId(emp2Id))){
			
		final String sql="update tbl_ticket_details set employee_id=? where id=?";
		final Object[] params={emp2Id,ticId};
		jdbcTemplate.update(sql,params);
		
		tic.setId(ticId);
		emp1.setId(emp2Id);
		emp2.setId(empId);
		ticAss.setEmp1(emp1);
		ticAss.setEmp2(emp2);
		ticAss.setTic(tic);
		
		ticAssDAO.save(ticAss);
		return "Ticket reassigned successfully";
		}
		return "Unsuccessful Assignment";
	}
	public void deleteTicket(String emailId,int ticId) throws PersistenceException{
		roleId=eDAO.getRoleId(emailId);
		deptId=eDAO.getDepartmentId(emailId);
		if((deptId==ticDAO.getDeptId(ticId))&&(roleId==roleDAO.getRoleId("Admin")))
		{
	
			ticDAO.deleteTicket(ticId);	
		
		}
		
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
