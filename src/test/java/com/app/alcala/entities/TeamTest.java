package com.app.alcala.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

    private Team team;
    private Employee teamLeader;
    private Map<Long, Employee> employeeMap;
    private Map<Long, Project> projectMapTeam;
    private Map<Long, Ticket> ticketMapTeam;

    @BeforeEach
    public void setup() {
        teamLeader = new Employee();
        teamLeader.setEmployeeId(1L);
        teamLeader.setEmployeeName("John Doe");

        employeeMap = new HashMap<>();
        projectMapTeam = new HashMap<>();
        ticketMapTeam = new HashMap<>();

        team = new Team(
                1L,
                "Development Team",
                teamLeader,
                "This team is responsible for software development.",
                employeeMap,
                projectMapTeam,
                ticketMapTeam
        );
    }

    @Test
    public void testConstructor() {
        // Verifica que los valores se inicialicen correctamente
        assertThat(team.getIdTeam()).isEqualTo(1L);
        assertThat(team.getNameTeam()).isEqualTo("Development Team");
        assertThat(team.getTeamLeader()).isEqualTo(teamLeader);
        assertThat(team.getDescriptionTeam()).isEqualTo("This team is responsible for software development.");
        assertThat(team.getEmployeeMap()).isEqualTo(employeeMap);
        assertThat(team.getProjectMapTeam()).isEqualTo(projectMapTeam);
        assertThat(team.getTicketMapTeam()).isEqualTo(ticketMapTeam);
    }

    @Test
    public void testGettersSetters() {
        Team newTeam = new Team();

        // Setea valores utilizando setters
        Employee newLeader = new Employee();
        newLeader.setEmployeeId(2L);
        newLeader.setEmployeeName("Alice Smith");

        Map<Long, Employee> newEmployeeMap = new HashMap<>();
        Map<Long, Project> newProjectMap = new HashMap<>();
        Map<Long, Ticket> newTicketMap = new HashMap<>();

        newTeam.setIdTeam(2L);
        newTeam.setNameTeam("QA Team");
        newTeam.setTeamLeader(newLeader);
        newTeam.setDescriptionTeam("This team is responsible for quality assurance.");
        newTeam.setEmployeeMap(newEmployeeMap);
        newTeam.setProjectMapTeam(newProjectMap);
        newTeam.setTicketMapTeam(newTicketMap);

        // Verifica los valores establecidos
        assertThat(newTeam.getIdTeam()).isEqualTo(2L);
        assertThat(newTeam.getNameTeam()).isEqualTo("QA Team");
        assertThat(newTeam.getTeamLeader()).isEqualTo(newLeader);
        assertThat(newTeam.getDescriptionTeam()).isEqualTo("This team is responsible for quality assurance.");
        assertThat(newTeam.getEmployeeMap()).isEqualTo(newEmployeeMap);
        assertThat(newTeam.getProjectMapTeam()).isEqualTo(newProjectMap);
        assertThat(newTeam.getTicketMapTeam()).isEqualTo(newTicketMap);
    }

    @Test
    public void testEqualsAndHashCode() {
        Team anotherTeam = new Team(
                1L,
                "Development Team",
                teamLeader,
                "This team is responsible for software development.",
                employeeMap,
                projectMapTeam,
                ticketMapTeam
        );

        // Verifica que dos objetos con los mismos valores sean iguales
        assertThat(team).isEqualTo(anotherTeam);
        assertThat(team.hashCode()).isEqualTo(anotherTeam.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Team{idTeam=" + 1 + ", nameTeam='" + "Development Team" + "'}"; 

        // Verifica que el método toString genere la salida esperada
        assertThat(team.toString()).isEqualTo(expectedString);
    }
}

