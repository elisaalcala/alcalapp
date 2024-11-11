package com.app.alcala.web.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeePerTeamTest {

    @Test
    public void testConstructores() {
        // Verifica el constructor sin argumentos
        EmployeePerTeam employee = new EmployeePerTeam();
        assertThat(employee).isNotNull();

        // Verifica el constructor con argumentos
        EmployeePerTeam employeeConDatos = new EmployeePerTeam("john.doe", "Developer");
        assertThat(employeeConDatos.getUserEmployee()).isEqualTo("john.doe");
        assertThat(employeeConDatos.getEmployeePosition()).isEqualTo("Developer");
    }

    @Test
    public void testGettersSetters() {
        EmployeePerTeam employee = new EmployeePerTeam();
        employee.setUserEmployee("jane.doe");
        employee.setEmployeePosition("Manager");

        assertThat(employee.getUserEmployee()).isEqualTo("jane.doe");
        assertThat(employee.getEmployeePosition()).isEqualTo("Manager");
    }

    @Test
    public void testEqualsHashCode() {
        EmployeePerTeam employee1 = new EmployeePerTeam("john.doe", "Developer");
        EmployeePerTeam employee2 = new EmployeePerTeam("john.doe", "Developer");

        // Verifica que dos objetos con los mismos datos sean iguales
        assertThat(employee1).isEqualTo(employee2);
        assertThat(employee1.hashCode()).isEqualTo(employee2.hashCode());
    }

    @Test
    public void testToString() {
        EmployeePerTeam employee = new EmployeePerTeam("john.doe", "Developer");
        String esperado = "EmployeePerTeam(userEmployee=john.doe, employeePosition=Developer)";

        // Verifica que el método toString produzca la salida esperada
        assertThat(employee.toString()).isEqualTo(esperado);
    }
}
