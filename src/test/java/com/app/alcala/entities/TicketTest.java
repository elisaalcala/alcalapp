package com.app.alcala.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {

    private Ticket ticket;
    private Timestamp initialDate;
    private Timestamp modifyDate;
    private Timestamp finishDate;
    private List<Message> messages;

    @BeforeEach
    public void setup() {
        initialDate = Timestamp.valueOf("2023-01-01 10:10:10.0");
        modifyDate = Timestamp.valueOf("2023-02-01 12:12:12.0");
        finishDate = Timestamp.valueOf("2023-03-01 14:14:14.0");
        messages = new ArrayList<>();

        ticket = new Ticket(
                1L,
                "TKT-001",
                "Critical Bug",
                "Critical issue in production environment",
                "High",
                "Production",
                initialDate,
                modifyDate,
                finishDate,
                "Open",
                messages,
                "Development Team",
                "john_doe",
                "DevOps Team",
                "jane_smith",
                null,  // teamAssign, assuming it will be injected or initialized separately
                null,  // employeeAssign
                null,  // teamCreation
                null   // employeeCreation
        );
    }

    @Test
    public void testConstructor() {
        // Verifica que el constructor inicialice correctamente los valores
        assertThat(ticket.getIdTicket()).isEqualTo(1L);
        assertThat(ticket.getNameTicket()).isEqualTo("TKT-001");
        assertThat(ticket.getTitleTicket()).isEqualTo("Critical Bug");
        assertThat(ticket.getDescriptionTicket()).isEqualTo("Critical issue in production environment");
        assertThat(ticket.getPriorityTicket()).isEqualTo("High");
        assertThat(ticket.getEnvironmentTicket()).isEqualTo("Production");
        assertThat(ticket.getInitialDate()).isEqualTo(initialDate);
        assertThat(ticket.getModifyDate()).isEqualTo(modifyDate);
        assertThat(ticket.getFinishDate()).isEqualTo(finishDate);
        assertThat(ticket.getStatusTicket()).isEqualTo("Open");
        assertThat(ticket.getMessageTicket()).isEqualTo(messages);
        assertThat(ticket.getTeamNameAssign()).isEqualTo("Development Team");
        assertThat(ticket.getEmployeeUserAssign()).isEqualTo("john_doe");
        assertThat(ticket.getTeamNameCreation()).isEqualTo("DevOps Team");
        assertThat(ticket.getEmployeeUserCreation()).isEqualTo("jane_smith");
    }

    @Test
    public void testGettersSetters() {
        Ticket newTicket = new Ticket();

        newTicket.setIdTicket(2L);
        newTicket.setNameTicket("TKT-002");
        newTicket.setTitleTicket("Minor Bug");
        newTicket.setDescriptionTicket("Minor issue in test environment");
        newTicket.setPriorityTicket("Low");
        newTicket.setEnvironmentTicket("Test");
        newTicket.setInitialDate(initialDate);
        newTicket.setModifyDate(modifyDate);
        newTicket.setFinishDate(finishDate);
        newTicket.setStatusTicket("Closed");
        newTicket.setMessageTicket(messages);
        newTicket.setTeamNameAssign("Test Team");
        newTicket.setEmployeeUserAssign("alex_smith");
        newTicket.setTeamNameCreation("Support Team");
        newTicket.setEmployeeUserCreation("mark_brown");

        assertThat(newTicket.getIdTicket()).isEqualTo(2L);
        assertThat(newTicket.getNameTicket()).isEqualTo("TKT-002");
        assertThat(newTicket.getTitleTicket()).isEqualTo("Minor Bug");
        assertThat(newTicket.getDescriptionTicket()).isEqualTo("Minor issue in test environment");
        assertThat(newTicket.getPriorityTicket()).isEqualTo("Low");
        assertThat(newTicket.getEnvironmentTicket()).isEqualTo("Test");
        assertThat(newTicket.getInitialDate()).isEqualTo(initialDate);
        assertThat(newTicket.getModifyDate()).isEqualTo(modifyDate);
        assertThat(newTicket.getFinishDate()).isEqualTo(finishDate);
        assertThat(newTicket.getStatusTicket()).isEqualTo("Closed");
        assertThat(newTicket.getMessageTicket()).isEqualTo(messages);
        assertThat(newTicket.getTeamNameAssign()).isEqualTo("Test Team");
        assertThat(newTicket.getEmployeeUserAssign()).isEqualTo("alex_smith");
        assertThat(newTicket.getTeamNameCreation()).isEqualTo("Support Team");
        assertThat(newTicket.getEmployeeUserCreation()).isEqualTo("mark_brown");
    }

    @Test
    public void testEqualsAndHashCode() {
        Ticket anotherTicket = new Ticket(
                1L,
                "TKT-001",
                "Critical Bug",
                "Critical issue in production environment",
                "High",
                "Production",
                initialDate,
                modifyDate,
                finishDate,
                "Open",
                messages,
                "Development Team",
                "john_doe",
                "DevOps Team",
                "jane_smith",
                null,  // teamAssign
                null,  // employeeAssign
                null,  // teamCreation
                null   // employeeCreation
        );

        assertThat(ticket).isEqualTo(anotherTicket);
        assertThat(ticket.hashCode()).isEqualTo(anotherTicket.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Ticket(idTicket=1, nameTicket=TKT-001, titleTicket=Critical Bug, descriptionTicket=Critical issue in production environment, " +
                "priorityTicket=High, environmentTicket=Production, initialDate=" + initialDate + ", modifyDate=" + modifyDate + ", finishDate=" + finishDate + 
                ", statusTicket=Open, messageTicket=[], teamNameAssign=Development Team, employeeUserAssign=john_doe, " +
                "teamNameCreation=DevOps Team, employeeUserCreation=jane_smith, teamAssign=null, employeeAssign=null, teamCreation=null, employeeCreation=null)";

        assertThat(ticket.toString()).isEqualTo(expectedString);
    }
}
