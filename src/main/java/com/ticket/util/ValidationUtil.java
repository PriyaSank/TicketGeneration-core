package com.ticket.util;

import com.ticket.exception.ValidationException;

public class ValidationUtil {

	public static void isInvalidString(String name,String msg) throws ValidationException {
		if (name == null || "".equals(name.trim())) {

			throw new ValidationException("Invalid"+msg);

		}

	}

	public static void isInvalidNumber(Integer no,String string) throws ValidationException {
		if (no == null || no <= 0) {

			throw new ValidationException("Invalid"+string);
		}
	}

	public static void isInvalidBoolean(Boolean boolVar,String msg) throws ValidationException {
		if (boolVar == null) {

			throw new ValidationException("Invalid"+msg);

		}
	}
}
