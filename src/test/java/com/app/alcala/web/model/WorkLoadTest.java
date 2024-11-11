package com.app.alcala.web.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class WorkLoadTest {

    @Test
    public void testConstructores() {
        // Verifica el constructor sin argumentos
        WorkLoad workLoad = new WorkLoad();
        assertThat(workLoad).isNotNull();

        // Verifica el constructor con argumentos
        WorkPerEmployee employee1 = new WorkPerEmployee("john.doe", 101L, 50, List.of(), List.of());
        WorkPerEmployee employee2 = new WorkPerEmployee("jane.smith", 102L, 30, List.of(), List.of());
        List<WorkPerEmployee> employeeList = List.of(employee1, employee2);

        WorkLoad workLoadWithEmployees = new WorkLoad(employeeList);

        assertThat(workLoadWithEmployees.getListWorkPerEmployee()).isEqualTo(employeeList);
    }

    @Test
    public void testGettersSetters() {
        WorkLoad workLoad = new WorkLoad();

        WorkPerEmployee employee = new WorkPerEmployee("john.doe", 101L, 50, List.of(), List.of());
        List<WorkPerEmployee> employeeList = List.of(employee);

        workLoad.setListWorkPerEmployee(employeeList);

        assertThat(workLoad.getListWorkPerEmployee()).isEqualTo(employeeList);
    }

    @Test
    public void testEqualsHashCode() {
        WorkPerEmployee employee1 = new WorkPerEmployee("john.doe", 101L, 50, List.of(), List.of());
        WorkPerEmployee employee2 = new WorkPerEmployee("jane.smith", 102L, 30, List.of(), List.of());
        List<WorkPerEmployee> employeeList1 = List.of(employee1, employee2);
        List<WorkPerEmployee> employeeList2 = List.of(employee1, employee2);

        WorkLoad workLoad1 = new WorkLoad(employeeList1);
        WorkLoad workLoad2 = new WorkLoad(employeeList2);

        // Verifica que dos objetos con la misma lista de trabajo por empleado sean iguales
        assertThat(workLoad1).isEqualTo(workLoad2);
        assertThat(workLoad1.hashCode()).isEqualTo(workLoad2.hashCode());
    }

    @Test
    public void testToString() {
        WorkPerEmployee employee = new WorkPerEmployee("john.doe", 101L, 50, List.of(), List.of());
        List<WorkPerEmployee> employeeList = List.of(employee);

        WorkLoad workLoad = new WorkLoad(employeeList);
        String expectedString = "WorkLoad(listWorkPerEmployee=" + employeeList.toString() + ")";

        // Verifica que el método toString produce la salida esperada
        assertThat(workLoad.toString()).isEqualTo(expectedString);
    }
}
