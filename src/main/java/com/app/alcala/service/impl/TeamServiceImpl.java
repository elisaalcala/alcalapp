package com.app.alcala.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.TeamRepository;
import com.app.alcala.service.TeamService;
import com.app.alcala.web.model.TeamDTO;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	TeamRepository teamRepository;

	@Override
	public Team findByNameTeam(String nameTeam) {
		return teamRepository.findByNameTeam(nameTeam).orElseThrow();
	}

	@Override
	public List<Team> findTeamsToSendTicket(Team team) {
		List<Team> createTicketTeamsList = teamRepository.findAll();
		createTicketTeamsList.remove(team);
		return createTicketTeamsList;
	}

	@Override
	public Team deleteTicket(Team teamQuit, Ticket ticket) {
		teamQuit.getTicketMapTeam().remove(ticket.getIdTicket());
		return teamRepository.save(teamQuit);
	}

	@Override
	public Team deleteProject(Team teamQuit, Project project) {
		teamQuit.getProjectMapTeam().remove(project.getIdProject());
		return teamRepository.save(teamQuit);
	}

	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void delete(Team teamDelete) {
		teamRepository.delete(teamDelete);
	}
	@Override
	public void delete(long id) {
		Team teamDelete = teamRepository.findByIdTeam(id);
		teamRepository.delete(teamDelete);
	}


	@Override
	public Team createTeam(TeamDTO team) {
		Team newTeam = new Team();
		newTeam.setDescriptionTeam(team.getDescriptionTeam());
		newTeam.setNameTeam(team.getNameTeam());
		newTeam.setTicketMapTeam(new HashMap<>());
		newTeam.setProjectMapTeam(new HashMap<>());
		newTeam.setEmployeeMap(new HashMap<>());
		newTeam.setTeamLeader(null);
		return teamRepository.save(newTeam);
	}

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Override
	public Team findByIdTeam(long id) {
		return teamRepository.findByIdTeam(id);
	}

	@Override
	public Team editTeam(long id, TeamDTO teamDTO) {
		Team team = teamRepository.findByIdTeam(id);
		team.setDescriptionTeam(teamDTO.getDescriptionTeam());
		team.setNameTeam(teamDTO.getNameTeam());
		return teamRepository.save(team);
	}
	
	
	
}
