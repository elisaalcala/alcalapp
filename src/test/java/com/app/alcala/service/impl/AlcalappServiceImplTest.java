package com.app.alcala.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.entities.User;
import com.app.alcala.repositories.UserRepository;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.MessageService;
import com.app.alcala.service.ProjectService;
import com.app.alcala.service.ReleaseService;
import com.app.alcala.service.TeamService;
import com.app.alcala.service.TicketService;
import com.app.alcala.web.model.EmployeePerTeam;
import com.app.alcala.web.model.ProjectTable;
import com.app.alcala.web.model.TablePerEmployee;
import com.app.alcala.web.model.TableTeam;
import com.app.alcala.web.model.WorkLoad;
import com.app.alcala.web.model.WorkPerEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
class AlcalappServiceImplTest {

    @InjectMocks
    private AlcalappServiceImpl alcalappService;

    @Mock
    private ReleaseService releaseService;

    @Mock
    private ProjectService projectService;

    @Mock
    private TicketService ticketService;

    @Mock
    private TeamService teamService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private MessageService messageService;
    
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateUserAndEmployee() {
        // Datos de prueba
        User user = new User();
        user.setEncodedPassword("plainPassword");

        Employee employeeNew = new Employee();
        employeeNew.setNameTeam("Development");

        Team team = new Team();
        Employee createdEmployee = new Employee();

        // Configuración de los mocks
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(teamService.findByNameTeam("Development")).thenReturn(team);
        when(employeeService.createNewEmployee(employeeNew, team)).thenReturn(createdEmployee);

        // Llamada al método
        Employee result = alcalappService.createUserAndEmployee(user, employeeNew);

        // Verificaciones
        assertEquals(createdEmployee, result, "El empleado creado debe coincidir con el retornado por el servicio");

        // Verificar que la contraseña fue encriptada y el usuario guardado
        verify(passwordEncoder, times(1)).encode("plainPassword");
        assertEquals("encodedPassword", user.getEncodedPassword(), "La contraseña debe estar encriptada");
        verify(userRepository, times(1)).save(user);

        // Verificar que el equipo fue buscado por su nombre
        verify(teamService, times(1)).findByNameTeam("Development");

        // Verificar que el nuevo empleado fue creado con el equipo encontrado
        verify(employeeService, times(1)).createNewEmployee(employeeNew, team);
    }
    @Test
    void testFindProjectsPerRelease() {
        Team team = new Team();
        List<Release> releases = new ArrayList<>();
        Release release = new Release();
        release.setIdRelease(1L);
        release.setNameRelease("Release 1");
        releases.add(release);

        List<Project> projects = new ArrayList<>();
        Project project = new Project();
        project.setIdProject(1L);
        project.setNameProject("Project 1");
        projects.add(project);

        when(releaseService.findByReleaseNotCompleted()).thenReturn(releases);
        when(projectService.findByTeamAssignAndRelease(any(Team.class), any(Release.class))).thenReturn(projects);

        List<ProjectTable> result = alcalappService.findProjectsPerRelease(team);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Release 1", result.get(0).getReleaseName());
    }

    @Test
    void testSaveTicket() {
        Team team = new Team();
        team.setNameTeam("Team 1");
        team.setTicketMapTeam(new HashMap<>());
        
        Employee employee = new Employee();
        employee.setUserEmployee("Employee 1");

        Ticket updatedTicket = new Ticket();
        updatedTicket.setTeamNameAssign("Team 1");

        Team teamAssign = new Team();
        teamAssign.setTicketMapTeam(new HashMap<>()); 
        teamAssign.setNameTeam("Team 1");

        Message message = new Message();
        Message messageAssign = new Message();
        Ticket savedTicket = new Ticket();
        savedTicket.setIdTicket(1L);

        when(teamService.findByNameTeam(anyString())).thenReturn(teamAssign);
        when(messageService.messageCreation(any(Timestamp.class), anyString(), anyString())).thenReturn(message);
        when(messageService.messageAssign(any(Timestamp.class), anyString(), anyString())).thenReturn(messageAssign);
        when(ticketService.mapNewTicket(any(Ticket.class), any(Employee.class), any(Team.class), any(Team.class), any(Message.class), any(Message.class))).thenReturn(savedTicket);
        when(ticketService.save(any(Ticket.class))).thenReturn(savedTicket);

        Ticket result = alcalappService.save(updatedTicket, employee, team);

        assertNotNull(result);
        assertEquals(1L, result.getIdTicket());
    }

    @Test
    void testAssignTicket() {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);

        Employee employee = new Employee();
        employee.setUserEmployee("User 1");
        employee.setTicketMapEmployee(new HashMap<>());

        when(ticketService.findById(anyLong())).thenReturn(ticket);
        when(employeeService.findByUserEmployee(anyString())).thenReturn(employee);
        when(ticketService.save(any(Ticket.class))).thenReturn(ticket);

        Ticket result = alcalappService.assignTicket(1L, "\"User 1\"");

        assertNotNull(result);
        assertEquals("User 1", result.getEmployeeUserAssign());
        assertEquals(employee, result.getEmployeeAssign());
        assertTrue(employee.getTicketMapEmployee().containsKey(1L));
        assertEquals(ticket, employee.getTicketMapEmployee().get(1L));
    }
    
    @Test
    void testAssignTicketWithoutAssigningEmployee() {
    	Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        Employee employee = new Employee();
        employee.setUserEmployee("User 1");
        employee.setTicketMapEmployee(new HashMap<>());
        employee.getTicketMapEmployee().put(1L, ticket);
        ticket.setEmployeeAssign(employee);
        
        when(ticketService.findById(anyLong())).thenReturn(ticket);
        when(ticketService.save(any(Ticket.class))).thenReturn(ticket);

        Ticket result = alcalappService.assignTicket(1L, "\"Sin asignar\"");

        assertNull(result.getEmployeeAssign());
        assertEquals("", result.getEmployeeUserAssign());

    }
    
    @Test
    void testMoveTicket() {
    	
        Employee employee = new Employee();
        employee.setUserEmployee("User 1");

        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        ticket.setEmployeeAssign(employee);

        Team team = new Team();
        team.setNameTeam("Team 1");
        team.setTicketMapTeam(new HashMap<>());

        Message message = new Message();

        when(ticketService.findById(anyLong())).thenReturn(ticket);
        when(teamService.findByNameTeam(anyString())).thenReturn(team);
        when(messageService.messageMove(any(Timestamp.class), anyString(), anyString())).thenReturn(message);
        when(ticketService.moveTicket(any(Ticket.class), any(Team.class), any(Message.class))).thenReturn(ticket);

        Ticket result = alcalappService.moveTicket(1L, "\"Team 1\"");

        assertNotNull(result);
        assertEquals(1L, result.getIdTicket());
        assertEquals("User 1", ticket.getEmployeeAssign().getUserEmployee());
        assertEquals("Team 1", team.getNameTeam());
    }

    @Test
    void testDeleteTicket() {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);

        when(ticketService.findById(anyLong())).thenReturn(ticket);
        when(ticketService.delete(any(Ticket.class))).thenReturn(true);

        Boolean result = alcalappService.deleteTicket(1L, new Team());

        assertTrue(result);
    }

    @Test
    void testAssignProject() {
        Project project = new Project();
        project.setIdProject(1L);

        Employee employee = new Employee();
        employee.setUserEmployee("User 1");
        employee.setProjectMapEmployee(new HashMap<>());
        
        when(projectService.findById(anyLong())).thenReturn(project);
        when(employeeService.findByUserEmployee(anyString())).thenReturn(employee);
        when(projectService.save(any(Project.class))).thenReturn(project);

        Project result = alcalappService.assignProject(1L, "\"User 1\"");

        assertNotNull(result);
        assertEquals("User 1", result.getEmployeeUserAssign()); 
        
    }
    
    @Test
    void testAssignProjectWithoutAssigningEmployee() {
    	Project project = new Project();
        project.setIdProject(1L);
        Employee employee = new Employee();
        employee.setUserEmployee("User 1");
        employee.setProjectMapEmployee(new HashMap<>());
        employee.getProjectMapEmployee().put(1L, project);
        project.setEmployeeAssign(employee);
        
        when(projectService.findById(anyLong())).thenReturn(project);
        when(projectService.save(any(Project.class))).thenReturn(project);

        Project result = alcalappService.assignProject(1L, "\"Sin asignar\"");

        assertNull(result.getEmployeeAssign());
        assertEquals("", result.getEmployeeUserAssign());

    }
    
    @Test
    void testDeleteProject() {
        Project project = new Project();
        project.setIdProject(1L);

        when(projectService.findById(anyLong())).thenReturn(project);
        when(projectService.delete(any(Project.class))).thenReturn(true);

        Boolean result = alcalappService.deleteProject(1L);

        assertTrue(result);
    }

    @Test
    void testSaveProject() {
        Project project = new Project();
        project.setIdProject(1L);
        project.setTeamNameAssign("Team 1");
        project.setReleaseName("Release 1");

        Employee employee = new Employee();

        Team teamAssign = new Team();
        teamAssign.setNameTeam("Team 1");
        teamAssign.setProjectMapTeam(new HashMap<>());

        Release release = new Release();
        release.setNameRelease("Release 1");
        release.setProjectMap(new HashMap<>());

        when(teamService.findByNameTeam(anyString())).thenReturn(teamAssign);
        when(releaseService.findByNameRelease(anyString())).thenReturn(release);
        when(projectService.mapNewProject(any(Project.class), any(Employee.class), any(Team.class), any(Release.class))).thenReturn(project);
        when(projectService.save(any(Project.class))).thenReturn(project);

        Project result = alcalappService.save(project, employee);

        assertNotNull(result);
        assertEquals(1L, result.getIdProject());
        assertEquals("Team 1", teamAssign.getProjectMapTeam().get(1L).getTeamNameAssign());
        assertEquals("Release 1", release.getProjectMap().get(1L).getReleaseName());
    }

    @Test
    void testDeleteRelease() {
        Release release = new Release();
        release.setIdRelease(1L);
        release.setProjectMap(new HashMap<>());

        when(releaseService.findByIdRelease(anyLong())).thenReturn(release);
        when(releaseService.delete(any(Release.class))).thenReturn(true);

        Boolean result = alcalappService.deleteRelease(1L);

        assertTrue(result);
    }

    @Test
    void testCalculateWorkLoad() {
        Team team = new Team();
        team.setEmployeeMap(new HashMap<>());

        Employee employee = new Employee();
        employee.setUserEmployee("User 1");

        team.getEmployeeMap().put(1L, employee);

        WorkPerEmployee workPerEmployee = new WorkPerEmployee();
        workPerEmployee.setUserEmployee("User 1");
        workPerEmployee.setLoad(10);

        when(employeeService.calculateWorkLoad(any(Employee.class))).thenReturn(workPerEmployee);

        WorkLoad result = alcalappService.calculateWorkLoad(team);

        assertNotNull(result);
        assertEquals(1, result.getListWorkPerEmployee().size());
        assertEquals("User 1", result.getListWorkPerEmployee().get(0).getUserEmployee());
        assertEquals(10, result.getListWorkPerEmployee().get(0).getLoad());
    }
    
    @Test
    void testLoadPerEmployee() {
        WorkLoad workload = new WorkLoad();
        List<WorkPerEmployee> workPerEmployeeList = new ArrayList<>();
        workPerEmployeeList.add(new WorkPerEmployee("User1", null, 10, null, null));
        workPerEmployeeList.add(new WorkPerEmployee("User2", null, 20, null, null));
        workload.setListWorkPerEmployee(workPerEmployeeList);

        AlcalappServiceImpl alcalappService = new AlcalappServiceImpl();
        List<String> result = alcalappService.loadPerEmployee(workload);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("\"10\"", result.get(0));
        assertEquals("\"20\"", result.get(1));
    }

    @Test
    void testUserPerEmployee() {
        WorkLoad workload = new WorkLoad();
        List<WorkPerEmployee> workPerEmployeeList = new ArrayList<>();
        workPerEmployeeList.add(new WorkPerEmployee("User1", null, 10, null, null));
        workPerEmployeeList.add(new WorkPerEmployee("User2", null, 20, null, null));
        workload.setListWorkPerEmployee(workPerEmployeeList);

        AlcalappServiceImpl alcalappService = new AlcalappServiceImpl();
        List<String> result = alcalappService.userPerEmployee(workload);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("\"User1\"", result.get(0));
        assertEquals("\"User2\"", result.get(1));
    }
    
    @Test
    public void testCalculateTableTeam() {
        // Configurar datos de prueba
        Team team = new Team();
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1L);
        employee1.setUserEmployee("user1");
        
        Employee employee2 = new Employee();
        employee2.setEmployeeId(2L);
        employee2.setUserEmployee("user2");

        Map<Long, Employee> employeeMap = new HashMap<>();
        employeeMap.put(1L, employee1);
        employeeMap.put(2L, employee2);
        team.setEmployeeMap(employeeMap);

        when(ticketService.findCountTicketsFinishByEmployee(employee1)).thenReturn(5);
        when(projectService.findCountProjectsFinishByEmployee(employee1)).thenReturn(2);
        when(ticketService.findCountTicketsFinishByEmployee(employee2)).thenReturn(3);
        when(projectService.findCountProjectsFinishByEmployee(employee2)).thenReturn(4);

        // Llamada al método
        TableTeam result = alcalappService.calculateTableTeam(team);

        // Verificación de resultados
        assertEquals(2, result.getListTablePerEmployee().size(), "Debe haber 2 empleados en la tabla del equipo");

        TablePerEmployee table1 = result.getListTablePerEmployee().get(0);
        assertEquals(1L, table1.getIdEmployee());
        assertEquals("user1", table1.getUserEmployee());
        assertEquals(5, table1.getFinishTickets());
        assertEquals(2, table1.getFinishProjects());

        TablePerEmployee table2 = result.getListTablePerEmployee().get(1);
        assertEquals(2L, table2.getIdEmployee());
        assertEquals("user2", table2.getUserEmployee());
        assertEquals(3, table2.getFinishTickets());
        assertEquals(4, table2.getFinishProjects());
    }
    
    @Test
    public void testGetEmployeesTeam() throws JsonProcessingException {
        TableTeam tableTeam = new TableTeam();
        TablePerEmployee table1 = new TablePerEmployee();
        table1.setUserEmployee("user1");
        TablePerEmployee table2 = new TablePerEmployee();
        table2.setUserEmployee("user2");

        tableTeam.setListTablePerEmployee(Arrays.asList(table1, table2));

        String result = alcalappService.getEmployeesTeam(tableTeam);
        
        assertEquals("[\"user1\",\"user2\"]", result);
    }
    
    @Test
    public void testGetEmployeesTicketsResolved() throws JsonProcessingException {
        TableTeam tableTeam = new TableTeam();
        TablePerEmployee table1 = new TablePerEmployee();
        table1.setFinishTickets(5);
        TablePerEmployee table2 = new TablePerEmployee();
        table2.setFinishTickets(3);

        tableTeam.setListTablePerEmployee(Arrays.asList(table1, table2));

        String result = alcalappService.getEmployeesTicketsResolved(tableTeam);
        
        assertEquals("[5,3]", result);
    }
    
    @Test
    public void testGetEmployeesProjectsResolved() throws JsonProcessingException {
        TableTeam tableTeam = new TableTeam();
        TablePerEmployee table1 = new TablePerEmployee();
        table1.setFinishProjects(4);
        TablePerEmployee table2 = new TablePerEmployee();
        table2.setFinishProjects(2);

        tableTeam.setListTablePerEmployee(Arrays.asList(table1, table2));

        String result = alcalappService.getemployeesProjectsResolved(tableTeam);
        
        assertEquals("[4,2]", result);
    }
    
    @Test
    public void testGetLastSixMonths() throws JsonProcessingException {
        String result = alcalappService.getLastSixMonths();
        
        // Generar los nombres de los últimos 6 meses en español para la verificación
        List<String> expectedMonths = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        Locale spanishLocale = new Locale("es");
        for (int i = 5; i >= 0; i--) {
            String month = currentDate.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL, spanishLocale);
            month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
            expectedMonths.add(month);
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String expectedJson = mapper.writeValueAsString(expectedMonths);
        
        assertEquals(expectedJson, result);
    }
    
    @Test
    public void testGetEmployeesPerTeam() {
        Employee employee1 = new Employee();
        employee1.setUserEmployee("user1");
        employee1.setEmployeePosition("Developer");

        Employee employee2 = new Employee();
        employee2.setUserEmployee("user2");
        employee2.setEmployeePosition("Manager");

        Employee employeeSession = new Employee();
        employeeSession.setUserEmployee("userSession");

        List<Employee> employees = Arrays.asList(employee1, employee2, employeeSession);

        List<EmployeePerTeam> result = alcalappService.getEmployeesPerTeam(employees, employeeSession);

        assertEquals(2, result.size(), "Debe haber 2 empleados excluyendo la sesión actual");
        assertEquals("user1", result.get(0).getUserEmployee());
        assertEquals("Developer", result.get(0).getEmployeePosition());
        assertEquals("user2", result.get(1).getUserEmployee());
        assertEquals("Manager", result.get(1).getEmployeePosition());
    }
}
