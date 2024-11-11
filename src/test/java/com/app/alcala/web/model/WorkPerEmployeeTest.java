package com.app.alcala.web.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.app.alcala.entities.Project;
import com.app.alcala.entities.Ticket;

public class WorkPerEmployeeTest {

    @Test
    public void testConstructores() {
        // Verifica el constructor sin argumentos
        WorkPerEmployee workPerEmployee = new WorkPerEmployee();
        assertThat(workPerEmployee).isNotNull();

        // Verifica el constructor con todos los argumentos
        List<Project> projects = List.of(new Project(), new Project());
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());

        WorkPerEmployee workPerEmployeeWithValues = new WorkPerEmployee("john.doe", 123L, 50, projects, tickets);

        assertThat(workPerEmployeeWithValues.getUserEmployee()).isEqualTo("john.doe");
        assertThat(workPerEmployeeWithValues.getIdEmployee()).isEqualTo(123L);
        assertThat(workPerEmployeeWithValues.getLoad()).isEqualTo(50);
        assertThat(workPerEmployeeWithValues.getProjectInProgress()).isEqualTo(projects);
        assertThat(workPerEmployeeWithValues.getTicketInProgress()).isEqualTo(tickets);
    }

    @Test
    public void testGettersSetters() {
        WorkPerEmployee workPerEmployee = new WorkPerEmployee();

        List<Project> projects = List.of(new Project());
        List<Ticket> tickets = List.of(new Ticket());

        workPerEmployee.setUserEmployee("jane.doe");
        workPerEmployee.setIdEmployee(456L);
        workPerEmployee.setLoad(75);
        workPerEmployee.setProjectInProgress(projects);
        workPerEmployee.setTicketInProgress(tickets);

        assertThat(workPerEmployee.getUserEmployee()).isEqualTo("jane.doe");
        assertThat(workPerEmployee.getIdEmployee()).isEqualTo(456L);
        assertThat(workPerEmployee.getLoad()).isEqualTo(75);
        assertThat(workPerEmployee.getProjectInProgress()).isEqualTo(projects);
        assertThat(workPerEmployee.getTicketInProgress()).isEqualTo(tickets);
    }

    @Test
    public void testEqualsHashCode() {
        List<Project> projects = List.of(new Project());
        List<Ticket> tickets = List.of(new Ticket());

        WorkPerEmployee workPerEmployee1 = new WorkPerEmployee("john.doe", 123L, 50, projects, tickets);
        WorkPerEmployee workPerEmployee2 = new WorkPerEmployee("john.doe", 123L, 50, projects, tickets);

        // Verifica que dos objetos con los mismos datos sean iguales y tengan el mismo hashCode
        assertThat(workPerEmployee1).isEqualTo(workPerEmployee2);
        assertThat(workPerEmployee1.hashCode()).isEqualTo(workPerEmployee2.hashCode());
    }

    @Test
    public void testToString() {
        List<Project> projects = List.of(new Project());
        List<Ticket> tickets = List.of(new Ticket());

        WorkPerEmployee workPerEmployee = new WorkPerEmployee("john.doe", 123L, 50, projects, tickets);
        String esperado = "WorkPerEmployee(userEmployee=john.doe, idEmployee=123, load=50, projectInProgress=" + projects.toString() + ", ticketInProgress=" + tickets.toString() + ")";

        // Verifica que el método toString produce la salida esperada
        assertThat(workPerEmployee.toString()).isEqualTo(esperado);
    }
}
