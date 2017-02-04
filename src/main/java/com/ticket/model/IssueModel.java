package com.ticket.model;

import lombok.Data;

@Data
public class IssueModel {
	private Integer id;
	private TicketDetailsModel tic;
	private EmployeeModel emp;
	private String solution;
}
