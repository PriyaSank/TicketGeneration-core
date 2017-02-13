package com.ticket.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.EmployeeModel;
import com.ticket.model.IssueModel;
import com.ticket.model.TicketAssignModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;
import com.ticket.util.MailUtil;
import com.ticket.dao.GetEmail;
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
	public Boolean assignTicket(String emailId,int emp,int toEmpId,int ticId) throws PersistenceException{
		
		
		deptId=eDAO.getDepartmentId(emailId);
		if((ticDAO.getDeptId(ticId)==eDAO.getDepartmentIdByEmpId(toEmpId))&&(deptId==ticDAO.getDeptId(ticId)))
		{
		final String sql="update tbl_ticket_details set employee_id=?,updated_timestamp=now() where id=?";
		final Object[] params={toEmpId,ticId};
		jdbcTemplate.update(sql,params);
		tic.setId(ticId);
		emp1.setId(toEmpId);
		emp2.setId(emp);
		ticAss.setEmp1(emp1);
		ticAss.setEmp2(emp2);
		ticAss.setTic(tic);
		
		ticAssDAO.save(ticAss);
		GetEmail find=new GetEmail();
		String emailId1=find.getEmployeeEmail(ticId);
		try{
			MailUtil.sendSimpleMail(emailId1,"The assigned ticket id is:",ticId);
		
}
		catch(Exception e)
		{
	throw new PersistenceException("Mail not sent",e);
		}
		return true;
		}
		return false;
	}
	public Boolean reassignTicket(int empl,int emp2Id,int ticId) throws PersistenceException{
		
		if((ticDAO.checkEmployeeTicket(empl,ticId))&&(ticDAO.getDeptId(ticId)==eDAO.getDepartmentIdByEmpId(emp2Id))){
			
		final String sql="update tbl_ticket_details set employee_id=?,updated_timestamp=now() where id=?";
		final Object[] params={emp2Id,ticId};
		jdbcTemplate.update(sql,params);
		
		tic.setId(ticId);
		emp1.setId(emp2Id);
		emp2.setId(empl);
		ticAss.setEmp1(emp1);
		ticAss.setEmp2(emp2);
		ticAss.setTic(tic); 
		
		ticAssDAO.save(ticAss);
		GetEmail findEmp=new GetEmail();
		String emailId1=findEmp.getEmployeeEmail(ticId);
		try{
			MailUtil.sendSimpleMail(emailId1,"The assigned ticket id is:",ticId);
		
}
		catch(Exception e)
		{
	throw new PersistenceException("Mail not sent",e);
		}
		return true;
		}
		return false;
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
	
	public void replyTicket(IssueModel issue) throws PersistenceException{
	
		GetEmail finds=new GetEmail();
			iss.save(issue);
			
			tic.setStatus("RESOLVED");
			tic.setId(issue.getTic().getId());
			
			System.out.println(ticDAO.updateTicketStatus(tic));
			
			
			String emailId1=finds.getUserEmail(issue.getTic().getId());
			String emailId2=finds.getAdminEmail(issue.getTic().getId());
			try{
				MailUtil.sendSimpleMail(emailId1,"The solution is:"+issue.getSolution()+"The ticket id is:",issue.getTic().getId());
		
			
			  MailUtil.sendSimpleMail(emailId2,"The solution is:"+issue.getSolution()+"The ticket id is:",issue.getTic().getId());
			
			
			
	}
			catch(Exception e)
			{
		throw new PersistenceException("Mail not sent",e);
			}
		
	}
	
	
}
