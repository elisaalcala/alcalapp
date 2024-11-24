package com.app.alcala.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {

    private Employee employee;
    private Team teamMock;
    private Map<Long, Project> projectMapMock;
    private Map<Long, Ticket> ticketMapMock;
    private Timestamp birthDate;
    private Timestamp hireDate;

    @BeforeEach
    public void setup() {
        // Inicializa mocks y datos de prueba
        teamMock = mock(Team.class);
        projectMapMock = Map.of();
        ticketMapMock = Map.of();
        birthDate = Timestamp.valueOf("1990-01-01 00:00:00");
        hireDate = Timestamp.valueOf("2020-01-01 00:00:00");

        // Crea un objeto Employee utilizando el constructor completo, asegurándose de que el campo active_employee esté inicializado
        employee = new Employee(
                1L,
                "John",
                true,
                "Doe",
                "12345678A",
                "Developer",
                birthDate,
                hireDate,
                "johndoe",
                "DevTeam",
                teamMock,
                projectMapMock,
                ticketMapMock// Asegurarse de que el empleado esté activo
        );
    }

    @Test
    public void testConstructor() {
        // Verifica que el constructor asigne correctamente los valores
        assertThat(employee.getEmployeeId()).isEqualTo(1L);
        assertThat(employee.getEmployeeName()).isEqualTo("John");
        assertThat(employee.getEmployeeLastName()).isEqualTo("Doe");
        assertThat(employee.getEmployeeDni()).isEqualTo("12345678A");
        assertThat(employee.getEmployeePosition()).isEqualTo("Developer");
        assertThat(employee.getBirthDate()).isEqualTo(birthDate);
        assertThat(employee.getHireDate()).isEqualTo(hireDate);
        assertThat(employee.getUserEmployee()).isEqualTo("johndoe");
        assertThat(employee.getNameTeam()).isEqualTo("DevTeam");
        assertThat(employee.getTeam()).isEqualTo(teamMock);
        assertThat(employee.getProjectMapEmployee()).isEqualTo(projectMapMock);
        assertThat(employee.getTicketMapEmployee()).isEqualTo(ticketMapMock);
        assertThat(employee.getEmployeeActive()).isTrue(); // Verifica que el empleado esté activo
    }

    @Test
    public void testGettersSetters() {
        Employee newEmployee = new Employee();
        
        newEmployee.setEmployeeId(2L);
        newEmployee.setEmployeeName("Jane");
        newEmployee.setEmployeeLastName("Smith");
        newEmployee.setEmployeeDni("87654321B");
        newEmployee.setEmployeePosition("Manager");
        newEmployee.setBirthDate(Timestamp.valueOf("1985-05-15 00:00:00"));
        newEmployee.setHireDate(Timestamp.valueOf("2019-05-15 00:00:00"));
        newEmployee.setUserEmployee("janesmith");
        newEmployee.setNameTeam("ManagementTeam");
        newEmployee.setTeam(teamMock);
        newEmployee.setProjectMapEmployee(projectMapMock);
        newEmployee.setTicketMapEmployee(ticketMapMock);
        newEmployee.setEmployeeActive(true); // Setear el atributo active_employee a true

        // Verifica que los getters devuelvan los valores esperados
        assertThat(newEmployee.getEmployeeId()).isEqualTo(2L);
        assertThat(newEmployee.getEmployeeName()).isEqualTo("Jane");
        assertThat(newEmployee.getEmployeeLastName()).isEqualTo("Smith");
        assertThat(newEmployee.getEmployeeDni()).isEqualTo("87654321B");
        assertThat(newEmployee.getEmployeePosition()).isEqualTo("Manager");
        assertThat(newEmployee.getBirthDate()).isEqualTo(Timestamp.valueOf("1985-05-15 00:00:00"));
        assertThat(newEmployee.getHireDate()).isEqualTo(Timestamp.valueOf("2019-05-15 00:00:00"));
        assertThat(newEmployee.getUserEmployee()).isEqualTo("janesmith");
        assertThat(newEmployee.getNameTeam()).isEqualTo("ManagementTeam");
        assertThat(newEmployee.getTeam()).isEqualTo(teamMock);
        assertThat(newEmployee.getProjectMapEmployee()).isEqualTo(projectMapMock);
        assertThat(newEmployee.getTicketMapEmployee()).isEqualTo(ticketMapMock);
        assertThat(newEmployee.getEmployeeActive()).isTrue(); // Verifica que el empleado esté activo
    }

    @Test
    public void testEqualsAndHashCode() {
        Employee anotherEmployee = new Employee(
                1L,
                "John",
                true ,
                "Doe",
                "12345678A",
                "Developer",
                birthDate,
                hireDate,
                "johndoe",
                "DevTeam",
                teamMock,
                projectMapMock,
                ticketMapMock
        );

        // Verifica que dos objetos con los mismos valores sean iguales
        assertThat(employee).isEqualTo(anotherEmployee);
        assertThat(employee.hashCode()).isEqualTo(anotherEmployee.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Employee(employeeId=1, employeeName=John, employeeActive=true, employeeLastName=Doe, employeeDni=12345678A, " +
                "employeePosition=Developer, birthDate=" + birthDate.toString() + ", hireDate=" + hireDate.toString() + 
                ", userEmployee=johndoe, nameTeam=DevTeam, team=" + teamMock.toString()+")"; 

        // Verifica que el método toString genere la salida esperada
        assertThat(employee.toString()).isEqualTo(expectedString);
    }
}
