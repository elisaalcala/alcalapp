package com.app.alcala.web.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TablePerEmployeeTest {

    @Test
    public void testConstructores() {
        // Verifica el constructor sin argumentos
        TablePerEmployee employee = new TablePerEmployee();
        assertThat(employee).isNotNull();

        // Verifica el constructor con todos los argumentos
        TablePerEmployee employeeConDatos = new TablePerEmployee(
            "john.doe", 1L, 5, 3, 2, 1, 0, 1
        );

        assertThat(employeeConDatos.getUserEmployee()).isEqualTo("john.doe");
        assertThat(employeeConDatos.getIdEmployee()).isEqualTo(1L);
        assertThat(employeeConDatos.getFinishTickets()).isEqualTo(5);
        assertThat(employeeConDatos.getFinishProjects()).isEqualTo(3);
        assertThat(employeeConDatos.getReadyTickets()).isEqualTo(2);
        assertThat(employeeConDatos.getReadyProjects()).isEqualTo(1);
        assertThat(employeeConDatos.getNotCompletedTickets()).isEqualTo(0);
        assertThat(employeeConDatos.getNotCompletedProjects()).isEqualTo(1);
    }

    @Test
    public void testGettersSetters() {
        TablePerEmployee employee = new TablePerEmployee();
        employee.setUserEmployee("jane.doe");
        employee.setIdEmployee(2L);
        employee.setFinishTickets(7);
        employee.setFinishProjects(4);
        employee.setReadyTickets(3);
        employee.setReadyProjects(2);
        employee.setNotCompletedTickets(1);
        employee.setNotCompletedProjects(0);

        assertThat(employee.getUserEmployee()).isEqualTo("jane.doe");
        assertThat(employee.getIdEmployee()).isEqualTo(2L);
        assertThat(employee.getFinishTickets()).isEqualTo(7);
        assertThat(employee.getFinishProjects()).isEqualTo(4);
        assertThat(employee.getReadyTickets()).isEqualTo(3);
        assertThat(employee.getReadyProjects()).isEqualTo(2);
        assertThat(employee.getNotCompletedTickets()).isEqualTo(1);
        assertThat(employee.getNotCompletedProjects()).isEqualTo(0);
    }

    @Test
    public void testEqualsHashCode() {
        TablePerEmployee employee1 = new TablePerEmployee(
            "john.doe", 1L, 5, 3, 2, 1, 0, 1
        );
        TablePerEmployee employee2 = new TablePerEmployee(
            "john.doe", 1L, 5, 3, 2, 1, 0, 1
        );

        // Verifica que dos objetos con los mismos datos sean iguales
        assertThat(employee1).isEqualTo(employee2);
        assertThat(employee1.hashCode()).isEqualTo(employee2.hashCode());
    }

    @Test
    public void testToString() {
        TablePerEmployee employee = new TablePerEmployee(
            "john.doe", 1L, 5, 3, 2, 1, 0, 1
        );
        String esperado = "TablePerEmployee(userEmployee=john.doe, idEmployee=1, finishTickets=5, finishProjects=3, readyTickets=2, readyProjects=1, notCompletedTickets=0, notCompletedProjects=1)";

        // Verifica que el método toString produzca la salida esperada
        assertThat(employee.toString()).isEqualTo(esperado);
    }
}
