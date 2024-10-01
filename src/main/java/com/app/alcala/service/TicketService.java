package com.app.alcala.service;

import java.util.List;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TicketService {


	Ticket save(Ticket updatedTicket);

	Ticket findById(long id);

	List<Ticket> findAll();

	List<Ticket> findTicketsNotCompletedByEmployee(Employee employee);

	List<Ticket> findticketsNotCompletedByTeam(Team team);

	List<Ticket> findTicketsReadyByEmployee(Employee employee);

	Ticket assignTicket(long id, Employee employee);

	Ticket mapNewTicket(Ticket updatedTicket, Employee employee, Team team, Team teamAssign, Message message, Message messageAssign);

	Ticket editTicket(long id, Ticket updatedTicket, Employee employee);

	Ticket moveTicket(Ticket ticket, Team team, Message message);

	Boolean delete(Ticket ticket);

	List<Ticket> findTicketsFinishByEmployee(Employee employee);

	Integer findCountTicketsReadyByEmployee(Employee employee);

	Integer findCountTicketsNotCompletedByEmployee(Employee employee);

	Integer findCountTicketsFinishByEmployee(Employee employee);

	String getTickedResolvedPerMonth(List<Ticket> ticketsFinish) throws JsonProcessingException;

	String getHoursTickedResolvedPerMonth(List<Ticket> ticketsFinish) throws JsonProcessingException;
	
	String getByEmployeeCreationPerMonth(Employee employee)throws JsonProcessingException;
	
	String getTickedByEmployeeCreationPerMonth(List<Ticket> ticketsCreation) throws JsonProcessingException;
	
	String getByEmployeeCreationAndStatusClosedPerMonth(Employee employee) throws JsonProcessingException;
	
	String getByEmployeeCreationAndStatusResolvedPerMonth(Employee employee) throws JsonProcessingException;

}
