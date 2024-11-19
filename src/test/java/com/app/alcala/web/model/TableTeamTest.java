package com.app.alcala.web.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TableTeamTest {

    @Test
    public void testConstructores() {
        // Verifica el constructor sin argumentos
        TableTeam team = new TableTeam();
        assertThat(team).isNotNull();

        // Verifica el constructor con argumentos
        TablePerEmployee employee1 = new TablePerEmployee("john.doe", 1L, 5, 3, 2, 1, 0, 1);
        TablePerEmployee employee2 = new TablePerEmployee("jane.smith", 2L, 4, 2, 3, 2, 1, 0);
        List<TablePerEmployee> employeeList = List.of(employee1, employee2);

        TableTeam teamWithEmployees = new TableTeam(employeeList);

        assertThat(teamWithEmployees.getListTablePerEmployee()).isEqualTo(employeeList);
    }

    @Test
    public void testGettersSetters() {
        TableTeam team = new TableTeam();
        
        TablePerEmployee employee = new TablePerEmployee("john.doe", 1L, 5, 3, 2, 1, 0, 1);
        List<TablePerEmployee> employeeList = List.of(employee);

        team.setListTablePerEmployee(employeeList);

        assertThat(team.getListTablePerEmployee()).isEqualTo(employeeList);
    }

    @Test
    public void testEqualsHashCode() {
        TablePerEmployee employee1 = new TablePerEmployee("john.doe", 1L, 5, 3, 2, 1, 0, 1);
        TablePerEmployee employee2 = new TablePerEmployee("jane.smith", 2L, 4, 2, 3, 2, 1, 0);
        List<TablePerEmployee> employeeList1 = List.of(employee1, employee2);
        List<TablePerEmployee> employeeList2 = List.of(employee1, employee2);

        TableTeam team1 = new TableTeam(employeeList1);
        TableTeam team2 = new TableTeam(employeeList2);

        // Verifica que dos objetos con la misma lista de empleados sean iguales
        assertThat(team1).isEqualTo(team2);
        assertThat(team1.hashCode()).isEqualTo(team2.hashCode());
    }

    @Test
    public void testToString() {
        TablePerEmployee employee = new TablePerEmployee("john.doe", 1L, 5, 3, 2, 1, 0, 1);
        List<TablePerEmployee> employeeList = List.of(employee);

        TableTeam team = new TableTeam(employeeList);
        
        String esperado = "TableTeam(listTablePerEmployee=[" + employee.toString() + "])";

        // Verifica que el método toString produce la salida esperada
        assertThat(team.toString()).isEqualTo(esperado);
    }
}
