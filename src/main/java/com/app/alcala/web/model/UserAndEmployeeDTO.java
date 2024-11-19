package com.app.alcala.web.model;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndEmployeeDTO {
	
	private User user;
    private Employee employee;

}
