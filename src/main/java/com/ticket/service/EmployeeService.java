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
	
	IssueValidation issVal=new IssueValidation();
	public void logIn(String emailId, String pwd) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			System.out.println(eTask.logIn(emailId, pwd));

		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Try a diff email id", e);
		}
	}

	public void registerEmployee(EmployeeModel emp) throws ServiceException {
		try {
			empVal.validateSave(emp);
			eDAO.save(emp);
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		}
	}

	public void assignTicket(String emailId, String pwd, int ticId) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			if (eTask.logIn(emailId, pwd))

			{

				eVal.validateAssignTicket(ticId);
				eTask.assignTicket(emailId,ticId);
			}
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}

	public List<TicketDetailsModel> viewAssignedTicket(String emailId, String pwd) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			if (eTask.logIn(emailId, pwd))

			{
				Integer empId = eDAO.getId(emailId);
				ValidationUtil.isInvalidNumber(empId, "EmailId");
				return eTask.viewAssignedTicket(empId);
			}
			return null;
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	public void replyToTicket(String emailId, String pwd,IssueModel issue) throws ServiceException {
		try {
			eVal.validateLogin(emailId, pwd);
			if (eTask.logIn(emailId, pwd))

			{
				issVal.validateSave(issue);
				eTask.replyTicket(emailId, issue);
				
				
				}
			
			
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {

			throw new ServiceException("Try a diff email id", e);
		}
	}
	
}
