package com.ticket.validation;

import com.ticket.exception.ValidationException;
import com.ticket.model.UserModel;
import com.ticket.util.ValidationUtil;

public class UserValidation {

	public void validateSave(UserModel user) throws ValidationException {
		ValidationUtil.isInvalidString(user.getEmailId(), "InvalidEmailId");
		ValidationUtil.isInvalidString(user.getName(), "InvalidName");
		ValidationUtil.isInvalidString(user.getPassword(), "InvalidPassword");
	}

	public void validateUpdatePassword(UserModel user) throws ValidationException {

		ValidationUtil.isInvalidString(user.getEmailId(), "InvalidEmailId");

		ValidationUtil.isInvalidString(user.getPassword(), "InvalidPassword");
	}

	public void validateUpdateActive(UserModel user) throws ValidationException {
		ValidationUtil.isInvalidString(user.getEmailId(), "InvalidEmailId");
		ValidationUtil.isInvalidBoolean(user.getActive(), "InvalidState");
	}

	public void validateListById(UserModel user) throws ValidationException {
		ValidationUtil.isInvalidNumber(user.getId(), "InvalidID");
	}

}