package com.ticket.service;

import java.util.List;

import com.ticket.dao.DepartmentDAO;
import com.ticket.dao.PriorityDAO;
import com.ticket.dao.TicketDetailsDAO;
import com.ticket.dao.TicketGenerationDAO;
import com.ticket.dao.UserDAO;
import com.ticket.dao.UserLoginDAO;
import com.ticket.dao.UserModule;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidationException;
import com.ticket.model.DepartmentModel;
import com.ticket.model.PriorityModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.model.UserModel;
import com.ticket.validation.LoginValidation;
import com.ticket.validation.TicketValidation;
import com.ticket.validation.UserValidation;

public class UserService {
	TicketValidation ticVal = new TicketValidation();
	UserDAO userDAO = new UserDAO();
	UserModel user = new UserModel();
	PriorityDAO priorDAO = new PriorityDAO();
	DepartmentDAO depDAO = new DepartmentDAO();
	PriorityModel prior = new PriorityModel();
	DepartmentModel dept = new DepartmentModel();
	com.ticket.validation.UserValidation val = new com.ticket.validation.UserValidation();
	LoginValidation logval = new LoginValidation();
	UserLoginDAO uLog = new UserLoginDAO();
	UserValidation userval = new UserValidation();
	TicketGenerationDAO ticGen = new TicketGenerationDAO();
	TicketDetailsDAO ticDAO = new TicketDetailsDAO();
	TicketDetailsModel tic = new TicketDetailsModel();
	UserModule userMod = new UserModule();
	int userId;
	int deptId;
	int priorId;

	public String register(UserModel user) throws ServiceException {

		try {
			val.validateSave(user);
			userDAO.save(user);
			return "Registration is successful";
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Try a diff email id", e);
		}
	}

	public Boolean logIn(String emailId, String pwd) throws ServiceException {
		try {
			logval.validateLogin(emailId, pwd);

			if (uLog.logIn(emailId, pwd)) {
				return true;
			}
			return false;
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Try a diff email id", e);
		}
	}

	public Boolean ticketGeneration(String emailId, String sub, String desc, String depart, String prio)
			throws ServiceException {
		try {
			priorId = priorDAO.getPriorityId(prio);
			userId = userDAO.getUserId(emailId);
			deptId = depDAO.getDeptId(depart);
			user.setId(userId);
			tic.setUser(user);
			dept.setId(deptId);
			prior.setId(priorId);
			tic.setDept(dept);
			ticVal.validateTicketGenertaion(sub, desc);

			tic.setDescription(desc);
			tic.setSubject(sub);
			tic.setPrior(prior);
			ticGen.ticketGenerate(tic, userId);
			try {
				System.out.println(tic);
				ticGen.ticketGenerationEmail(tic, emailId);
			} catch (PersistenceException e) {

				e.printStackTrace();
			}
			return true;

		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		}

	}

	public String updateTicketStatus(String emailId, String pwd, int ticketId, String ticketStatus)
			throws ServiceException {
		try {
			logval.validateLogin(emailId, pwd);
			if (uLog.logIn(emailId, pwd)) {
				userval.validateId(ticketId);
				return userMod.updateTicket(emailId, ticketId, ticketStatus);
			}
			return "Login unsuccessful";
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Try a diff email id", e);
		}
	}

	public String closeTicket(String emailId, String pwd, int ticketId) throws ServiceException {
		try {
			logval.validateLogin(emailId, pwd);
			if (uLog.logIn(emailId, pwd)) {
				userval.validateId(ticketId);
				return userMod.closeTicket(emailId, ticketId);
			}
			return "Login unsuccessful";
		} catch (ValidationException e) {
			throw new ServiceException("Enter proper inputs", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Try a diff email id", e);
		}
	}

	public List<TicketDetailsModel> viewTicket(String emailId) throws ServiceException {
		try {

		 userId=userDAO.getUserId(emailId);
	
			return ticDAO.listByEmployeeId(userId);
		}

		catch (PersistenceException e) {
			throw new ServiceException("Try a diff email id", e);
		}

	}
}
