package com.app.alcala.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.TicketRepository;
import com.app.alcala.service.MessageService;
import com.app.alcala.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {

	@Mock
	private TicketRepository ticketRepository;

	@Mock
	private MessageService messageService;

	@InjectMocks
	private TicketServiceImpl ticketService;

	@Test
	void testEditTicket() {
		Ticket ticket = new Ticket();
		ticket.setIdTicket(1L);
		ticket.setTitleTicket("Old Title");
		ticket.setDescriptionTicket("Old Description");
		ticket.setPriorityTicket("High");
		ticket.setEnvironmentTicket("Production");
		ticket.setStatusTicket("In Progress");

		Ticket updatedTicket = new Ticket();
		updatedTicket.setTitleTicket("New Title");
		updatedTicket.setDescriptionTicket("New Description");
		updatedTicket.setPriorityTicket("Low");
		updatedTicket.setEnvironmentTicket("Development");
		updatedTicket.setStatusTicket("Closed");

		Employee employee = new Employee();
		employee.setUserEmployee("JohnDoe");

		when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
		when(messageService.messageFinish(any(Timestamp.class), eq(employee.getUserEmployee())))
				.thenReturn(new Message());
		when(ticketRepository.save(ticket)).thenReturn(ticket);

		Ticket result = ticketService.editTicket(1L, updatedTicket, employee);

		assertEquals("New Title", result.getTitleTicket());
		assertEquals("New Description", result.getDescriptionTicket());
		assertEquals("Low", result.getPriorityTicket());
		assertEquals("Development", result.getEnvironmentTicket());
		assertEquals("Closed", result.getStatusTicket());
		assertNotNull(result.getFinishDate());
		verify(ticketRepository, times(1)).save(ticket);
	}

	@Test
	void testSave() {
		Ticket ticket = new Ticket();

		when(ticketRepository.save(ticket)).thenReturn(ticket);

		Ticket result = ticketService.save(ticket);

		assertEquals(ticket, result);
		verify(ticketRepository, times(1)).save(ticket);
	}

	@Test
	void testFindById() {
		Ticket ticket = new Ticket();
		ticket.setIdTicket(1L);

		when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

		Ticket result = ticketService.findById(1L);

		assertEquals(ticket, result);
	}

	@Test
	void testFindAll() {
		List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());

		when(ticketRepository.findAll()).thenReturn(tickets);

		List<Ticket> result = ticketService.findAll();

		assertEquals(2, result.size());
		assertTrue(result.containsAll(tickets));
	}

	@Test
	void testAssignTicket() {
		Ticket ticket = new Ticket();
		ticket.setIdTicket(1L);
		Employee employee = new Employee();
		employee.setUserEmployee("user");

		when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
		when(ticketRepository.save(ticket)).thenReturn(ticket);

		Ticket result = ticketService.assignTicket(1L, employee);

		assertEquals(employee, result.getEmployeeAssign());
		assertEquals(employee.getUserEmployee(), result.getEmployeeUserAssign());
		verify(ticketRepository, times(1)).save(ticket);
	}

	@Test
	void testMapNewTicket() {
		Ticket updatedTicket = new Ticket();
		updatedTicket.setIdTicket(1L);
		updatedTicket.setTitleTicket("New Ticket");

		Employee employee = new Employee();
		employee.setUserEmployee("JohnDoe");

		Team team = new Team();
		team.setNameTeam("Team 1");

		Team teamAssign = new Team();
		teamAssign.setNameTeam("Team 2");

		Message message = new Message();
		Message messageAssign = new Message();

		when(ticketRepository.save(updatedTicket)).thenReturn(updatedTicket);

		Ticket result = ticketService.mapNewTicket(updatedTicket, employee, team, teamAssign, message, messageAssign);

		assertEquals(employee, result.getEmployeeCreation());
		assertNotNull(result.getEmployeeUserCreation());
		assertEquals(team, result.getTeamCreation());
		assertEquals("Team 1", result.getTeamNameCreation());
		assertNotNull(result.getInitialDate());
		assertNotNull(result.getModifyDate());
		assertEquals(teamAssign, result.getTeamAssign());
		assertEquals("Backlog", result.getStatusTicket());
		assertNotNull(result.getMessageTicket());
		assertEquals(2, result.getMessageTicket().size());
		assertEquals("TCK 1", result.getNameTicket());
		verify(ticketRepository, times(1)).save(updatedTicket);
	}

	@Test
	void testMoveTicket() {
		Ticket ticket = new Ticket();
		ticket.setMessageTicket(new ArrayList<>());
		Team team = new Team();
		Message message = new Message();

		when(ticketRepository.save(ticket)).thenReturn(ticket);

		Ticket result = ticketService.moveTicket(ticket, team, message);

		assertEquals(team, result.getTeamAssign());
		assertEquals("Backlog", result.getStatusTicket());
		assertNotNull(result.getInitialDate());
		assertNotNull(result.getModifyDate());
		assertEquals(1, result.getMessageTicket().size());
		verify(ticketRepository, times(1)).save(ticket);
	}

	@Test
	void testDelete() {
		Ticket ticket = new Ticket();

		assertTrue(ticketService.delete(ticket));
		verify(ticketRepository, times(1)).delete(ticket);
	}

	@Test
	void testGetTickedResolvedPerMonth() throws JsonProcessingException {

		Ticket ticket1 = new Ticket();
		ticket1.setIdTicket(1L);
		ticket1.setFinishDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(1)));

		Ticket ticket2 = new Ticket();
		ticket2.setIdTicket(2L);
		ticket2.setFinishDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(3)));

		Ticket ticket3 = new Ticket();
		ticket3.setIdTicket(3L);
		ticket3.setFinishDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(7)));

		List<Ticket> tickets = Arrays.asList(ticket1, ticket2, ticket3);

		String jsonResult = ticketService.getTickedResolvedPerMonth(tickets);

		List<Integer> expected = Arrays.asList(0, 0, 1, 0, 1, 0);
		List<Integer> result = new ObjectMapper().readValue(jsonResult, List.class);

		assertEquals(expected, result, "El conteo de tickets no es correcto para los últimos seis meses.");
	}

	@Test
	void testGetMinTickedResolvedPerMonth() throws JsonProcessingException {

		Ticket ticket1 = new Ticket();
		ticket1.setIdTicket(1L);
		ticket1.setInitialDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(1)));
		ticket1.setFinishDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(1).plusHours(5)));

		Ticket ticket2 = new Ticket();
		ticket2.setIdTicket(2L);
		ticket2.setInitialDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(3)));
		ticket2.setFinishDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(3).plusHours(2)));

		Ticket ticket3 = new Ticket();
		ticket3.setIdTicket(3L);
		ticket3.setInitialDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(7)));
		ticket3.setFinishDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(7).plusHours(1)));

		List<Ticket> tickets = Arrays.asList(ticket1, ticket2, ticket3);

		String jsonResult = ticketService.getMinTickedResolvedPerMonth(tickets);

		List<Double> expected = Arrays.asList(0.0, 0.0, 120.0, 0.0, 300.0, 0.0);
		List<Double> result = new ObjectMapper().readValue(jsonResult, List.class);

		assertEquals(expected, result, "El promedio de minutos no es correcto para los últimos seis meses.");
	}

	@Test
	void testGetTickedByEmployeeCreationPerMonth() throws JsonProcessingException {

		Employee employee = new Employee();
		employee.setEmployeeId(1L);
		employee.setEmployeeName("Empleado Prueba");

		Ticket ticket1 = new Ticket();
		ticket1.setIdTicket(1L);
		ticket1.setInitialDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(1)));

		Ticket ticket2 = new Ticket();
		ticket2.setIdTicket(2L);
		ticket2.setInitialDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(3)));

		Ticket ticket3 = new Ticket();
		ticket3.setIdTicket(3L);
		ticket3.setInitialDate(Timestamp.valueOf(LocalDateTime.now().minusMonths(7)));

		List<Ticket> tickets = Arrays.asList(ticket1, ticket2, ticket3);

		when(ticketRepository.findByEmployeeCreation(employee)).thenReturn(tickets);

		String jsonResult = ticketService.getByEmployeeCreationPerMonth(employee);

		List<Integer> expected = Arrays.asList(0, 0, 1, 0, 1, 0);
		List<Integer> result = new ObjectMapper().readValue(jsonResult, List.class);

		assertEquals(expected, result,
				"El conteo de tickets creados por empleado no es correcto para los últimos seis meses.");
	}

	@Test
	public void testFindByEmployeeAssignOrderByModifyDateDesc() {
		Employee employee = new Employee();

		Ticket ticket1 = new Ticket();
		ticket1.setModifyDate(Timestamp.valueOf(LocalDateTime.of(2023, 10, 25, 15, 0)));

		Ticket ticket2 = new Ticket();
		ticket2.setModifyDate(Timestamp.valueOf(LocalDateTime.of(2023, 10, 20, 12, 0)));

		List<Ticket> expectedTickets = Arrays.asList(ticket1, ticket2);

		when(ticketRepository.findByEmployeeAssignOrderByModifyDateDesc(employee)).thenReturn(expectedTickets);

		List<Ticket> result = ticketService.findByEmployeeAssignOrderByModifyDateDesc(employee);

		assertEquals(expectedTickets, result,
				"La lista de tickets debe estar ordenada por fecha de modificación descendente");
		verify(ticketRepository).findByEmployeeAssignOrderByModifyDateDesc(employee);
	}

	@Test
	public void testFindCountTicketsReadyByEmployee() {
		Employee employee = new Employee();
		int expectedCount = 5;

		when(ticketRepository.countByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_READY))
				.thenReturn((long) expectedCount);

		int result = ticketService.findCountTicketsReadyByEmployee(employee);

		assertEquals(expectedCount, result,
				"El conteo de tickets en estado 'ready' debe coincidir con el valor esperado");
		verify(ticketRepository).countByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_READY);
	}

	@Test
	public void testFindCountTicketsNotCompletedByEmployee() {
		Employee employee = new Employee();
		int expectedCount = 3;

		when(ticketRepository.countByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_NOT_COMPLETED))
				.thenReturn((long) expectedCount);

		int result = ticketService.findCountTicketsNotCompletedByEmployee(employee);

		assertEquals(expectedCount, result,
				"El conteo de tickets en estado 'not completed' debe coincidir con el valor esperado");
		verify(ticketRepository).countByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_NOT_COMPLETED);
	}

	@Test
	public void testGetByEmployeeCreationAndStatusClosedPerMonth() throws JsonProcessingException {
		Employee employee = new Employee();
		when(ticketRepository.findByEmployeeCreationAndStatusTicketIn(eq(employee), anyList()))
				.thenReturn(Collections.singletonList(new Ticket()));
		String result = ticketService.getByEmployeeCreationAndStatusClosedPerMonth(employee);
		assertNotNull(result);
		verify(ticketRepository).findByEmployeeCreationAndStatusTicketIn(employee,
				Arrays.asList(Constants.STATUS_CLOSED));
	}

	@Test
	public void testGetByEmployeeCreationAndStatusResolvedPerMonth() throws JsonProcessingException {
		Employee employee = new Employee();
		when(ticketRepository.findByEmployeeCreationAndStatusTicketIn(eq(employee), anyList()))
				.thenReturn(Collections.singletonList(new Ticket()));
		String result = ticketService.getByEmployeeCreationAndStatusResolvedPerMonth(employee);
		assertNotNull(result);
		verify(ticketRepository).findByEmployeeCreationAndStatusTicketIn(employee, Arrays.asList(Constants.RESOLVED));
	}

	@Test
	public void testFindticketsCompletedByTeam() {
		Team team = new Team();
		when(ticketRepository.findByTeamAssignAndStatusTicketInAndEnvironmentTicketNot(eq(team),
				eq(Constants.STATUS_COMPLETED_TICKET), eq(Constants.PRO)))
				.thenReturn(Collections.singletonList(new Ticket()));
		List<Ticket> result = ticketService.findticketsCompletedByTeam(team);
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(ticketRepository).findByTeamAssignAndStatusTicketInAndEnvironmentTicketNot(team,
				Constants.STATUS_COMPLETED_TICKET, Constants.PRO);
	}

	@Test
	public void testFindticketsCompletedByTeamPro() {
		Team team = new Team();
		when(ticketRepository.findByTeamAssignAndStatusTicketInAndEnvironmentTicket(eq(team),
				eq(Constants.STATUS_COMPLETED_TICKET), eq(Constants.PRO)))
				.thenReturn(Collections.singletonList(new Ticket()));
		List<Ticket> result = ticketService.findticketsCompletedByTeamPro(team);
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(ticketRepository).findByTeamAssignAndStatusTicketInAndEnvironmentTicket(team,
				Constants.STATUS_COMPLETED_TICKET, Constants.PRO);
	}

	@Test
	public void testFindTicketsFinishByEmployee() {
		Employee employee = new Employee();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		List<Ticket> expectedTickets = List.of(ticket1, ticket2);
		when(ticketRepository.findByEmployeeAssignAndStatusTicketIn(eq(employee),
				eq(Constants.STATUS_COMPLETED_TICKET))).thenReturn(expectedTickets);
		List<Ticket> result = ticketService.findTicketsFinishByEmployee(employee);
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(ticketRepository).findByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_COMPLETED_TICKET);
	}

	@Test
	public void testFindCountTicketsFinishByEmployee() {
		Employee employee = new Employee();
		int expectedCount = 3;
		when(ticketRepository.countByEmployeeAssignAndStatusTicketIn(eq(employee),
				eq(Constants.STATUS_COMPLETED_TICKET))).thenReturn((long) expectedCount);
		Integer result = ticketService.findCountTicketsFinishByEmployee(employee);
		assertNotNull(result);
		assertEquals(expectedCount, result);
		verify(ticketRepository).countByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_COMPLETED_TICKET);
	}

	@Test
	public void testFindTicketsReadyByEmployee() {
		Employee employee = new Employee();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		List<Ticket> expectedTickets = List.of(ticket1, ticket2);
		when(ticketRepository.findByEmployeeAssignAndStatusTicketIn(eq(employee), eq(Constants.STATUS_READY)))
				.thenReturn(expectedTickets);
		List<Ticket> result = ticketService.findTicketsReadyByEmployee(employee);
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(ticketRepository).findByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_READY);
	}

	@Test
	public void testFindTicketsReadyByTeam() {
		Team team = new Team();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		List<Ticket> expectedTickets = List.of(ticket1, ticket2);
		when(ticketRepository.findByTeamAssignAndStatusTicketIn(eq(team), eq(Constants.STATUS_READY)))
				.thenReturn(expectedTickets);
		List<Ticket> result = ticketService.findTicketsReadyByTeam(team);
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(ticketRepository).findByTeamAssignAndStatusTicketIn(team, Constants.STATUS_READY);
	}

	@Test
	public void testFindticketsNotCompletedByTeam() {
		Team team = new Team();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		List<Ticket> expectedTickets = List.of(ticket1, ticket2);
		when(ticketRepository.findByTeamAssignAndStatusTicketIn(eq(team),
				eq(Arrays.asList(Constants.STATUS_BACKLOG, Constants.STATUS_IN_PROGRESS)))).thenReturn(expectedTickets);
		List<Ticket> result = ticketService.findticketsNotCompletedByTeam(team);
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(ticketRepository).findByTeamAssignAndStatusTicketIn(team,
				Arrays.asList(Constants.STATUS_BACKLOG, Constants.STATUS_IN_PROGRESS));
	}

	@Test
	public void testFindticketsBlockedByTeam() {
		Team team = new Team();
		Ticket ticket1 = new Ticket();
		List<Ticket> expectedTickets = Collections.singletonList(ticket1);
		when(ticketRepository.findByTeamAssignAndStatusTicketIn(eq(team), eq(Arrays.asList(Constants.STATUS_BLOCKED))))
				.thenReturn(expectedTickets);
		List<Ticket> result = ticketService.findticketsBlockedByTeam(team);
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(ticketRepository).findByTeamAssignAndStatusTicketIn(team, Arrays.asList(Constants.STATUS_BLOCKED));
	}

	@Test
	public void testFindTicketsNotCompletedByEmployee() {
		Employee employee = new Employee();
		Ticket ticket1 = new Ticket();
		List<Ticket> expectedTickets = Collections.singletonList(ticket1);
		when(ticketRepository.findByEmployeeAssignAndStatusTicketIn(eq(employee), eq(Constants.STATUS_NOT_COMPLETED)))
				.thenReturn(expectedTickets);
		List<Ticket> result = ticketService.findTicketsNotCompletedByEmployee(employee);
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(ticketRepository).findByEmployeeAssignAndStatusTicketIn(employee, Constants.STATUS_NOT_COMPLETED);
	}

}
