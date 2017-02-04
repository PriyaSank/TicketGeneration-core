package com.ticket.service;

import com.ticket.dao.TicketDetailsDAO;
import com.ticket.dao.TicketGenerationDAO;
import com.ticket.dao.UserDAO;
import com.ticket.dao.UserLoginDAO;
import com.ticket.dao.UserModule;
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
	TicketDetailsDAO ticDAO=new TicketDetailsDAO();
	UserModule userMod=new UserModule();
public String register(UserModel user) throws ServiceException {
	
	try{
		val.validateSave(user);
		userDAO.save(user);
		return "Registration is successful";
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}

public String logIn(String emailId, String pwd) throws ServiceException{
	try{
	logval.validateLogin(emailId, pwd);
	
	if(uLog.logIn(emailId, pwd))
	{
		return "Login is successful";
	}
	return "Login unsuccessful";
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}
public String ticketGeneration(TicketDetailsModel tic,String emailId, String pwd) throws ServiceException{
	try{
	logval.validateLogin(emailId, pwd);
	if(uLog.logIn(emailId, pwd))
	{
	ticGen.ticketGenerate(tic);
	return "Ticket Generation is successful";
	}
	return "Ticket Generation is unsuccessful";
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
	
}
public String updateTicketStatus(String emailId, String pwd,int ticketId,String ticketStatus) throws ServiceException{
	try{
	logval.validateLogin(emailId, pwd);
	if(uLog.logIn(emailId, pwd)){
	return userMod.updateTicket(emailId, pwd, ticketId, ticketStatus);
	}
	return "Login unsuccessful";
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}
public String closeTicket(String emailId, String pwd,int ticketId) throws ServiceException{
	try{
	logval.validateLogin(emailId, pwd);
	if(uLog.logIn(emailId, pwd)){
	return userMod.closeTicket(emailId, pwd, ticketId);
	}
	return "Login unsuccessful";
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}
public String viewTicket(String emailId, String pwd) throws ServiceException{
	try{
	logval.validateLogin(emailId, pwd);
	if(uLog.logIn(emailId, pwd)){
	int userId=userDAO.getUserId(emailId);
	ticDAO.listByUserId(userId);
	}
	return "Login unsuccessful";
	}
	catch(ValidationException e){
	throw new ServiceException ("Enter proper inputs",e);	
	}
	catch(PersistenceException e){
		throw new ServiceException ("Try a diff email id",e);	
		}
}
}
