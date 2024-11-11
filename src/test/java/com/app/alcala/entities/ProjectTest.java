package com.app.alcala.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    private Project project;
    private Timestamp initialDate;
    private Timestamp modifyDate;
    private Timestamp finishDate;

    @BeforeEach
    public void setup() {
        initialDate = Timestamp.valueOf("2023-01-01 10:00:00");
        modifyDate = Timestamp.valueOf("2023-02-01 10:00:00");
        finishDate = Timestamp.valueOf("2023-03-01 10:00:00");

        // Instancia de Project con el constructor completo
        project = new Project(
                1L,
                "Project A",
                "Title A",
                "This is a long description for Project A.",
                "Type A",
                "High",
                "Production",
                initialDate,
                modifyDate,
                finishDate,
                "InProgress",
                "Release 1",
                "Team Alpha",
                "john.doe",
                "jane.smith",
                "mike.manager",
                null,
                null,
                null,
                null,
                null
        );
    }

    @Test
    public void testConstructor() {
        // Verifica los valores iniciales del constructor
        assertThat(project.getIdProject()).isEqualTo(1L);
        assertThat(project.getNameProject()).isEqualTo("Project A");
        assertThat(project.getTitleProject()).isEqualTo("Title A");
        assertThat(project.getDescriptionProject()).isEqualTo("This is a long description for Project A.");
        assertThat(project.getTypeProject()).isEqualTo("Type A");
        assertThat(project.getPriorityProject()).isEqualTo("High");
        assertThat(project.getEnvironmentProject()).isEqualTo("Production");
        assertThat(project.getInitialDate()).isEqualTo(initialDate);
        assertThat(project.getModifyDate()).isEqualTo(modifyDate);
        assertThat(project.getFinishDate()).isEqualTo(finishDate);
        assertThat(project.getStatusProject()).isEqualTo("InProgress");
        assertThat(project.getReleaseName()).isEqualTo("Release 1");
        assertThat(project.getTeamNameAssign()).isEqualTo("Team Alpha");
        assertThat(project.getEmployeeUserAssign()).isEqualTo("john.doe");
        assertThat(project.getEmployeeUserCreation()).isEqualTo("jane.smith");
        assertThat(project.getEmployeeUserPrjManager()).isEqualTo("mike.manager");
    }

    @Test
    public void testGettersSetters() {
        Project newProject = new Project();

        // Setea valores utilizando setters
        newProject.setIdProject(2L);
        newProject.setNameProject("Project B");
        newProject.setTitleProject("Title B");
        newProject.setDescriptionProject("This is a long description for Project B.");
        newProject.setTypeProject("Type B");
        newProject.setPriorityProject("Medium");
        newProject.setEnvironmentProject("Staging");
        newProject.setInitialDate(Timestamp.valueOf("2024-01-01 10:00:00"));
        newProject.setModifyDate(Timestamp.valueOf("2024-02-01 10:00:00"));
        newProject.setFinishDate(Timestamp.valueOf("2024-03-01 10:00:00"));
        newProject.setStatusProject("Completed");
        newProject.setReleaseName("Release 2");
        newProject.setTeamNameAssign("Team Beta");
        newProject.setEmployeeUserAssign("alice.doe");
        newProject.setEmployeeUserCreation("bob.smith");
        newProject.setEmployeeUserPrjManager("carol.manager");

        // Verifica que los getters devuelvan los valores esperados
        assertThat(newProject.getIdProject()).isEqualTo(2L);
        assertThat(newProject.getNameProject()).isEqualTo("Project B");
        assertThat(newProject.getTitleProject()).isEqualTo("Title B");
        assertThat(newProject.getDescriptionProject()).isEqualTo("This is a long description for Project B.");
        assertThat(newProject.getTypeProject()).isEqualTo("Type B");
        assertThat(newProject.getPriorityProject()).isEqualTo("Medium");
        assertThat(newProject.getEnvironmentProject()).isEqualTo("Staging");
        assertThat(newProject.getInitialDate()).isEqualTo(Timestamp.valueOf("2024-01-01 10:00:00"));
        assertThat(newProject.getModifyDate()).isEqualTo(Timestamp.valueOf("2024-02-01 10:00:00"));
        assertThat(newProject.getFinishDate()).isEqualTo(Timestamp.valueOf("2024-03-01 10:00:00"));
        assertThat(newProject.getStatusProject()).isEqualTo("Completed");
        assertThat(newProject.getReleaseName()).isEqualTo("Release 2");
        assertThat(newProject.getTeamNameAssign()).isEqualTo("Team Beta");
        assertThat(newProject.getEmployeeUserAssign()).isEqualTo("alice.doe");
        assertThat(newProject.getEmployeeUserCreation()).isEqualTo("bob.smith");
        assertThat(newProject.getEmployeeUserPrjManager()).isEqualTo("carol.manager");
    }

    @Test
    public void testEqualsAndHashCode() {
        Project anotherProject = new Project(
                1L,
                "Project A",
                "Title A",
                "This is a long description for Project A.",
                "Type A",
                "High",
                "Production",
                initialDate,
                modifyDate,
                finishDate,
                "InProgress",
                "Release 1",
                "Team Alpha",
                "john.doe",
                "jane.smith",
                "mike.manager",
                null,
                null,
                null,
                null,
                null
        );

        // Verifica que dos objetos con los mismos valores sean iguales
        assertThat(project).isEqualTo(anotherProject);
        assertThat(project.hashCode()).isEqualTo(anotherProject.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Project(idProject=1, nameProject=Project A, titleProject=Title A, descriptionProject=This is a long description for Project A., " +
                "typeProject=Type A, priorityProject=High, environmentProject=Production, initialDate=" + initialDate.toString() + ", " +
                "modifyDate=" + modifyDate.toString() + ", finishDate=" + finishDate.toString() + ", statusProject=InProgress, " +
                "releaseName=Release 1, teamNameAssign=Team Alpha, employeeUserAssign=john.doe, employeeUserCreation=jane.smith, " +
                "employeeUserPrjManager=mike.manager, release=null, teamAssign=null, employeeAssign=null, employeeCreation=null, employeePrjManager=null)";

        // Verifica que el método toString genere la salida esperada
        assertThat(project.toString()).isEqualTo(expectedString);
    }
}
