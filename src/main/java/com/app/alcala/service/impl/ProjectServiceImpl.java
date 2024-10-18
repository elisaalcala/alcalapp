package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.repositories.ProjectRepository;
import com.app.alcala.service.ProjectService;
import com.app.alcala.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project findById(long id) {
		return projectRepository.findByIdProject(id).orElseThrow();
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public List<Project> findProjectsNotCompletedByEmployee(Employee employee) {
		return projectRepository.findByEmployeeAssignAndStatusProjectIn(employee, Constants.STATUS_NOT_COMPLETED);
	}

	@Override
	public List<Project> findByTeamAssignAndRelease(Team team, Release release) {
		return projectRepository.findByTeamAssignAndRelease(team, release);
	}

	@Override
	public List<Project> findProjectsReadyByEmployee(Employee employee) {
		return projectRepository.findByEmployeeAssignAndStatusProjectIn(employee, Constants.STATUS_READY);
	}

	@Override
	public List<Project> findProjectsReadyByTeam(Team team) {
		return projectRepository.findByTeamAssignAndStatusProjectIn(team, Constants.STATUS_READY);
	}

	@Override
	public List<Project> findByRelease(Release release) {

		return projectRepository.findByRelease(release);
	}

	@Override
	public Project save(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Boolean delete(Project project) {
		try {
			projectRepository.delete(project);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Project editProject(long id, Project updatedProject) {
		Project project = projectRepository.findById(id).orElseThrow();

		if (!StringUtils.isNullOrEmpty(updatedProject.getTitleProject())) {
			project.setTitleProject(updatedProject.getTitleProject());
		}

		if (!StringUtils.isNullOrEmpty(updatedProject.getDescriptionProject())) {
			project.setDescriptionProject(updatedProject.getDescriptionProject());
		}
		if (!StringUtils.isNullOrEmpty(updatedProject.getTypeProject())) {
			project.setTypeProject(updatedProject.getTypeProject());
		}
		if (!StringUtils.isNullOrEmpty(updatedProject.getPriorityProject())) {
			project.setPriorityProject(updatedProject.getPriorityProject());
		}

		if (!StringUtils.isNullOrEmpty(updatedProject.getEnvironmentProject())) {
			project.setEnvironmentProject(updatedProject.getEnvironmentProject());
		}

		if (!StringUtils.isNullOrEmpty(updatedProject.getStatusProject())) {
			// Si el estado estaba en Resuelto y se cambia a No resuelto, hay que eliminar
			// la fecha de finalizacion
			if (!isCloseOrFinish(updatedProject) && isCloseOrFinish(project)) {
				project.setFinishDate(null);
			} else if (isCloseOrFinish(updatedProject) && !isCloseOrFinish(project)) {
				LocalDateTime currentDate = LocalDateTime.now();
				project.setFinishDate(Timestamp.valueOf(currentDate));

			}
			project.setStatusProject(updatedProject.getStatusProject());
		}
		LocalDateTime currentDate = LocalDateTime.now();
		project.setModifyDate(Timestamp.valueOf(currentDate));
		return projectRepository.save(project);
	}

	private boolean isCloseOrFinish(Project project) {
		return project.getStatusProject().equalsIgnoreCase(Constants.STATUS_CLOSED)
				|| project.getStatusProject().equalsIgnoreCase(Constants.STATUS_FINISH);
	}

	public Project mapNewProject(Project updatedProject, Employee employee, Team teamAssign, Release release) {
		updatedProject.setEmployeeCreation(employee);
		updatedProject.setEmployeeUserCreation(employee.getUserEmployee());
		LocalDateTime currentDate = LocalDateTime.now();
		updatedProject.setInitialDate(Timestamp.valueOf(currentDate));
		updatedProject.setModifyDate(Timestamp.valueOf(currentDate));
		updatedProject.setTeamAssign(teamAssign);
		updatedProject.setStatusProject(Constants.STATUS_BACKLOG);
		updatedProject.setRelease(release);
		save(updatedProject);
		updatedProject.setNameProject(Constants.NAME_PRJ + updatedProject.getIdProject());
		return updatedProject;
	}

	@Override
	public List<Project> findProjectsFinishByEmployee(Employee employee) {
		return projectRepository.findByEmployeeAssignAndStatusProjectIn(employee, Constants.STATUS_COMPLETED);
	}

	@Override
	public Integer findCountProjectsReadyByEmployee(Employee employee) {
		Integer projectCount = (int) projectRepository.countByEmployeeAssignAndStatusProjectIn(employee,
				Constants.STATUS_READY);
		return projectCount;
	}

	@Override
	public Integer findCountProjectsNotCompletedByEmployee(Employee employee) {
		Integer projectCount = (int) projectRepository.countByEmployeeAssignAndStatusProjectIn(employee,
				Constants.STATUS_NOT_COMPLETED);
		return projectCount;
	}

	@Override
	public Integer findCountProjectsFinishByEmployee(Employee employee) {
		Integer projectCount = (int) projectRepository.countByEmployeeAssignAndStatusProjectIn(employee,
				Constants.STATUS_COMPLETED);
		return projectCount;
	}

	@Override
	public String getProjectResolvedPerMonth(List<Project> projectsFinish) throws JsonProcessingException {

		Map<String, Integer> monthTicketCountMap = new LinkedHashMap<>();
		LocalDate now = LocalDate.now();

		for (int i = 5; i >= 0; i--) {
			String monthName = now.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL,
					new Locale(Constants.LANGUAGE_ES, Constants.LANGUAGE_ES_UPPER));
			monthTicketCountMap.put(monthName, 0);
		}

		for (Project ticket : projectsFinish) {
			if (ticket.getFinishDate() != null) {
				LocalDate finishDate = ticket.getFinishDate().toLocalDateTime().toLocalDate();

				if (!finishDate.isBefore(now.minusMonths(5).withDayOfMonth(1))
						&& !finishDate.isAfter(now.withDayOfMonth(now.lengthOfMonth()))) {
					String monthName = finishDate.getMonth().getDisplayName(TextStyle.FULL,
							new Locale(Constants.LANGUAGE_ES, Constants.LANGUAGE_ES_UPPER));

					monthTicketCountMap.put(monthName, monthTicketCountMap.get(monthName) + 1);
				}
			}
		}
		List<Integer> ticketsPerMonth = new ArrayList<>(monthTicketCountMap.values());

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(ticketsPerMonth);
	}

	@Override
	public String getHoursProjectResolvedPerMonth(List<Project> projectsFinish) throws JsonProcessingException {
		Map<String, List<Long>> monthDurationMap = new LinkedHashMap<>();
		LocalDate now = LocalDate.now();

		for (int i = 5; i >= 0; i--) {
			String monthName = now.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL,
					new Locale(Constants.LANGUAGE_ES, Constants.LANGUAGE_ES_UPPER));
			monthDurationMap.put(monthName, new ArrayList<>());
		}

		for (Project project : projectsFinish) {
			if (project.getFinishDate() != null && project.getInitialDate() != null) {
				LocalDate finishDate = project.getFinishDate().toLocalDateTime().toLocalDate();
				LocalDate initialDate = project.getInitialDate().toLocalDateTime().toLocalDate();

				if (!finishDate.isBefore(now.minusMonths(5).withDayOfMonth(1))
						&& !finishDate.isAfter(now.withDayOfMonth(now.lengthOfMonth()))) {

					long daysSpent = ChronoUnit.DAYS.between(initialDate, finishDate);
					String monthName = finishDate.getMonth().getDisplayName(TextStyle.FULL,
							new Locale(Constants.LANGUAGE_ES, Constants.LANGUAGE_ES_UPPER));
					monthDurationMap.get(monthName).add(daysSpent);
				}
			}
		}

		Map<String, Double> monthAverageMap = new LinkedHashMap<>();
		for (Map.Entry<String, List<Long>> entry : monthDurationMap.entrySet()) {
			List<Long> durations = entry.getValue();
			if (!durations.isEmpty()) {
				double averageDays = durations.stream().mapToLong(Long::longValue).average().orElse(0.0);
				monthAverageMap.put(entry.getKey(), averageDays);
			} else {
				monthAverageMap.put(entry.getKey(), 0.0);
			}
		}

		List<Double> averagesPerMonth = new ArrayList<>(monthAverageMap.values());
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(averagesPerMonth);
	}

	@Override
	public List<Project> findprojectsCompletedByTeam(Team team) {
		return projectRepository.findByTeamAssignAndStatusProjectIn(team, Constants.STATUS_COMPLETED);

	}

}
