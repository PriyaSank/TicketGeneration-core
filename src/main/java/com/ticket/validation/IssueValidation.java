package com.ticket.validation;

import com.ticket.exception.ValidationException;

import com.ticket.model.IssueModel;
import com.ticket.util.ValidationUtil;

public class IssueValidation {
	public void validateSave(IssueModel issue) throws ValidationException {
		ValidationUtil.isInvalidString(issue.getSolution(),"Solution");
		ValidationUtil.isInvalidNumber(issue.getTic().getId(), "TicketId");
		ValidationUtil.isInvalidNumber(issue.getEmp().getId(), "EmpId");
	}

}
