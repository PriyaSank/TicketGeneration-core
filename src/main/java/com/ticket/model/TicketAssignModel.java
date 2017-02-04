package com.ticket.model;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class TicketAssignModel {
private int id;
private TicketDetailsModel tic;
private EmployeeModel emp1;
private EmployeeModel emp2;
private LocalDateTime timestamp;

}
