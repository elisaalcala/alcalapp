package com.app.alcala.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long employeeId;

    @Column(name = "name_employee")
    private String employeeName;
    
    @Column(name = "active_employee")
    private Boolean employeeActive;

    @Column(name = "last_name_employee")
    private String employeeLastName;

    @Column(name = "dni")
    private String employeeDni;

    @Column(name = "position")
    private String employeePosition;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @Column(name = "hire_date")
    private Timestamp hireDate;
    
    @Column(name = "user_employee")
    private String userEmployee;
    
    @Column(name = "name_team")
    private String nameTeam;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Team.class)
    private Team team;

    @ToString.Exclude
    @OneToMany(targetEntity = Project.class, fetch = FetchType.EAGER, mappedBy = "employeeAssign")
    @MapKey(name = "idProject")
    private Map<Long, Project> projectMapEmployee;

    @ToString.Exclude
    @OneToMany(targetEntity = Ticket.class, fetch = FetchType.EAGER, mappedBy = "employeeAssign")
    @MapKey(name = "idTicket")
    private Map<Long, Ticket> ticketMapEmployee;

}
