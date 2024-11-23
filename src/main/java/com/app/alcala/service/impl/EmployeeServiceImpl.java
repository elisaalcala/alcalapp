package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.EmployeeRepository;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.utils.Constants;
import com.app.alcala.web.model.WorkPerEmployee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee findByUserEmployee(String name) {
		return employeeRepository.findByUserEmployee(name).orElseThrow();
	}

	@Override
	public Employee findByEmployeeId(long id) {
		return employeeRepository.findByEmployeeId(id).orElseThrow();
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee deleteTicket(Employee employeeQuit, Ticket ticket) {
		employeeQuit.getTicketMapEmployee().remove(ticket.getIdTicket());
		return employeeRepository.save(employeeQuit);
	}

	@Override
	public Employee deleteProject(Employee employeeQuit, Project project) {
        employeeQuit.getProjectMapEmployee().remove(project.getIdProject());
        return employeeRepository.save(employeeQuit);
	}

	@Override
	public WorkPerEmployee calculateWorkLoad(Employee employee) {
		WorkPerEmployee workPerEmployee = new WorkPerEmployee();
		workPerEmployee.setIdEmployee(employee.getEmployeeId());
		workPerEmployee.setUserEmployee(employee.getUserEmployee());
		Integer load = 0;
		
		List<Project> projects = new ArrayList<>();
		for(Project project: employee.getProjectMapEmployee().values()) {
			if(project.getStatusProject().equalsIgnoreCase(Constants.STATUS_IN_PROGRESS)) {
				projects.add(project);
				load++;
			}
		}
		
		List<Ticket> tickets = new ArrayList<>();
		for(Ticket ticket: employee.getTicketMapEmployee().values()) {
			if(ticket.getStatusTicket().equalsIgnoreCase(Constants.STATUS_IN_PROGRESS)) {
				tickets.add(ticket);
				load++;
			}
		}
		workPerEmployee.setProjectInProgress(projects);
		workPerEmployee.setTicketInProgress(tickets);
		workPerEmployee.setLoad(load);
		
		return workPerEmployee;
	}

	@Override
	public void delete(Employee employeeDelete) {
		employeeRepository.delete(employeeDelete);
		
	}

	@Override
	public Employee createNewEmployee(Employee employeeNew, Team team) {

		LocalDateTime currentDate = LocalDateTime.now().withSecond(0).withNano(0);
		employeeNew.setHireDate(Timestamp.valueOf(currentDate));
		LocalDateTime date = employeeNew.getBirthDate().toLocalDateTime().withSecond(0).withNano(0);
		employeeNew.setBirthDate(Timestamp.valueOf(date));
		employeeNew.setProjectMapEmployee(new HashMap<>());
		employeeNew.setTicketMapEmployee(new HashMap<>());
		employeeNew.setTeam(team);
		employeeNew.setEmployeeActive(true);
		return save(employeeNew);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findByEmployeeActiveTrue();
	}




}
