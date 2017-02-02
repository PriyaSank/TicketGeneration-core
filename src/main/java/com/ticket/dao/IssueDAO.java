package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.IssueModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;

public class IssueDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public List<IssueModel> listAll() {

		final String sql = "select id,ticket_id,solution from tbl_ticket_details";
		return jdbcTemplate.query(sql, (rs, rownum) -> convert(rs));
		
	}
	private IssueModel convert(ResultSet rs) throws SQLException {
		IssueModel issue=new IssueModel();
		TicketDetailsModel tic=new TicketDetailsModel();
		issue.setId(rs.getInt("id"));
		tic.setId(rs.getInt("ticket_id"));
		issue.setTic(tic);
		issue.setSolution(rs.getString("solution"));
		return issue;
		
		
	}
}
