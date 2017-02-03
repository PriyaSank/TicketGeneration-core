package com.ticket.service;

import com.ticket.dao.TicketGenerationDAO;
import com.ticket.dao.UserDAO;
import com.ticket.dao.UserLoginDAO;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidationException;
import com.ticket.model.TicketDetailsModel;
import com.ticket.model.UserModel;
import com.ticket.validation.LoginValidation;


public class UserService {
	UserDAO userDAO=new UserDAO();
	com.ticket.validation.UserValidation val=new com.ticket.validation.UserValidation();
	LoginValidation logval=new LoginValidation();
	UserLoginDAO uLog=new UserLoginDAO();
	TicketGenerationDAO ticGen=new TicketGenerationDAO();
public void register(UserModel user) throws ServiceException {
	
	try{
		val.validateSave(user);
		userDAO.save(user);
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}

public void logIn(String emailId, String pwd) throws ServiceException{
	try{
	logval.validateLogin(emailId, pwd);
	System.out.println(uLog.logIn(emailId, pwd));	
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}
public void ticketGeneration(TicketDetailsModel tic,String emailId, String pwd) throws ServiceException{
	try{
	logval.validateLogin(emailId, pwd);
	System.out.println(uLog.logIn(emailId, pwd));
	ticGen.ticketGenerate(tic);
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}
}
