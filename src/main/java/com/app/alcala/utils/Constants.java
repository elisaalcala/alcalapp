package com.app.alcala.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String DATEFORMAT_YYYY_MM = "yyyy-MM";
	
	public static final String LANGUAGE_ES = "es";
	public static final String LANGUAGE_ES_UPPER = "ES";
	public static final String NAME_PRJ = "PRJ ";
	public static final String NAME_R = "R";
	public static final String NAME_TCK = "TCK ";

	// STATUS
	public static final String STATUS_SIN_ASIGNAR = "Sin asignar";
	public static final String STATUS_IN_PROGRESS = "In progress";
	public static final String STATUS_CLOSED = "Closed";
	public static final String STATUS_FINISH = "Finish";
	public static final String STATUS_BACKLOG = "Backlog";
	public static final String STATUS_COMPLETED_RELEASE = "Completed";
	public static final String STATUS_START = "Start";
	public static final String STATUS_BLOCKED = "Blocked";
	public static final String STATUS_RESOLVED = "Resolved";

	public static final List<String> STATUS_NOT_COMPLETED = Arrays.asList("Backlog", "In progress", "Blocked");
	public static final List<String> STATUS_COMPLETED = Arrays.asList("Finish", "Closed");
	public static final List<String> STATUS_COMPLETED_TICKET = Arrays.asList("Closed", "Resolved");
	public static final List<String> STATUS_READY = Arrays.asList("Test", "Ready to UAT", "Ready to PRO");

	public static final List<String> ALL_STATUS_RELEASES = Arrays.asList("Backlog", "In Progress", "Resolved", "Closed");

	// MESSAGE
	public static final String CREATED_BY = "Creado por ";
	public static final String ASSIGNED_TO = "Asignado a ";
	public static final String MOVE_TO = "Traspasado a ";
	public static final String RESOLVED = "Resuelto";
	public static final String REOPEN = "Reabierto";
	
	//ENVIREMENT
	public static final String PRO = "PRO";
	
	//EXCEPTION
	public static final String EXCEPTION_USER_NOT_FOUND = "User not found";
	
	//ROLES
	public static final List<String> ROLES = Arrays.asList("USER", "ADMIN");
}