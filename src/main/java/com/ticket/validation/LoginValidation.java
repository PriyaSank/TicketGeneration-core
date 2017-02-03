package com.ticket.validation;

import com.ticket.exception.ValidationException;

import com.ticket.util.ValidationUtil;

public class LoginValidation {
	public void validateLogin(String emailId,String pwd) throws ValidationException {
		ValidationUtil.isInvalidString(emailId, "InvalidEmailId");
		ValidationUtil.isInvalidString(pwd, "InvalidPassword");
	}
}
