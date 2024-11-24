package com.app.alcala.web.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	

    private String username;
    
    private String employeeName;

    private String employeeLastName;

    private String employeeDni;

    private String employeePosition;

    private Timestamp birthDate;
    
    private String nameTeam;
    
    private String role;
}
