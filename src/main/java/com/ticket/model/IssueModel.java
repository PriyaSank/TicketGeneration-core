package com.ticket.model;

import lombok.Data;

@Data
public class IssueModel {
	private Integer id;
	private TicketDetailsModel tic;
	private String solution;
}
