package com.ticket.validation;

import com.ticket.util.ValidationUtil;

import com.ticket.exception.ValidationException;
import com.ticket.model.RoleModel;

public class RoleValidator {

	public void validateSave(RoleModel role) throws ValidationException {
		ValidationUtil.isInvalidString(role.getName(), "Name");

	}

	public void validateUpdate(RoleModel role) throws ValidationException {
		ValidationUtil.isInvalidNumber(role.getId(), "Id");
		ValidationUtil.isInvalidString(role.getName(), "Name");
	}

	public void validateUpdateActive(RoleModel role) throws ValidationException {
		ValidationUtil.isInvalidString(role.getName(), "Name");
		ValidationUtil.isInvalidBoolean(role.getActive(), "Active state");
	}

	public void validateListById(int id) throws ValidationException {
		ValidationUtil.isInvalidNumber(id, "Id");

	}

}
