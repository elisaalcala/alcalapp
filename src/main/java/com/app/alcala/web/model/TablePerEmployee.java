package com.app.alcala.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablePerEmployee {
	String userEmployee;
	Long idEmployee;
	Integer finishTickets;
	Integer finishProjects;
	Integer readyTickets;
	Integer readyProjects;
	Integer notCompletedTickets;
	Integer notCompletedProjects;
}
