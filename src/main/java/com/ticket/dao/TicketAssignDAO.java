package com.ticket.dao;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.TicketAssignModel;

import com.ticket.util.ConnectionUtil;

public class TicketAssignDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public void save(TicketAssignModel ticAss)  throws PersistenceException{
		try{
		final String sql = "insert into tbl_ticket_assignment(ticket_id,to_employee_id,by_employee_id) values (?,?,?)";
		final Object[] params = { ticAss.getTic().getId(),ticAss.getEmp1().getId(),ticAss.getEmp2().getId() };
		jdbcTemplate.update(sql, params);
		}
		catch(DuplicateKeyException e){
			throw new PersistenceException("Email Id already exists",e);
		}
	}
}
