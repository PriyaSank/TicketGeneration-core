package com.ticket.model;

import lombok.Data;

@Data
public class UserModel {
private Integer id;
private String name;
private String emailId;
private String password;
private Boolean active;
}
