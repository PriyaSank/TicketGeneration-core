package com.ticket.validation;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.ValidationException;

import com.ticket.util.ConnectionUtil;
import com.ticket.util.ValidationUtil;

public class EmployeeTasksValidation {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public void validateLogin(String emailId,String pwd) throws ValidationException {
	ValidationUtil.isInvalidString(emailId, "InvalidEmailId");
	ValidationUtil.isInvalidString(pwd, "InvalidPassword");
}
	public void validateAssignTicket(int ticId) throws ValidationException {
		
		ValidationUtil.isInvalidNumber(ticId, "TicketId");
	}
	
}