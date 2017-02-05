package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.RoleModel;
import com.ticket.util.ConnectionUtil;

public class RoleDAO implements InterfaceDAO<RoleModel> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	@Override
	public void save(RoleModel role) {
		final String sql = "insert into tbl_roles(name) values (?)";
		final Object[] params = { role.getName() };
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public void update(RoleModel role) {
		final String sql = "update tbl_roles set name=? where id=?";
		final Object[] params = { role.getName(),role.getId() };
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public void updateAsInactive(RoleModel role) {
		final String sql = "update tbl_roles set active=? where id=?";
		final Object[] params = { role.getActive(),role.getId() };
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public List<RoleModel> listAll() {

		final String sql = "select id,name,active from tbl_roles";
		return jdbcTemplate.query(sql, (rs, rownum) -> convert(rs));
	}
	public int getRoleId(String name) {
		final String sql="select id from tbl_ticket_details where name=?";
		final Object[] params={name};
		
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
		
	}

	private RoleModel convert(ResultSet rs) throws SQLException {
		RoleModel role=new RoleModel();
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		role.setActive(rs.getBoolean("active"));
		return role;
	}

	@Override
	public RoleModel listById(int id) {

		final String sql = "select id,name,active from tbl_roles where id=?";
		final Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rownum) -> convert(rs));
	}

}
