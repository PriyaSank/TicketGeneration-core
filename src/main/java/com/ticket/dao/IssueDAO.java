package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.EmployeeModel;
import com.ticket.model.IssueModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;

public class IssueDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public void save(IssueModel issue){
		final String sql="insert into tbl_issue_solution(ticket_id,employee_id,solution) values(?,?,?)";
		final Object[] params={issue.getTic().getId(),issue.getEmp().getId(),issue.getSolution()};
		jdbcTemplate.update(sql,params);
	}
	public List<IssueModel> listAll() {

		final String sql = "select id,ticket_id,employee_id,solution from tbl_issue_solutions";
		return jdbcTemplate.query(sql, (rs, rownum) -> convert(rs));
		
	}
	private IssueModel convert(ResultSet rs) throws SQLException {
		IssueModel issue=new IssueModel();
		TicketDetailsModel tic=new TicketDetailsModel();
		EmployeeModel emp=new EmployeeModel();
		issue.setId(rs.getInt("id"));
		tic.setId(rs.getInt("ticket_id"));
		issue.setTic(tic);
		emp.setId(rs.getInt("employee_id"));
		issue.setEmp(emp);
		issue.setSolution(rs.getString("solution"));
		return issue;
		
		
	}
}
