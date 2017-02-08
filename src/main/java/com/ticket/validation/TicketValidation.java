package com.ticket.validation;

import com.ticket.exception.ValidationException;

import com.ticket.util.ValidationUtil;

public class TicketValidation {
	public void validateTicketGenertaion(String sub, String desc) throws ValidationException {
		ValidationUtil.isInvalidString(sub, "Subject");
		ValidationUtil.isInvalidString(desc, "Description");
	
	}
}
