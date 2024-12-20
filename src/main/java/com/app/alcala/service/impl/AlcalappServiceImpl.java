package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.entities.User;
import com.app.alcala.repositories.UserRepository;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.MessageService;
import com.app.alcala.service.ProjectService;
import com.app.alcala.service.ReleaseService;
import com.app.alcala.service.TeamService;
import com.app.alcala.service.TicketService;
import com.app.alcala.utils.Constants;
import com.app.alcala.web.model.EmployeeDTO;
import com.app.alcala.web.model.EmployeePerTeam;
import com.app.alcala.web.model.ProjectTable;
import com.app.alcala.web.model.TablePerEmployee;
import com.app.alcala.web.model.TableTeam;
import com.app.alcala.web.model.WorkLoad;
import com.app.alcala.web.model.WorkPerEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;

@Service
public class AlcalappServiceImpl implements AlcalappService {
	@Autowired
	private ReleaseService releaseService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    public Employee createUserAndEmployee(User user, Employee employeeNew) {
        user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
        userRepository.save(user);
        
        Team team = teamService.findByNameTeam(employeeNew.getNameTeam());
        return employeeService.createNewEmployee(employeeNew, team);
    }

	@Override
	public List<ProjectTable> findProjectsPerRelease(Team team) {
		List<ProjectTable> projectsTables = new ArrayList<>();
		List<Release> releaseNotCompleted = releaseService.findByReleaseNotCompleted();
		for (Release release : releaseNotCompleted) {
			List<Project> projectsNotCompleted = projectService.findByTeamAssignAndRelease(team, release);
			ProjectTable projectTable = new ProjectTable();
			projectTable.setProjects(projectsNotCompleted);
			projectTable.setReleaseName(release.getNameRelease());
			projectTable.setIdRelease(release.getIdRelease());
			projectTable.setInitialDate(release.getInitialDate());
			projectTable.setProDate(release.getProDate());
			projectTable.setTstDate(release.getTstDate());
			projectTable.setUatDate(release.getUatDate());
			projectsTables.add(projectTable);
		}
		return projectsTables;
	}

	@Override
	public Ticket save(Ticket updatedTicket, Employee employee, Team team) {
		Team teamAssign = teamService.findByNameTeam(updatedTicket.getTeamNameAssign());
		LocalDateTime currentDate = LocalDateTime.now();
		Message message = messageService.messageCreation(Timestamp.valueOf(currentDate),employee.getUserEmployee(), team.getNameTeam() );
		Message messageAssign = messageService.messageAssign(Timestamp.valueOf(currentDate),employee.getUserEmployee(), teamAssign.getNameTeam() );
		Ticket updated = ticketService.mapNewTicket(updatedTicket, employee, team, teamAssign, message, messageAssign);
		teamAssign.getTicketMapTeam().put(updatedTicket.getIdTicket(), updatedTicket);
		
		return ticketService.save(updated);
	}

	@Override
	public Ticket assignTicket(long id, String user) {
		String result = user.substring(1, user.length() - 1);
		Ticket ticket = ticketService.findById(id);
		if (result.equalsIgnoreCase(Constants.STATUS_SIN_ASIGNAR)){
			if (!ObjectUtils.isEmpty(ticket.getEmployeeAssign())) {
				Employee employeeQuit = ticket.getEmployeeAssign();
				employeeQuit.getTicketMapEmployee().remove(ticket.getIdTicket());
				ticket.setEmployeeAssign(null);
				ticket.setEmployeeUserAssign("");
				employeeService.save(employeeQuit);
			}
		} else {
			Employee employeeUpdate = employeeService.findByUserEmployee(result);
			ticket.setEmployeeAssign(employeeUpdate);
			ticket.setEmployeeUserAssign(employeeUpdate.getUserEmployee());
			employeeUpdate.getTicketMapEmployee().put(id, ticket);
			employeeService.save(employeeUpdate);
		}
		return ticketService.save(ticket);
	}

	@Override
	public Ticket moveTicket(long id, String nameTeam) {
		String nameTeamFinal = nameTeam.substring(1, nameTeam.length() - 1);
		Team team = teamService.findByNameTeam(nameTeamFinal);
		Ticket ticket = ticketService.findById(id);
		Employee employeeQuit = ticket.getEmployeeAssign();
		Team teamQuit = ticket.getTeamAssign();
		if (!ObjectUtils.isEmpty(employeeQuit)) employeeService.deleteTicket(employeeQuit, ticket);
		teamService.deleteTicket(teamQuit, ticket);
		LocalDateTime currentDate = LocalDateTime.now();
        String employeeQuitMssg = "";
        if (!ObjectUtils.isEmpty(employeeQuit)) employeeQuitMssg = employeeQuit.getUserEmployee();
		Message message = messageService.messageMove(Timestamp.valueOf(currentDate), employeeQuitMssg, team.getNameTeam() );
		return ticketService.moveTicket(ticket, team, message);
	}

	@Override
	public Boolean deleteTicket(long id, Team team) {
		Ticket ticket = ticketService.findById(id);
		if ((!StringUtils.isEmpty(ticket.getEmployeeUserAssign()))
				&& (!ObjectUtils.isEmpty(ticket.getEmployeeAssign())))
			employeeService.deleteTicket(ticket.getEmployeeAssign(), ticket);
		teamService.deleteTicket(team, ticket);
		return ticketService.delete(ticket);
	}

	@Override
	public Project assignProject(long id, String user) {
		String result = user.substring(1, user.length() - 1);
		Project project = projectService.findById(id);
		if (result.equalsIgnoreCase("Sin asignar")) {
			if (!ObjectUtils.isEmpty(project.getEmployeeAssign())) {
				Employee employeeQuit = project.getEmployeeAssign();
				employeeQuit.getProjectMapEmployee().remove(project.getIdProject());
				project.setEmployeeAssign(null);
				project.setEmployeeUserAssign("");
				employeeService.save(employeeQuit);
			}
		} else {
			Employee employeeUpdate = employeeService.findByUserEmployee(result);
			project.setEmployeeAssign(employeeUpdate);
			project.setEmployeeUserAssign(employeeUpdate.getUserEmployee());
			employeeUpdate.getProjectMapEmployee().put(id, project);
			employeeService.save(employeeUpdate);
		}
		return projectService.save(project);
	}

	@Override
	public Boolean deleteProject(long id) {
		Project project = projectService.findById(id);
		Team teamAssign = teamService.findByNameTeam(project.getTeamNameAssign());
		if ((!StringUtils.isEmpty(project.getEmployeeUserAssign()))
				&& (!ObjectUtils.isEmpty(project.getEmployeeAssign())))
			employeeService.deleteProject(project.getEmployeeAssign(), project);
		teamService.deleteProject(teamAssign, project);
		return projectService.delete(project);
	}

	@Override
	public Project save(Project updatedProject, Employee employee) {
		Team teamAssign = teamService.findByNameTeam(updatedProject.getTeamNameAssign());
		Release release = releaseService.findByNameRelease(updatedProject.getReleaseName());
		Project updated = projectService.mapNewProject(updatedProject, employee, teamAssign, release);
		teamAssign.getProjectMapTeam().put(updatedProject.getIdProject(), updatedProject);
		release.getProjectMap().put(updated.getIdProject(), updated);
		return projectService.save(updated);
	}

	@Override
	public Boolean deleteRelease(long id) {
		Release release = releaseService.findByIdRelease(id);
		for(Long deleteProjectId : release.getProjectMap().keySet())
			deleteProject(deleteProjectId);
		return releaseService.delete(release);
	}

	@Override
	public WorkLoad calculateWorkLoad(Team team) {
		WorkLoad workLoad = new WorkLoad();
		List<WorkPerEmployee> workPerEmployee = new ArrayList<>();
		for(Employee employee: team.getEmployeeMap().values()) {
			workPerEmployee.add(employeeService.calculateWorkLoad(employee));
		}
		workLoad.setListWorkPerEmployee(workPerEmployee);
		return workLoad;
	}
	
	public List<String> loadPerEmployee(WorkLoad workload){
		List<String> list = new ArrayList<String>();
		for (WorkPerEmployee perEmplpoyee : workload.getListWorkPerEmployee())
			list.add(('"'+ (perEmplpoyee.getLoad().toString()) +'"'));
		return list;
	}
	public List<String> userPerEmployee(WorkLoad workload){
		List<String> list = new ArrayList<String>();
		for (WorkPerEmployee perEmplpoyee : workload.getListWorkPerEmployee())
			list.add('"'+ perEmplpoyee.getUserEmployee()+'"');
		return list;
	}

	@Override
	public TableTeam calculateTableTeam(Team team) {
		TableTeam tableTeam = new TableTeam();
		List<TablePerEmployee> tablePerEmployee = new ArrayList<>();
		for(Employee employee: team.getEmployeeMap().values()) {
			TablePerEmployee table = new TablePerEmployee();
			table.setUserEmployee(employee.getUserEmployee());
			table.setIdEmployee(employee.getEmployeeId());
			table.setFinishTickets(ticketService.findCountTicketsFinishByEmployee(employee));
			table.setFinishProjects(projectService.findCountProjectsFinishByEmployee(employee));
			tablePerEmployee.add(table);
		}
		tableTeam.setListTablePerEmployee(tablePerEmployee);
		return tableTeam;
	}
	
	public String getEmployeesTeam(TableTeam tableTeam) throws JsonProcessingException{
		List<String> employees = new ArrayList<>();
		for(TablePerEmployee table: tableTeam.getListTablePerEmployee()) {
			employees.add(table.getUserEmployee());
		}
		ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(employees);
	}
	
	public String getEmployeesTicketsResolved(TableTeam tableTeam) throws JsonProcessingException {
		List<Integer> tck = new ArrayList<>();
		for(TablePerEmployee table: tableTeam.getListTablePerEmployee()) {
			tck.add(table.getFinishTickets());
		}
		ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(tck);
	}
	
	public String getemployeesProjectsResolved(TableTeam tableTeam) throws JsonProcessingException {
		List<Integer> prj = new ArrayList<>();
		for(TablePerEmployee table: tableTeam.getListTablePerEmployee()) {
			prj.add(table.getFinishProjects());
		}
		ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(prj);
	}
	
	@Override
	public String getLastSixMonths() throws JsonProcessingException {
	    List<String> months = new ArrayList<>();
	    LocalDate currentDate = LocalDate.now();
	    Locale spanishLocale = new Locale(Constants.LANGUAGE_ES);

	    for (int i = 5; i >= 0; i--) {
	        String month = currentDate.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL, spanishLocale);
	        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
	        months.add(month);
	    }
	    
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(months);
	}

	@Override
	public List<EmployeePerTeam> getEmployeesPerTeam(Collection<Employee> values, Employee employeesession) {
		List<EmployeePerTeam> employeesPerTeam = new ArrayList<>();
		for(Employee employee: values) {
			if(!employee.equals(employeesession)) {
				EmployeePerTeam employeePerTeam = new EmployeePerTeam();
				employeePerTeam.setUserEmployee(employee.getUserEmployee());
				employeePerTeam.setEmployeePosition(employee.getEmployeePosition());
				employeesPerTeam.add(employeePerTeam);
			}
		}
		
		return employeesPerTeam;
	}

	@Override
	public Employee editEmployee(long id, EmployeeDTO employeeDTO) {
		User user = userRepository.findByName(employeeDTO.getUsername()).get();
		user.setRoles(Arrays.asList(employeeDTO.getRole()));
		Employee employee = employeeService.findByEmployeeId(id);
		employee.setEmployeeDni(employeeDTO.getEmployeeDni());
		employee.setEmployeeLastName(employeeDTO.getEmployeeLastName());
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setEmployeePosition(employeeDTO.getEmployeePosition());
		employee.setBirthDate(employeeDTO.getBirthDate());
		if(!employee.getNameTeam().equals(employeeDTO.getNameTeam())) {
			desasignarTicketsAbiertos(employee);
			desasignarProyectosAbiertos(employee);
			employee.setNameTeam(employeeDTO.getNameTeam());
			employee.setTeam(teamService.findByNameTeam(employeeDTO.getNameTeam()));
		}
		return employeeService.save(employee);
	}

	private void desasignarTicketsAbiertos(Employee employee) {
	    
	    Collection<Ticket> assignedTickets = employee.getTicketMapEmployee().values();

	    List<Ticket> ticketsToUnassign = assignedTickets.stream()
	            .filter(ticket -> ticket.getTeamAssign().equals(employee.getTeam())) 
	            .filter(ticket -> !ticket.getStatusTicket().equalsIgnoreCase(Constants.STATUS_CLOSED) 
	                    && !ticket.getStatusTicket().equalsIgnoreCase(Constants.STATUS_RESOLVED)) 
	            .collect(Collectors.toList());

	    for (Ticket ticket : ticketsToUnassign) {
	        ticket.setEmployeeAssign(null);
	        ticket.setEmployeeUserAssign(null);
	        ticketService.save(ticket); 
	    }
	}

	private void desasignarProyectosAbiertos(Employee employee) {
	    
	    Collection<Project> assignedProjects = employee.getProjectMapEmployee().values();

	    List<Project> projectsToUnassign = assignedProjects.stream()
	            .filter(project -> project.getTeamAssign().equals(employee.getTeam()))
	            .filter(project -> !project.getStatusProject().equalsIgnoreCase(Constants.STATUS_CLOSED)
	                    && !project.getStatusProject().equalsIgnoreCase(Constants.STATUS_FINISH))
	            .collect(Collectors.toList());

	    for (Project project : projectsToUnassign) {
	        project.setEmployeeAssign(null); 
	        project.setEmployeeUserAssign(null);
	        projectService.save(project); 
	    }
	}

	@Override
	public Employee deleteUser(long id) {
		Employee employee = employeeService.findByEmployeeId(id);
		desasignarTicketsAbiertos(employee);
		desasignarProyectosAbiertos(employee);
		employee.setNameTeam(null);
		employee.setTeam(null);
		employee.setEmployeeActive(false);
		return employeeService.save(employee);
	}

    @Override
    public User findByUserName(String name) {
        return userRepository.findByName(name).get();
    }

	

}
