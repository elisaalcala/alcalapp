package com.app.alcala.entities;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TEAM")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_team")
    private Long idTeam;

    @Column(name = "name_team")
    private String nameTeam;

    @Column(name = "leader")
    private Employee teamLeader;
    
    @Lob
    @Column(name = "description_team", columnDefinition = "LONGTEXT")
    private String descriptionTeam;

    @JsonIgnore
    @OneToMany(targetEntity = Employee.class, fetch = FetchType.EAGER, mappedBy = "team")
    @MapKey(name = "employeeId")
    private Map<Long, Employee> employeeMap;
    
    @OneToMany(targetEntity = Project.class, fetch = FetchType.LAZY, mappedBy = "teamAssign")
    @MapKey(name = "idProject")
    private Map<Long, Project> projectMapTeam;

    @OneToMany(targetEntity = Ticket.class, fetch = FetchType.EAGER, mappedBy = "teamAssign")
    @MapKey(name = "idTicket")
    private Map<Long, Ticket> ticketMapTeam;
    
    
    @Override
    public String toString() {
        return "Team{idTeam=" + idTeam + ", nameTeam='" + nameTeam + "'}"; // Evitar imprimir la colección completa
    }
}
