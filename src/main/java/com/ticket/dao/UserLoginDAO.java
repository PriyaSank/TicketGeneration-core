package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.UserModel;
import com.ticket.util.ConnectionUtil;

public class UserLoginDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public Boolean logIn(String emailId,String pwd )
	{
		UserDAO uDAO=new UserDAO();
		
		UserModel user=uDAO.getPassword(emailId);
				if(user.getPassword().equals(pwd))
				{
					return true;
				}
				return false;
	}
}
