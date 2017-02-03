package com.ticket.model;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class TicketDetailsModel {
	private Integer id;
	private UserModel user;
	private DepartmentModel dept;
	private String subject;
	private String description;
	private PriorityModel prior;
	private LocalDateTime openTimestamp;
	private EmployeeModel emp;
	private LocalDateTime updateTimestamp;
	private String status;
}
