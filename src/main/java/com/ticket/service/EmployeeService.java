package com.ticket.service;

import java.util.List;

import com.ticket.dao.EmployeeDAO;
import com.ticket.dao.EmployeeTasks;
import com.ticket.dao.TicketDetailsDAO;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidationException;
import com.ticket.model.EmployeeModel;
import com.ticket.model.IssueModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ValidationUtil;
import com.ticket.validation.EmployeeTasksValidation;
import com.ticket.validation.EmployeeValidation;
import com.ticket.validation.IssueValidation;

public class EmployeeService {
	
	EmployeeTasks eTask = new EmployeeTasks();
	EmployeeTasksValidation eVal = new EmployeeTasksValidation();
	EmployeeDAO eDAO = new EmployeeDAO();
	EmployeeValidation empVal = new EmployeeValidation();
	
	IssueValidation issVal=new IssueValidation();
	TicketDetailsModel tic=new TicketDetailsModel();
	TicketDetailsDAO ticDAO=new TicketDetailsDAO();
	public boolean logIn(String emailId, String pwd) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			if(eTask.logIn(emailId, pwd))
			{
			return true;
			}
			return false;
		}
		
		catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {
			throw new ServiceException("email id is not registered", e);
		}
	}

	public String registerEmployee(EmployeeModel emp) throws ServiceException {
		try {
			empVal.validateSave(emp);
			eDAO.save(emp);
			return "Registration successful";
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		}
	}
	public void deleteTicket(int ticId) throws ServiceException {
		try {
		
				eVal.validateAssignTicket(ticId);
				   ticDAO.deleteTicket(ticId);
				
			
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		}
	}
	public Boolean assignTicket(String emailId,int toEmpId, int ticId) throws ServiceException {
		try {
			int emp1=eDAO.getId(emailId);
			if(eTask.assignTicket(emailId,emp1,toEmpId,ticId))
			{
				return true;
			}
		} catch (PersistenceException e) {
		
		}
		return false;
	}
	public Boolean reassignTicket(int empl,int toEmpId, int ticId) throws ServiceException {
		try {
				eVal.validateAssignTicket(ticId);
				if(eTask.reassignTicket(empl,toEmpId,ticId))
				{
					return true;
				}
				return false;
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	public List<TicketDetailsModel> viewAssignedTicket(String emailId) throws ServiceException {
		try {
			
				Integer empId = eDAO.getId(emailId);
				ValidationUtil.isInvalidNumber(empId, "EmailId");
				return eTask.viewAssignedTicket(empId);
			
			
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	public List<TicketDetailsModel> viewTicketByDepartment(int depId) throws ServiceException {
		try {
			
			
				ValidationUtil.isInvalidNumber(depId, "DepId");
				return ticDAO.listByDepartmentId(depId);
			
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public void replyToTicket(IssueModel issue) throws ServiceException {
		try {
			
				issVal.validateSave(issue);
				eTask.replyTicket(issue);
		
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	
}
