package com.ticket.validation;

import com.ticket.exception.ValidationException;
import com.ticket.model.DepartmentModel;

import com.ticket.util.ValidationUtil;

public class DepartmentValidation {
	public void validateSave(DepartmentModel dept) throws ValidationException {
		ValidationUtil.isInvalidString(dept.getName(), "Name");

	}

	public void validateUpdate(DepartmentModel dept) throws ValidationException {
		ValidationUtil.isInvalidNumber(dept.getId(), "Id");
		ValidationUtil.isInvalidString(dept.getName(), "Name");
	}

	public void validateUpdateActive(DepartmentModel dept) throws ValidationException {
		ValidationUtil.isInvalidString(dept.getName(), "Name");
		ValidationUtil.isInvalidBoolean(dept.getActive(), "Active state");
	}

	public void validateListById(int id) throws ValidationException {
		ValidationUtil.isInvalidNumber(id, "Id");

	}
}
