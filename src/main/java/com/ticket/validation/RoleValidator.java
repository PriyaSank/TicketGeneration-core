package com.ticket.validation;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.ticket.util.ValidationUtil;


import com.ticket.dao.RoleDAO;
import com.ticket.exception.ValidationException;
import com.ticket.model.RoleModel;

public class RoleValidator {
	Logger logger = Logger.getLogger(RoleDAO.class.getName());
	 static final String MSG= "Exception:";
public void validateSave(RoleModel role){
	if (ValidationUtil.isInvalidString(role.getName())) {
		try {

			throw new ValidationException("Role name cant be null");
		} catch (ValidationException e) {
			logger.log(Level.INFO,MSG, e);
		}

	}

}
public void validateId(Integer id) {
	try{
			if (ValidationUtil.isInvalidNumber(id)) {
				throw new ValidationException("Role id cannot be empty");
			}
	}catch (ValidationException e) {
		logger.log(Level.SEVERE,MSG, e);
	}

}



public void validateUpdateActive(RoleModel role) {
	try{
			if ( ValidationUtil.isInvalidNumber(role.getId())) {
				throw new ValidationException("Role id cannot be empty");
			}
			else if (ValidationUtil.isInvalidBoolean(role.getActive()))
			{
				throw new ValidationException("Active state cant be empty");
			}
			
	}catch (ValidationException e) {
		logger.log(Level.SEVERE, "Exception:" + e);
	}

}
public void validateUpdate(RoleModel role) {
	try{
			if ( ValidationUtil.isInvalidNumber(role.getId())) {
				throw new ValidationException("Role id cannot be empty");
			}
			else if (ValidationUtil.isInvalidString(role.getName()))
			{
				throw new ValidationException("Role name cant be empty");
			}
			
	}catch (ValidationException e) {
		logger.log(Level.SEVERE, "Exception:" + e);
	}

}
}
