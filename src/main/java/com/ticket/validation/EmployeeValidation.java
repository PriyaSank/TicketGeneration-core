package com.ticket.validation;

import com.ticket.exception.ValidationException;
import com.ticket.model.EmployeeModel;
import com.ticket.util.ValidationUtil;

public class EmployeeValidation {
	public void validateSave(EmployeeModel emp) throws ValidationException {
		ValidationUtil.isInvalidString(emp.getEmailId(), "EmailId");
		ValidationUtil.isInvalidString(emp.getName(), "Name");
		ValidationUtil.isInvalidString(emp.getPassword(), "Password");
		ValidationUtil.isInvalidNumber(emp.getDept().getId(), "DeptID");
		ValidationUtil.isInvalidNumber(emp.getRole().getId(), "RoleId");
	}

	public void validateUpdatePassword(EmployeeModel emp) throws ValidationException {

		ValidationUtil.isInvalidString(emp.getEmailId(), "EmailId");

		ValidationUtil.isInvalidString(emp.getPassword(), "Password");
	}

	public void validateUpdateActive(EmployeeModel emp) throws ValidationException {
		ValidationUtil.isInvalidString(emp.getEmailId(), "EmailId");
		ValidationUtil.isInvalidBoolean(emp.getActive(), "State");
	}

	public void validateListById(EmployeeModel emp) throws ValidationException {
		ValidationUtil.isInvalidNumber(emp.getId(), "ID");
	}
	public void validateGetPassword(EmployeeModel emp) throws ValidationException {

		ValidationUtil.isInvalidString(emp.getEmailId(), "EmailId");
	}
}
