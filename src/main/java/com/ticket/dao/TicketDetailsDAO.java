package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.DepartmentModel;
import com.ticket.model.EmployeeModel;
import com.ticket.model.IssueModel;
import com.ticket.model.PriorityModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.model.UserModel;
import com.ticket.util.ConnectionUtil;

public class TicketDetailsDAO  {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public Boolean checkEmployeeTicket(int empId,int ticId){
		final String sql="select 1 from tbl_ticket_details where employee_id=? and id=?";
		final Object[] params={empId,ticId};
		
		return jdbcTemplate.queryForObject(sql,params,Boolean.class);
		
	}
	public void updateTicketStatus(TicketDetailsModel tic){
		final String sql="update tbl_ticket_details set status=? where id=?";
		final Object[] params={tic.getStatus(),tic.getId()};
		
		jdbcTemplate.update(sql,params);
		
	}
	public void deleteTicket(int id){
		
		final String sql="delete from tbl_ticket_details where id=?";
		final Object[] params={id};
		
		jdbcTemplate.update(sql,params);
		
	}
	public int getDeptId(int id) {
		final String sql="select department_id from tbl_ticket_details and id=?";
		final Object[] params={id};
		
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
		
	}
	public List<TicketDetailsModel> listAll() {

		final String sql = "select id,user_id,department_id,subject,description,priority_id,open_timestamp,employee_id,updated_timestamp,status from tbl_ticket_details";
		return jdbcTemplate.query(sql, (rs, rownum) -> convert(rs));
		
	}
	public List<TicketDetailsModel> listByUserId(int userId) {

		final String sql = "select id,user_id,department_id,subject,description,priority_id,open_timestamp,employee_id,updated_timestamp,status from tbl_ticket_details where user_id=?";
		final Object[] params={userId};
		return jdbcTemplate.query(sql,params, (rs, rownum) -> convert(rs));
		
	}
	public List<TicketDetailsModel> listByEmployeeId(int empId) {

		final String sql = "select id,user_id,department_id,subject,description,priority_id,open_timestamp,employee_id,updated_timestamp,status from tbl_ticket_details where employee_id=?";
		final Object[] params={empId};
		return jdbcTemplate.query(sql,params, (rs, rownum) -> convert(rs));
		
	}

public TicketDetailsModel getStatus(int ticId){
	final String sql="select status from tbl_ticket_details where id=?";
	final Object[] params={ticId};
	return jdbcTemplate.queryForObject(sql,params,(rs,rowNo)->{
		TicketDetailsModel tic=new TicketDetailsModel();
		tic.setStatus(rs.getString("status"));
		return tic; 
	}
);
	
}
	private TicketDetailsModel convert(ResultSet rs) throws SQLException {
		TicketDetailsModel tic=new TicketDetailsModel();
		UserModel user=new UserModel();
		DepartmentModel dep=new DepartmentModel();
		PriorityModel pri=new PriorityModel();
		tic.setId(rs.getInt("id"));
		user.setId(rs.getInt("user_id"));
		tic.setUser(user);
		dep.setId(rs.getInt("department_id"));
		tic.setDept(dep);
		tic.setSubject(rs.getString("subject"));
		tic.setDescription(rs.getString("description"));
		pri.setId(rs.getInt("priority_id"));
		tic.setPrior(pri);
		tic.setOpenTimestamp(rs.getTimestamp("open_timestamp").toLocalDateTime());
		EmployeeModel emp=new EmployeeModel();
		emp.setId(rs.getInt("employee_id"));
		tic.setEmp(emp);
		tic.setUpdateTimestamp(rs.getTimestamp("updated_timestamp").toLocalDateTime());
		tic.setStatus(rs.getString("status"));
	return tic;	
	}


}
