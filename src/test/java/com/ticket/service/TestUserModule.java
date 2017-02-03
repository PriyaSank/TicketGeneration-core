package com.ticket.service;


import com.ticket.exception.ServiceException;
import com.ticket.model.UserModel;

public class TestUserModule {
	
public static void main(String[] args) throws ServiceException {
	UserModel user=new UserModel();
	UserService serv=new UserService();
//	
//	user.setName("");
//	user.setEmailId("");
//	user.setPassword("sha");
//	
//	
//		serv.register(user);
serv.logIn("priyasankan95@gmail.com", "priyas");
		
	}
}

