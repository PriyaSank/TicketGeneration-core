package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.util.ConnectionUtil;

public class UserLoginDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public Boolean logIn(String emailId,String pwd ) throws PersistenceException
	{
		UserDAO uDAO=new UserDAO();
		
		String check=uDAO.getPassword(emailId);
				if(check.equals(pwd))
				{
					return true;
				}
				return false;
	}
}
