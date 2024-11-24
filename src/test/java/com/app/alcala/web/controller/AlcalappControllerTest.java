package com.app.alcala.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.app.alcala.configuration.SecurityConfiguration;
import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.ProjectService;
import com.app.alcala.service.ReleaseService;
import com.app.alcala.service.TeamService;
import com.app.alcala.service.TicketService;
import com.app.alcala.service.impl.UserDetailsServiceImpl;
import com.app.alcala.web.model.ProjectTable;
import com.app.alcala.web.model.TableTeam;
import com.app.alcala.web.model.WorkLoad;

@WebMvcTest(AlcalappController.class)
@Import(SecurityConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class AlcalappControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private ProjectService projectService;
    @MockBean
    private TeamService teamService;
    @MockBean
    private TicketService ticketService;
    @MockBean
    private ReleaseService releaseService;
    @MockBean
    private AlcalappService alcalappService;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().isFound()) 
                .andExpect(redirectedUrl("/login"));
    }
    
    @Test
    public void testLoginSuccess() throws Exception {
        
        UserDetails userDetails = User.withUsername("johndoe")
                .password(new BCryptPasswordEncoder().encode("pass"))
                .roles("USER")
                .build();
        
        when(userDetailsService.loadUserByUsername("johndoe")).thenReturn(userDetails);

        mockMvc.perform(post("/login")
                .param("username", "johndoe")
                .param("password", "pass"))
                
                .andExpect(status().isFound())
                
                .andExpect(header().string("Location", "/dailywork"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        when(userDetailsService.loadUserByUsername("user")).thenThrow(new UsernameNotFoundException("User not found"));

        mockMvc.perform(post("/login")
                .param("username", "user")
                .param("password", "password"))
        		.andExpect(status().isFound())
                .andExpect(header().string("Location", "/login?error=true"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testDailyWorkPage() throws Exception {
        // Crear mocks de las entidades y servicios necesarios
        Employee employee = new Employee();
        employee.setNameTeam("TeamA");

        Team team = new Team();
        List<Team> createTicketTeamsList = Arrays.asList(new Team());
        List<Release> releasesOpen = Arrays.asList(new Release());

        com.app.alcala.entities.User user = new com.app.alcala.entities.User();
        user.setRoles(Arrays.asList("USER"));
        // Configurar las respuestas de los servicios mockeados
        when(employeeService.findByUserEmployee("johndoe")).thenReturn(employee);
        when(teamService.findByNameTeam("TeamA")).thenReturn(team);
        when(teamService.findTeamsToSendTicket(any(Team.class))).thenReturn(createTicketTeamsList);
        when(releaseService.findByReleasesOpen()).thenReturn(releasesOpen);
        when(alcalappService.findByUserName(any())).thenReturn(user);

        List<ProjectTable> projectsTables = Arrays.asList(new ProjectTable());
        List<Ticket> ticketsNotCompleted = Arrays.asList(new Ticket());
        WorkLoad workLoad = new WorkLoad();
        List<String> userPerEmployee = Arrays.asList("User1");
        List<String> loadPerEmployee = Arrays.asList("Load1");
        TableTeam tableTeam = new TableTeam();

        when(alcalappService.findProjectsPerRelease(any(Team.class))).thenReturn(projectsTables);
        when(ticketService.findticketsNotCompletedByTeam(any(Team.class))).thenReturn(ticketsNotCompleted);
        when(alcalappService.calculateWorkLoad(any(Team.class))).thenReturn(workLoad);
        when(alcalappService.userPerEmployee(any(WorkLoad.class))).thenReturn(userPerEmployee);
        when(alcalappService.loadPerEmployee(any(WorkLoad.class))).thenReturn(loadPerEmployee);
        when(alcalappService.calculateTableTeam(any(Team.class))).thenReturn(tableTeam);

        // Ejecutar la solicitud simulada y verificar los resultados
        mockMvc.perform(get("/dailywork"))
                .andExpect(status().isOk())
                .andExpect(view().name("dailywork"))
                .andExpect(model().attribute("createTicketTeamsList", createTicketTeamsList))
                .andExpect(model().attribute("employee", employee))
                .andExpect(model().attribute("team", team))
                .andExpect(model().attribute("projectsTables", projectsTables))
                .andExpect(model().attribute("ticketsNotCompleted", ticketsNotCompleted))
                .andExpect(model().attribute("workLoad", workLoad))
                .andExpect(model().attribute("userPerEmployee", userPerEmployee))
                .andExpect(model().attribute("loadPerEmployee", loadPerEmployee))
                .andExpect(model().attribute("page", "TRABAJO DIARIO"));
    }

    
    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testMyWorkPage() throws Exception {
        Employee employee = new Employee();
        employee.setNameTeam("TeamA");

        List<Project> projectsNotCompleted = Arrays.asList(new Project());
        List<Ticket> ticketsNotCompleted = Arrays.asList(new Ticket());
        List<Project> projectsReady = Arrays.asList(new Project());
        List<Ticket> ticketsReady = Arrays.asList(new Ticket());
        List<Project> projectsFinish = Arrays.asList(new Project());
        List<Ticket> ticketsFinish = Arrays.asList(new Ticket());

        when(projectService.findProjectsNotCompletedByEmployee(any(Employee.class))).thenReturn(projectsNotCompleted);
        when(ticketService.findTicketsNotCompletedByEmployee(any(Employee.class))).thenReturn(ticketsNotCompleted);
        when(projectService.findProjectsReadyByEmployee(any(Employee.class))).thenReturn(projectsReady);
        when(ticketService.findTicketsReadyByEmployee(any(Employee.class))).thenReturn(ticketsReady);
        when(projectService.findProjectsFinishByEmployee(any(Employee.class))).thenReturn(projectsFinish);
        when(ticketService.findTicketsFinishByEmployee(any(Employee.class))).thenReturn(ticketsFinish);

        mockMvc.perform(get("/mywork")
                .sessionAttr("employee", employee))
                .andExpect(status().isOk())
                .andExpect(view().name("mywork"))
                .andExpect(model().attribute("projectsNotCompleted", projectsNotCompleted))
                .andExpect(model().attribute("ticketsNotCompleted", ticketsNotCompleted))
                .andExpect(model().attribute("projectsReady", projectsReady))
                .andExpect(model().attribute("ticketsReady", ticketsReady))
                .andExpect(model().attribute("projectsFinish", projectsFinish))
                .andExpect(model().attribute("ticketsFinish", ticketsFinish))
                .andExpect(model().attribute("page", "MI TRABAJO"));
    }
    


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGestion() throws Exception {
        List<Team> teams = Arrays.asList(new Team());
        List<Employee> employees = Arrays.asList(new Employee());

        when(teamService.findAll()).thenReturn(teams);
        when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/gestion"))
                .andExpect(status().isOk())
                .andExpect(view().name("gestion"))
                .andExpect(model().attribute("teams", teams))
                .andExpect(model().attribute("employees", employees))
                .andExpect(model().attribute("page", "GESTION"));
    }
    
    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testTeamPage() throws Exception {
        long teamId = 1L;
        Team team = new Team();
        team.setNameTeam("Team A");
        team.setEmployeeMap(new HashMap<>());
        com.app.alcala.entities.User user = new com.app.alcala.entities.User();
        user.setRoles(Arrays.asList("MANAGER"));


        when(teamService.findByIdTeam(teamId)).thenReturn(team);
        when(alcalappService.findByUserName(anyString())).thenReturn(user);

        mockMvc.perform(get("/teams/{id}", teamId))
                .andExpect(status().isOk())
                .andExpect(view().name("team"))
                .andExpect(model().attribute("team", team))
                .andExpect(model().attribute("page", "MI EQUIPO"));
    }
    
    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testUserPage() throws Exception {
        long teamId = 1L;
        Employee employee = new Employee();
        com.app.alcala.entities.User user = new com.app.alcala.entities.User();
        user.setRoles(Arrays.asList("USER"));


        when(employeeService.findByEmployeeId(teamId)).thenReturn(employee);
        when(alcalappService.findByUserName(anyString())).thenReturn(user);

        mockMvc.perform(get("/users/{id}", teamId))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(model().attribute("employeeSelect", employee))
                .andExpect(model().attribute("page", "MI PERFIL"));
    }


 

}
