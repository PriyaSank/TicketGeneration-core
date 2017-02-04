package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.DepartmentModel;
import com.ticket.model.EmployeeModel;
import com.ticket.model.RoleModel;
import com.ticket.util.ConnectionUtil;

public class EmployeeDAO implements InterfaceDAO<EmployeeModel> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(EmployeeModel emp) {
		final String sql = "insert into tbl_employees(name,role_id,email_id,password,department_id) values (?,?,?,?,?)";
		final Object[] params = { emp.getName(), emp.getRole().getId(), emp.getEmailId(), emp.getPassword(),
				emp.getDept().getId() };
		jdbcTemplate.update(sql, params);
	}
	public String getPassword(String emailId) throws PersistenceException
	{
		try{
			
		
		final String sql="select password from tbl_employees where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,String.class);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new PersistenceException("Invalid Id",e);
		}
		
	}
	public Integer getId(String emailId) throws PersistenceException
	{
		try{
			
		
		final String sql="select id from tbl_employees where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new PersistenceException("Invalid MailId",e);
		}
		
	}
	public Integer getRoleId(String emailId) throws PersistenceException
	{
		try{
			
		
		final String sql="select role_id from tbl_employees where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new PersistenceException("Invalid MailId",e);
		}
		
	}
	public Integer getDepartmentId(String emailId) throws PersistenceException
	{
		try{
			
		
		final String sql="select department_id from tbl_employees where email_id=?";
		final Object[] params={emailId};
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new PersistenceException("Invalid MailId",e);
		}
		
	}
	@Override
	public void update(EmployeeModel emp) {
		final String sql = "update tbl_employees set password=? where email_id=?";
		final Object[] params = { emp.getPassword(), emp.getEmailId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(EmployeeModel emp) {

		final String sql = "update tbl_employees set active=? where email_id=?";
		final Object[] params = { emp.getActive(), emp.getEmailId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<EmployeeModel> listAll() {

		final String sql = "select id,role_id,department_id,name,email_id,password,active from tbl_employees";
		return jdbcTemplate.query(sql, (rs, rownum) -> covert(rs));
	}

	private EmployeeModel covert(ResultSet rs) throws SQLException {

		EmployeeModel emp = new EmployeeModel();
		DepartmentModel dept = new DepartmentModel();
		RoleModel role = new RoleModel();
		emp.setId(rs.getInt("id"));
		emp.setDept(dept);
		dept.setId(rs.getInt("department_id"));
		emp.setRole(role);
		role.setId(rs.getInt("role_id"));
		emp.setName(rs.getString("name"));

		emp.setEmailId(rs.getString("email_id"));

		emp.setPassword(rs.getString("password"));
		emp.setActive(rs.getBoolean("active"));
		return emp;

	}

	@Override
	public EmployeeModel listById(int id) {

		final String sql = "select id,role_id,department_id,name,email_id,password,active from tbl_employees where id=?";
		final Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rownum) -> covert(rs));
	}

}
