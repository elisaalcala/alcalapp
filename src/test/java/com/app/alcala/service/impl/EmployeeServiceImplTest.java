package com.app.alcala.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.EmployeeRepository;
import com.app.alcala.web.model.WorkPerEmployee;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testFindByUserEmployee() {
        Employee employee = new Employee();
        employee.setUserEmployee("JohnDoe");

        when(employeeRepository.findByUserEmployee("JohnDoe")).thenReturn(Optional.of(employee));

        Employee result = employeeService.findByUserEmployee("JohnDoe");

        assertEquals("JohnDoe", result.getUserEmployee());
    }

    @Test
    void testFindByEmployeeId() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        when(employeeRepository.findByEmployeeId(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.findByEmployeeId(1L);

        assertEquals(1L, result.getEmployeeId());
    }

    @Test
    void testSave() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.save(employee);

        assertEquals(1L, result.getEmployeeId());
    }

    @Test
    void testDeleteTicket() {
        Employee employee = new Employee();
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        employee.setTicketMapEmployee(new HashMap<>());
        employee.getTicketMapEmployee().put(1L, ticket);

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.deleteTicket(employee, ticket);

        assertNull(result.getTicketMapEmployee().get(1L));
        verify(employeeRepository, times(1)).save(employeeCaptor.capture());

        Employee capturedEmployee = employeeCaptor.getValue();

        assertSame(employee, capturedEmployee);
    }

    @Test
    void testDeleteProject() {
        Employee employee = new Employee();
        Project project = new Project();
        project.setIdProject(1L);
        employee.setProjectMapEmployee(new HashMap<>());
        employee.getProjectMapEmployee().put(1L, project);

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.deleteProject(employee, project);

        assertNull(result.getProjectMapEmployee().get(1L));
        verify(employeeRepository, times(1)).save(employeeCaptor.capture());

        Employee capturedEmployee = employeeCaptor.getValue();

        assertSame(employee, capturedEmployee);
    }

    @Test
    void testCalculateWorkLoad() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setUserEmployee("JohnDoe");

        Project project = new Project();
        project.setIdProject(1L);
        project.setStatusProject("In progress");
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        ticket.setStatusTicket("In Progress");

        employee.setProjectMapEmployee(new HashMap<>());
        employee.getProjectMapEmployee().put(1L, project);

        employee.setTicketMapEmployee(new HashMap<>());
        employee.getTicketMapEmployee().put(1L, ticket);

        WorkPerEmployee result = employeeService.calculateWorkLoad(employee);

        assertEquals(1, result.getProjectInProgress().size());
        assertEquals(1, result.getTicketInProgress().size());
        assertEquals(2, result.getLoad());
    }
    
    @Test
    public void testDeleteEmployee() {
        // Crear el empleado a eliminar
        Employee employeeToDelete = new Employee();
        
        // Llamada al método delete
        employeeService.delete(employeeToDelete);
        
        // Verificar que el método delete fue llamado en el repositorio con el objeto correcto
        verify(employeeRepository, times(1)).delete(employeeToDelete);
    }
    
    @Test
    public void testCreateNewEmployee() {
        // Datos de prueba
        Team team = new Team();
        Employee employeeNew = new Employee();
        employeeNew.setBirthDate(Timestamp.valueOf(LocalDateTime.of(1990, 5, 15, 0, 0)));

        // Comportamiento esperado del repositorio
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llamada al método
        Employee result = employeeService.createNewEmployee(employeeNew, team);

        // Verificaciones
        LocalDateTime expectedHireDate = LocalDateTime.now().withSecond(0).withNano(0);
        assertEquals(Timestamp.valueOf(expectedHireDate), result.getHireDate(), "La fecha de contratación debe ser la actual sin segundos ni nanosegundos");

        LocalDateTime expectedBirthDate = LocalDateTime.of(1990, 5, 15, 0, 0);
        assertEquals(Timestamp.valueOf(expectedBirthDate), result.getBirthDate(), "La fecha de nacimiento debe ser la asignada sin segundos ni nanosegundos");

        assertEquals(new HashMap<>(), result.getProjectMapEmployee(), "El mapa de proyectos debe estar vacío");
        assertEquals(new HashMap<>(), result.getTicketMapEmployee(), "El mapa de tickets debe estar vacío");
        assertEquals(team, result.getTeam(), "El equipo debe ser el mismo que se pasó como parámetro");

        // Verificar que el método save fue llamado una vez
        verify(employeeRepository, times(1)).save(employeeNew);
    }
}
