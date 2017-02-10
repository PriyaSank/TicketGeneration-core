package com.ticket.service;

import java.util.List;

import com.ticket.dao.EmployeeDAO;
import com.ticket.dao.EmployeeTasks;
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
	EmployeeModel emp=new EmployeeModel();
	IssueValidation issVal=new IssueValidation();
	TicketDetailsModel tic=new TicketDetailsModel();
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
	public void deleteTicket(String emailId, String pwd, int ticId) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			if (eTask.logIn(emailId, pwd))

			{
				eVal.validateAssignTicket(ticId);
				eTask.deleteTicket(emailId, ticId);
				
			}
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	public String assignTicket(String emailId, String pwd,int toEmpId, int ticId) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			if (eTask.logIn(emailId, pwd))

			{

				eVal.validateAssignTicket(ticId);
				return eTask.assignTicket(emailId,toEmpId,ticId);
			}
			return null;
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	public String reassignTicket(String emailId, String pwd,int toEmpId, int ticId) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			if (eTask.logIn(emailId, pwd))

			{

				eVal.validateAssignTicket(ticId);
				return eTask.reassignTicket(emailId,toEmpId,ticId);
				
			}
			return null;
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
	public void replyToTicket(int ticketId,int empId,String solution) throws ServiceException {
		try {
			IssueModel issue=new IssueModel();
			issue.setEmp(emp);
			emp.setId(empId);
			issue.setSolution(solution);
			issue.setTic(tic);
			tic.setId(ticketId);
				issVal.validateSave(issue);
				eTask.replyTicket(issue);
		
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	
}
