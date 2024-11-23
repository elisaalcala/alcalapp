package com.app.alcala.web.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.entities.User;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.ProjectService;
import com.app.alcala.service.ReleaseService;
import com.app.alcala.service.TeamService;
import com.app.alcala.service.TicketService;
import com.app.alcala.utils.Constants;
import com.app.alcala.web.model.EmployeeDTO;
import com.app.alcala.web.model.EmployeePerTeam;
import com.app.alcala.web.model.ProjectTable;
import com.app.alcala.web.model.TableTeam;
import com.app.alcala.web.model.TeamDTO;
import com.app.alcala.web.model.UserAndEmployeeDTO;
import com.app.alcala.web.model.WorkLoad;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AlcalappController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private ReleaseService releaseService;
	@Autowired
	private AlcalappService alcalappService;

	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error, HttpServletRequest request,
			Model model) {

		if (error != null)
			model.addAttribute("error", "  Credenciales incorrectos");

		return "login";
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		String redirectUrl = "/login";
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

    @RequestMapping("/error")
    public String handleError() {
        // Retorna la vista para mostrar los errores
        return "error"; // Asegúrate de tener el archivo JSP en esta ubicación
    }

	@GetMapping("/dailywork")
	public String dailyWorkPage(Model model, HttpServletRequest request) throws JsonProcessingException {

		String name = request.getUserPrincipal().getName();
		Employee employee = employeeService.findByUserEmployee(name);
        User user = alcalappService.findByUserName(name);
		Team team = teamService.findByNameTeam(employee.getNameTeam());
		List<Team> createTicketTeamsList = teamService.findTeamsToSendTicket(team);
		List<Release> releasesOpen = releaseService.findByReleasesOpen();

		HttpSession session = request.getSession();
		session.setAttribute("employee", employee);
        

		session.setAttribute("team", team);
		session.setAttribute("createTicketTeamsList", createTicketTeamsList);
		session.setAttribute("releasesOpen", releasesOpen);
		session.setAttribute("rolesList", Constants.ROLES);

		List<ProjectTable> projectsTables = alcalappService.findProjectsPerRelease(team);
		List<Ticket> ticketsNotCompleted = ticketService.findticketsNotCompletedByTeam(team);
		List<Ticket> ticketsBlocked = ticketService.findticketsBlockedByTeam(team);
		List<Ticket> ticketsCompletedByTeam = ticketService.findticketsCompletedByTeam(team);
		List<Ticket> ticketsCompletedByTeamPro = ticketService.findticketsCompletedByTeamPro(team);
		List<Project> projectsCompletedByTeam = projectService.findprojectsCompletedByTeam(team);

		List<Ticket> ticketsReadyToDeploy = ticketService.findTicketsReadyByTeam(team);
		List<Project> projectsReadyToDeploy = projectService.findProjectsReadyByTeam(team);

		WorkLoad workLoad = alcalappService.calculateWorkLoad(team);
		List<String> userPerEmployee = alcalappService.userPerEmployee(workLoad);
		List<String> loadPerEmployee = alcalappService.loadPerEmployee(workLoad);
		TableTeam tableTeam = alcalappService.calculateTableTeam(team);
		String employeesTeam = alcalappService.getEmployeesTeam(tableTeam);
		String employeesTicketsResolved = alcalappService.getEmployeesTicketsResolved(tableTeam);
		String employeesProjectsResolved = alcalappService.getemployeesProjectsResolved(tableTeam);
		String ticketsCompletedByTeamJson = ticketService.getMinTickedResolvedPerMonth(ticketsCompletedByTeam);
		String ticketsCompletedByTeamJsonPro = ticketService.getMinTickedResolvedPerMonth(ticketsCompletedByTeamPro);
		String projectsCompletedByTeamJson = projectService.getHoursProjectResolvedPerMonth(projectsCompletedByTeam);
		String monthsJson = alcalappService.getLastSixMonths();

        model.addAttribute("role", user.getRoles().get(0));
		model.addAttribute("createTicketTeamsList", createTicketTeamsList);
		model.addAttribute("employee", employee);
		model.addAttribute("team", team);
		model.addAttribute("projectsTables", projectsTables);
		model.addAttribute("ticketsNotCompleted", ticketsNotCompleted);
		model.addAttribute("ticketsBlocked", ticketsBlocked);
		model.addAttribute("ticketsReadyToDeploy", ticketsReadyToDeploy);
		model.addAttribute("projectsReadyToDeploy", projectsReadyToDeploy);
		model.addAttribute("workLoad", workLoad);
		model.addAttribute("userPerEmployee", userPerEmployee);
		model.addAttribute("loadPerEmployee", loadPerEmployee);
		model.addAttribute("employeesTeam", employeesTeam);
		model.addAttribute("employeesTicketsResolved", employeesTicketsResolved);
		model.addAttribute("employeesProjectsResolved", employeesProjectsResolved);
		model.addAttribute("ticketsCompletedByTeamJson", ticketsCompletedByTeamJson);
		model.addAttribute("ticketsCompletedByTeamJsonPro", ticketsCompletedByTeamJsonPro);
		model.addAttribute("projectsCompletedByTeamJson", projectsCompletedByTeamJson);
		model.addAttribute("monthsJson", monthsJson);
		model.addAttribute("page", "TRABAJO DIARIO");

		return "dailywork";
	}

	@GetMapping("/mywork")
	public String myWorkPage(Model model, HttpServletRequest request) throws JsonProcessingException {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		List<Project> projectsNotCompleted = projectService.findProjectsNotCompletedByEmployee(employee);
		List<Ticket> ticketsNotCompleted = ticketService.findTicketsNotCompletedByEmployee(employee);
		List<Project> projectsReady = projectService.findProjectsReadyByEmployee(employee);
		List<Ticket> ticketsReady = ticketService.findTicketsReadyByEmployee(employee);
		List<Project> projectsFinish = projectService.findProjectsFinishByEmployee(employee);
		List<Ticket> ticketsFinish = ticketService.findTicketsFinishByEmployee(employee);

		String labelsJson = alcalappService.getLastSixMonths();
		String tckDataJson = ticketService.getTickedResolvedPerMonth(ticketsFinish);
		String prjDataJson = projectService.getProjectResolvedPerMonth(projectsFinish);

		String tckDataLineJson = ticketService.getMinTickedResolvedPerMonth(ticketsFinish);
		String prjDataLineJson = projectService.getHoursProjectResolvedPerMonth(projectsFinish);

		String tckDataCreationClosedJson = ticketService.getByEmployeeCreationAndStatusClosedPerMonth(employee);
		String tckDataCreationResolvedJson = ticketService.getByEmployeeCreationAndStatusResolvedPerMonth(employee);

		model.addAttribute("labelsJson", labelsJson);
		model.addAttribute("tckDataJson", tckDataJson);
		model.addAttribute("prjDataJson", prjDataJson);
		model.addAttribute("tckDataLineJson", tckDataLineJson);
		model.addAttribute("prjDataLineJson", prjDataLineJson);

		model.addAttribute("tckDataCreationClosedJson", tckDataCreationClosedJson);
		model.addAttribute("tckDataCreationResolvedJson", tckDataCreationResolvedJson);

		model.addAttribute("projectsNotCompleted", projectsNotCompleted);
		model.addAttribute("ticketsNotCompleted", ticketsNotCompleted);
		model.addAttribute("projectsReady", projectsReady);
		model.addAttribute("ticketsReady", ticketsReady);
		model.addAttribute("projectsFinish", projectsFinish);
		model.addAttribute("ticketsFinish", ticketsFinish);
		model.addAttribute("page", "MI TRABAJO");

		return "mywork";
	}

	@GetMapping("/profile")
	public String profilePage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Team team = (Team) session.getAttribute("team");
		Employee employee = (Employee) session.getAttribute("employee");

		List<EmployeePerTeam> employeesPerTeam = alcalappService.getEmployeesPerTeam(team.getEmployeeMap().values(),
				employee);
		List<Ticket> recomendations = ticketService.findByEmployeeAssignOrderByModifyDateDesc(employee);

		model.addAttribute("page", "MI PERFIL");
		model.addAttribute("employeesPerTeam", employeesPerTeam);
		model.addAttribute("recomendations", recomendations);

		return "profile";
	}

	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody UserAndEmployeeDTO userAndEmployee) {

		alcalappService.createUserAndEmployee(userAndEmployee.getUser(), userAndEmployee.getEmployee());

		return ResponseEntity.ok("Usuario y empleado creados con éxito");
	}
	
	@PostMapping("/createTeam")
	public ResponseEntity<String> createTeam(@RequestBody TeamDTO team) {

		teamService.createTeam(team);

		return ResponseEntity.ok("Equipo creado con éxito");
	}
	
	@GetMapping("/gestion")
	public String gestion(Model model) {
		List<Team> teams = teamService.findAll();
		model.addAttribute("teams", teams);
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("page", "GESTION");
		return "gestion";
	}
	
	@GetMapping("/teams/{id}")
	public String teamPage(Model model, @PathVariable long id) {

		Team team = teamService.findByIdTeam(id);
		Collection<Employee> empleados = team.getEmployeeMap().values();
		model.addAttribute("team", team);
		model.addAttribute("empleados", empleados);
		model.addAttribute("borrado", empleados.isEmpty());
		
		return "team";
	}
	
	@PutMapping("/teams/{id}/edit")
	public ResponseEntity<String> updateTeam(@PathVariable long id, @RequestBody TeamDTO teamDTO) {

		Team team = teamService.editTeam(id, teamDTO);
		String redirectUrl = "/teams/" + team.getIdTeam();
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}
	
	@DeleteMapping("/teams/{id}/delete")
	public ResponseEntity<String> deleteTeam(@PathVariable long id) {
		
		teamService.delete(id);
		String redirectUrl = "/gestion";
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}
	
	
	@GetMapping("/users/{id}")
	public String userPage(Model model, HttpServletRequest request, @PathVariable long id) {

        String name = request.getUserPrincipal().getName();
        User user = alcalappService.findByUserName(name);
        model.addAttribute("role", user.getRoles().get(0));
        model.addAttribute("sessionUsername", name);

		Employee employeeSelect = employeeService.findByEmployeeId(id);
		model.addAttribute("employeeSelect", employeeSelect);
		
		return "user";
	}
	
	@PutMapping("/users/{id}/edit")
	public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO, Model model) {

		Employee employee = alcalappService.editEmployee(id, employeeDTO);
		String redirectUrl = "/users/" + employee.getEmployeeId();
		
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}
	
	@PutMapping("/users/{id}/delete")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		
		alcalappService.deleteUser(id);
		String redirectUrl = "/users";
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

}