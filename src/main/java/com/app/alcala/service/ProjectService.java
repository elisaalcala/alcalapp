package com.app.alcala.service;

import java.util.List;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProjectService {

	Project findById(long id);

	List<Project> findAll();

	List<Project> findProjectsNotCompletedByEmployee(Employee employee);

	List<Project> findByTeamAssignAndRelease(Team team, Release release);

	List<Project> findProjectsReadyByEmployee(Employee employee);

	Project save(Project project);

	Boolean delete(Project project);

	Project editProject(long id, Project updatedProject);

	Project mapNewProject(Project updatedProject, Employee employee, Team teamAssign, Release release);

	List<Project> findByRelease(Release release);

	List<Project> findProjectsFinishByEmployee(Employee employee);

	Integer findCountProjectsReadyByEmployee(Employee employee);

	Integer findCountProjectsNotCompletedByEmployee(Employee employee);

	Integer findCountProjectsFinishByEmployee(Employee employee);

	String getProjectResolvedPerMonth(List<Project> projectsFinish) throws JsonProcessingException;

	String getHoursProjectResolvedPerMonth(List<Project> projectsFinish) throws JsonProcessingException;

	List<Project> findprojectsCompletedByTeam(Team team);

	List<Project> findProjectsReadyByTeam(Team team);
	

}
