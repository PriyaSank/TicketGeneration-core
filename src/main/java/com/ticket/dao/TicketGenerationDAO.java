package com.ticket.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.EmployeeModel;
import com.ticket.model.TicketDetailsModel;
import com.ticket.util.ConnectionUtil;
import com.ticket.util.MailUtil;

public class TicketGenerationDAO {
	UserDAO userDAO=new UserDAOImpl();
	EmployeeDAO emp=new EmployeeDAO();
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	public String ticketGenerate(TicketDetailsModel tic,int userId){
		
		
		final String sql="insert into tbl_ticket_details(user_id,department_id,subject,description,priority_id)values(?,?,?,?,?)";
		final Object[] params={userId,tic.getDept().getId(),tic.getSubject(),tic.getDescription(),tic.getPrior().getId()};
		jdbcTemplate.update(sql,params);

		return "Ticket generated successfully";
	
		
	}
	public String ticketGenerationEmail(TicketDetailsModel tic,String emailId1) throws PersistenceException{
		int userId=userDAO.getUserId(emailId1);

		int deptId=tic.getDept().getId();
		
		final String sql="select id from tbl_ticket_details where user_id=? and subject=? and description=?";
		final Object[] params={userId,tic.getSubject(),tic.getDescription()};
		int ticketId=jdbcTemplate.queryForObject(sql,params,Integer.class);
		String emailId2=emp.getEmailId(deptId);
		
		
		try{
			MailUtil.sendSimpleMail(emailId1,"Ticket Created Sucessfully.Your Ticket id is:",ticketId);
	
		
		  MailUtil.sendSimpleMail(emailId2," New Ticket Created. Ticket id is:",ticketId);
		
	
		
}
	catch(Exception e)
	{
		
	}
	return "Success";
}
}
