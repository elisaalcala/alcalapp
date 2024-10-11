package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.TicketRepository;
import com.app.alcala.service.MessageService;
import com.app.alcala.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;

@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private MessageService messageService;

	public Ticket editTicket(long id, Ticket updatedTicket, Employee employee) {
		Ticket ticket = ticketRepository.findById(id).orElseThrow();

		if (!StringUtils.isNullOrEmpty(updatedTicket.getTitleTicket())) {
			ticket.setTitleTicket(updatedTicket.getTitleTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getDescriptionTicket())) {
			ticket.setDescriptionTicket(updatedTicket.getDescriptionTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getPriorityTicket())) {
			ticket.setPriorityTicket(updatedTicket.getPriorityTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getEnvironmentTicket())) {
			ticket.setEnvironmentTicket(updatedTicket.getEnvironmentTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getStatusTicket())) {
			// Si el estado estaba en Resuelto y se cambia a No resuelto, hay que eliminar
			// la fecha de finalizacion
			if (!isCloseOrFinish(updatedTicket) && isCloseOrFinish(ticket)) {
				ticket.setFinishDate(null);
				LocalDateTime currentDate = LocalDateTime.now();
				Message message = messageService.messageReOpen(Timestamp.valueOf(currentDate),employee.getUserEmployee() );
				if (ObjectUtils.isEmpty(ticket.getMessageTicket())) {
					List<Message> mensajes = new ArrayList<>();
					mensajes.add(message);
					ticket.setMessageTicket(mensajes);
				}else ticket.getMessageTicket().add(message);
				
				
			} else if (isCloseOrFinish(updatedTicket) && !isCloseOrFinish(ticket)) {
				LocalDateTime currentDate = LocalDateTime.now();
				ticket.setFinishDate(Timestamp.valueOf(currentDate));
				Message message = messageService.messageFinish(Timestamp.valueOf(currentDate),employee.getUserEmployee() );
				if (ObjectUtils.isEmpty(ticket.getMessageTicket())) {
					List<Message> mensajes = new ArrayList<>();
					mensajes.add(message);
					ticket.setMessageTicket(mensajes);
				}else ticket.getMessageTicket().add(message);
				
			}
			ticket.setStatusTicket(updatedTicket.getStatusTicket());
		}
		LocalDateTime currentDate = LocalDateTime.now();
		ticket.setModifyDate(Timestamp.valueOf(currentDate));
		return ticketRepository.save(ticket);
	}

	private boolean isCloseOrFinish(Ticket ticket) {
		return ticket.getStatusTicket().equalsIgnoreCase("Closed")
				|| ticket.getStatusTicket().equalsIgnoreCase("Resolved");
	}

	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	public Ticket findById(long id) {
		return ticketRepository.findById(id).orElseThrow();
	}

	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	public List<Ticket> findticketsNotCompletedByTeam(Team team) {
		return ticketRepository.findByTeamAssignAndStatusTicketIn(team,
				Arrays.asList("Backlog", "In progress"));
	}
	
	public List<Ticket> findticketsBlockedByTeam(Team team) {
		return ticketRepository.findByTeamAssignAndStatusTicketIn(team,
				Arrays.asList("Blocked"));
	}

	@Override
	public List<Ticket> findTicketsNotCompletedByEmployee(Employee employee) {
		return ticketRepository.findByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Backlog", "In progress", "Blocked"));
	}

	@Override
	public Integer findCountTicketsNotCompletedByEmployee(Employee employee) {
		Integer ticketCount = (int) ticketRepository.countByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Backlog", "In progress", "Blocked"));
		return ticketCount;
	}
	@Override
	public List<Ticket> findTicketsReadyByEmployee(Employee employee) {
		return ticketRepository.findByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Test", "Ready to UAT", "Ready to PRO"));
	}
	
	@Override
	public List<Ticket> findTicketsReadyByTeam(Team team) {
		return ticketRepository.findByTeamAssignAndStatusTicketIn(team,
				Arrays.asList("Test", "Ready to UAT", "Ready to PRO"));
	}
	
	@Override
	public Integer findCountTicketsReadyByEmployee(Employee employee) {
		Integer ticketCount = (int) ticketRepository.countByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Test", "Ready to UAT", "Ready to PRO"));
		return ticketCount;
	}

	@Override
	public Ticket assignTicket(long id, Employee employee) {
		Ticket ticket = ticketRepository.findById(id).orElseThrow();
		ticket.setEmployeeAssign(employee);
		ticket.setEmployeeUserAssign(employee.getUserEmployee());
		return ticketRepository.save(ticket);
	}

	public Ticket mapNewTicket(Ticket updatedTicket, Employee employee, Team team, Team teamAssign, Message message, Message messageAssign) {

		updatedTicket.setEmployeeCreation(employee);
		updatedTicket.setEmployeeUserCreation(employee.getUserEmployee());
		updatedTicket.setTeamCreation(team);
		updatedTicket.setTeamNameCreation(team.getNameTeam());
		LocalDateTime currentDate = LocalDateTime.now();
		updatedTicket.setInitialDate(Timestamp.valueOf(currentDate));
		updatedTicket.setModifyDate(Timestamp.valueOf(currentDate));
		updatedTicket.setTeamAssign(teamAssign);
		updatedTicket.setStatusTicket("Backlog");
		List<Message> mensajes = new ArrayList<>();
		mensajes.add(message);
		mensajes.add(messageAssign);
		updatedTicket.setMessageTicket(mensajes);
		save(updatedTicket);
		updatedTicket.setNameTicket("TCK " + updatedTicket.getIdTicket());
		return updatedTicket;
	}

	@Override
	public Ticket moveTicket(Ticket ticket, Team team, Message message) {
		ticket.setTeamAssign(team);
		ticket.setTeamNameAssign(team.getNameTeam());
		ticket.setEmployeeAssign(null);
		ticket.setEmployeeUserAssign(null);
		ticket.setStatusTicket("Backlog");
		LocalDateTime currentDate = LocalDateTime.now();
		ticket.setInitialDate(Timestamp.valueOf(currentDate));
		ticket.setModifyDate(Timestamp.valueOf(currentDate));
		ticket.getMessageTicket().add(message);
		return ticketRepository.save(ticket);
	}

	@Override
	public Boolean delete(Ticket ticket) {
		try {
			ticketRepository.delete(ticket);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Ticket> findTicketsFinishByEmployee(Employee employee) {
		return ticketRepository.findByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Closed", "Resolved"));
	}
	
	@Override
	public Integer findCountTicketsFinishByEmployee(Employee employee) {
		Integer ticketCount = (int) ticketRepository.countByEmployeeAssignAndStatusTicketIn(employee, Arrays.asList("Closed", "Resolved"));
		return ticketCount;
	}


	@Override
	public String getTickedResolvedPerMonth(List<Ticket> ticketsFinish) throws JsonProcessingException {
		
	    Map<String, Integer> monthTicketCountMap = new LinkedHashMap<>();
	    LocalDate now = LocalDate.now();
	    
	    for (int i = 5; i >= 0; i--) {
	        String monthName = now.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
	        monthTicketCountMap.put(monthName, 0);
	    }

	    for (Ticket ticket : ticketsFinish) {
	        if (ticket.getFinishDate() != null) {
	            LocalDate finishDate = ticket.getFinishDate().toLocalDateTime().toLocalDate();
	            
	            if (!finishDate.isBefore(now.minusMonths(5).withDayOfMonth(1)) && !finishDate.isAfter(now.withDayOfMonth(now.lengthOfMonth()))) {
	                String monthName = finishDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
	                
	                monthTicketCountMap.put(monthName, monthTicketCountMap.get(monthName) + 1);
	            }
	        }
	    }
	    List<Integer> ticketsPerMonth = new ArrayList<>(monthTicketCountMap.values());
	    
		ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(ticketsPerMonth);
	}

	@Override
	public String getMinTickedResolvedPerMonth(List<Ticket> ticketsFinish) throws JsonProcessingException {
	    Map<String, List<Long>> monthDurationMap = new LinkedHashMap<>();
	    LocalDate now = LocalDate.now();

	    for (int i = 5; i >= 0; i--) {
	        String monthName = now.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
	        monthDurationMap.put(monthName, new ArrayList<>());
	    }

	    for (Ticket ticket : ticketsFinish) {
	        if (ticket.getFinishDate() != null && ticket.getInitialDate() != null) {
	            LocalDateTime finishDateTime = ticket.getFinishDate().toLocalDateTime();
	            LocalDateTime initialDateTime = ticket.getInitialDate().toLocalDateTime();

	            if (!finishDateTime.toLocalDate().isBefore(now.minusMonths(5).withDayOfMonth(1)) &&
	                !finishDateTime.toLocalDate().isAfter(now.withDayOfMonth(now.lengthOfMonth()))) {

	                long minutesSpent = Duration.between(initialDateTime, finishDateTime).toMinutes();
	                String monthName = finishDateTime.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
	                monthDurationMap.get(monthName).add(minutesSpent);
	            }
	        }
	    }

	    Map<String, Double> monthAverageMap = new LinkedHashMap<>();
	    for (Map.Entry<String, List<Long>> entry : monthDurationMap.entrySet()) {
	        List<Long> durations = entry.getValue();
	        if (!durations.isEmpty()) {
	            double averageMinutes = durations.stream().mapToLong(Long::longValue).average().orElse(0.0);
	            monthAverageMap.put(entry.getKey(), averageMinutes);
	        } else {
	            monthAverageMap.put(entry.getKey(), 0.0);
	        }
	    }

	    List<Double> averagesPerMonth = new ArrayList<>(monthAverageMap.values());
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(averagesPerMonth);
	}

	@Override
	public String getByEmployeeCreationPerMonth(Employee employee)  throws JsonProcessingException{
		return getTickedByEmployeeCreationPerMonth(ticketRepository.findByEmployeeCreation(employee));
	}

	@Override
	public String getTickedByEmployeeCreationPerMonth(List<Ticket> ticketsCreation) throws JsonProcessingException {
	    
	    Map<String, Integer> monthTicketCountMap = new LinkedHashMap<>();
	    LocalDate now = LocalDate.now();

	    for (int i = 5; i >= 0; i--) {
	        String monthName = now.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
	        monthTicketCountMap.put(monthName, 0);
	    }

	    for (Ticket ticket : ticketsCreation) {
	        if (ticket.getInitialDate() != null) {
	            LocalDate creationDate = ticket.getInitialDate().toLocalDateTime().toLocalDate();

	            if (!creationDate.isBefore(now.minusMonths(5).withDayOfMonth(1)) &&
	                !creationDate.isAfter(now.withDayOfMonth(now.lengthOfMonth()))) {
	                
	                String monthName = creationDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
	                monthTicketCountMap.put(monthName, monthTicketCountMap.get(monthName) + 1);
	            }
	        }
	    }

	    List<Integer> ticketsPerMonth = new ArrayList<>(monthTicketCountMap.values());
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(ticketsPerMonth);
	}



	public String getByEmployeeCreationAndStatusClosedPerMonth(Employee employee) throws JsonProcessingException{
		return getTickedByEmployeeCreationPerMonth(ticketRepository.findByEmployeeCreationAndStatusTicketIn(employee,
				Arrays.asList("Closed")));
	}
	
	public String getByEmployeeCreationAndStatusResolvedPerMonth(Employee employee) throws JsonProcessingException{
		return getTickedByEmployeeCreationPerMonth(ticketRepository.findByEmployeeCreationAndStatusTicketIn(employee,
				Arrays.asList("Resolved")));
	}

	@Override
	public List<Ticket> findticketsCompletedByTeam(Team team) {
		return ticketRepository.findByTeamAssignAndStatusTicketInAndEnvironmentTicketNot(team,
					Arrays.asList("Closed", "Resolved"), "PRO");
		
	}
	
	@Override
	public List<Ticket> findticketsCompletedByTeamPro(Team team) {
		return ticketRepository.findByTeamAssignAndStatusTicketInAndEnvironmentTicket(team,
					Arrays.asList("Closed", "Resolved"), "PRO");
		
	}
	
	@Override
	public List<Ticket> findByEmployeeAssignOrderByModifyDateDesc(Employee employee) {
		return ticketRepository.findByEmployeeAssignOrderByModifyDateDesc(employee);
		
	}
	

}
