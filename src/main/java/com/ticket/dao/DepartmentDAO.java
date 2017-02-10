package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.DepartmentModel;
import com.ticket.util.ConnectionUtil;

public class DepartmentDAO{
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	
	public void save(DepartmentModel dept) {
		final String sql = "insert into tbl_departments(name) values (?)";
		final Object[] params = { dept.getName() };
		jdbcTemplate.update(sql, params);
		
	}

	
	public void update(DepartmentModel dept) {
		final String sql = "update tbl_departments set name=? where id=?";
		final Object[] params = { dept.getName(),dept.getId() };
		jdbcTemplate.update(sql, params);
		
	}


	public void updateAsInactive(DepartmentModel dept) {
		final String sql = "update tbl_departments set active=? where id=?";
		final Object[] params = { dept.getActive(),dept.getId() };
		jdbcTemplate.update(sql, params);
		
	}
	public Integer getDeptId(String depName)
	{
		final String sql="select id from tbl_departments where name=?";
		final Object[] params={depName};
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
	}
	
	public List<DepartmentModel> listAll() {

		final String sql = "select id,name,active from tbl_departments";
		return jdbcTemplate.query(sql, (rs, rownum) -> convert(rs));
	}

	private DepartmentModel convert(ResultSet rs) throws SQLException {
		DepartmentModel dep=new DepartmentModel();
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("name"));
		dep.setActive(rs.getBoolean("active"));
		return dep;
	}

	
	public DepartmentModel listById(int id) {

		final String sql = "select id,name,active from tbl_departments where id=?";
		final Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rownum) -> convert(rs));
	}

}
