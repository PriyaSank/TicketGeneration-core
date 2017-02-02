package com.ticket.dao;

import java.util.List;

public interface InterfaceDAO<T> {
	void save(T t);
	void update(T t);
	void updateAsInactive(T t);
	List<T> listAll();
	T listById(int id);
}
