package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.DepartmentModel;
import com.ticket.model.EmployeeModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.model.UserModel;
import com.ticket.util.ConnectionUtil;

public class TicketDetailsDAO  {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();


	
	public List<TicketDetailsModel> listAll() {

		final String sql = "select id,user_id,department_id,subject,description,open_timestamp,employee_id,updated_timestamp,status from tbl_ticket_details";
		return jdbcTemplate.query(sql, (rs, rownum) -> convert(rs));
		
	}
	public List<TicketDetailsModel> listByUserId(int userId) {

		final String sql = "select id,user_id,department_id,subject,description,open_timestamp,employee_id,updated_timestamp,status from tbl_ticket_details where user_id=?";
		final Object[] params={userId};
		return jdbcTemplate.query(sql,params, (rs, rownum) -> convert(rs));
		
	}


	private TicketDetailsModel convert(ResultSet rs) throws SQLException {
		TicketDetailsModel tic=new TicketDetailsModel();
		UserModel user=new UserModel();
		DepartmentModel dep=new DepartmentModel();
		tic.setId(rs.getInt("id"));
		user.setId(rs.getInt("user_id"));
		tic.setUser(user);
		dep.setId(rs.getInt("department_id"));
		tic.setDept(dep);
		tic.setSubject(rs.getString("subject"));
		tic.setDescription(rs.getString("description"));
		tic.setOpenTimestamp(rs.getTimestamp("open_timestamp").toLocalDateTime());
		EmployeeModel emp=new EmployeeModel();
		emp.setId(rs.getInt("employee_id"));
		tic.setEmp(emp);
		tic.setUpdateTimestamp(rs.getTimestamp("updated_timestamp").toLocalDateTime());
		tic.setStatus(rs.getString("status"));
	return tic;	
	}


}
