package com.ticket.model;

import lombok.Data;

@Data
public class EmployeeModel {
	private Integer id;
	private RoleModel role;
	private DepartmentModel dept;
	private String name;
	private String emailId;
	private String password;
	private Boolean active;
}
