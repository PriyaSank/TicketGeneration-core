package com.ticket.dao;

import java.util.List;

import com.ticket.exception.PersistenceException;
import com.ticket.model.UserModel;

public interface UserDAO {

	void save(UserModel user) throws PersistenceException;

	void update(UserModel user);

	void updateAsInactive(UserModel user);

	String getPassword(String emailId) throws PersistenceException;

	Integer getUserId(String emailId);

	List<UserModel> listAll();

	UserModel listById(int id);

}