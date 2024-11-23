<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Alcalapp</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <link rel="stylesheet" href="/css/style.css">
    
    <%@ include file="scripts.jsp" %>
    <script src="/js/project.js"></script>

  </head>
<body>
  <div class="container">
    <!-- Topbar  -->
    <%@ include file="navbar.jsp" %>
    
    <main  class="d-flex">
      
      <!-- Sidebar -->
      <%@ include file="sidebar.jsp" %>

      <section class="container-fluid">

        <!-- Input oculto -->
        <input type="hidden" id="projectId" value="${project.idProject}" />
        <input type="hidden" id="employeeNameAssignToMe" value="${employee.employeeName}" />
        <input type="hidden" id="employeeLastNameAssignToMe" value="${employee.employeeLastName}" />
        <input type="hidden" id="userEmployee" value="${employee.userEmployee}" />
        <input type="hidden" id="proDate" value="${project.release.proDate}" />
        <input type="hidden" id="tstDate" value="${project.release.tstDate}" />

        <div class="card custom-card shadow ">
          <div class="card-header d-flex align-items-center justify-content-between bg-base">
            <h6 class="m-0 font-weight-bold text-uppercase text-white">${project.nameProject} - ${project.titleProject}</h6>
          </div>
          <div class="card-body">
            <div class="div-col justify-content-between mt-1">

              <div class="div-col">
                <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                <div class="ticket-button m-left">
                  <button type="button" class="btn bg-base-light" id="assignButton" data-bs-toggle="modal" data-bs-target="#assignModalProject" 
                  <sec:authorize access="hasRole('ADMIN')">
                      >
                    </sec:authorize>
                    <sec:authorize access="hasRole('MANAGER')">
                      ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>
                    </sec:authorize>
                    Asignar</button>

                </div>
              </sec:authorize>
                <div class="ticket-button dropdown m-left">
                  <button class="btn bg-base-light dropdown-toggle" type="button" id="changeStatusButtonProject" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                  <sec:authorize access="hasRole('ADMIN')">
                      >
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('MANAGER', 'USER')">
                      ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>
                    </sec:authorize>
                    Cambiar estado
                  </button>
                  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <h6 class="collapse-header">Cambiar a:</h6>
                    <c:forEach items="${allStatus}" var="status"> 
                      <a class="collapse-item project-item" id="${status}" href="#">${status}</a>
                    </c:forEach>
                  </div>
                </div>

              </div>
              <div class="div-col">
                <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                  <div class="ticket-button m-left">
                    <button type="button" class="btn bg-base-light" id="cloneButton" data-bs-toggle="modal" data-bs-target="#cloneModalProject">
                      <i class="fa-solid fa-clone"></i>
                      Clonar</button>
                  </div>
                </sec:authorize>
                <div class="ticket-button m-left">
                  <button type="button" class="btn bg-base-light" id="editButton" data-bs-toggle="modal" data-bs-target="#editModalProject"
                  <sec:authorize access="hasRole('ADMIN')">
                      >
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('MANAGER', 'USER')">
                      ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>
                    </sec:authorize>
                    <i class="fa-solid fa-pen-to-square"></i>
                    Editar
                  </button>
                </div>
                <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                  <div class="ticket-button m-left">
                    <button type="button" class="btn btn-danger" id="deleteButton" data-bs-toggle="modal" data-bs-target="#deleteModalProject"
                    <sec:authorize access="hasRole('ADMIN')">
                      >
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('MANAGER', 'USER')">
                      ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>
                    </sec:authorize>
                      <i class="fa-solid fa-trash"></i>
                      Eliminar
                    </button>
                  </div>
                </sec:authorize>

              </div>
              
            </div>
            <div class="m-4">
              <div class="row">
                <div class="col">
                <div class="ticket-info">
                  <div class="ticket-detail-heading color-cyan">
                    Detalles
                  </div>
                  <div class="row">
                      <dt class="col">Alcance:</dt>
                      <dd class="col" id="ticketType">${project.typeProject}</dd>
                  </div>
                  <div class="row">
                      <dt class="col">Prioridad:</dt>
                      <dd class="col" id="ticketPriority">${project.priorityProject}</dd>
                  </div>
                  <div class="row">
                      <dt class="col">Entorno prueba:</dt>
                      <dd class="col" id="ticketEnvironment">${project.environmentProject}</dd>
                  </div>
                  <div class="row">
                      <dt class="col">Estado actual:</dt>
                      <dd class="col" id="ticketStatus">${project.statusProject}</dd>
                  </div>
                </div>
                </div>

                <div class="col">
                  <div class="ticket-info">
                    <div class="ticket-detail-heading color-cyan">
                      Personal
                    </div>
                    <div class="row">
                      <dt class="col">Empleado Asignado:</dt>
                        <c:choose>
                          <c:when test="${empty project.employeeUserAssign}">
                            <dd class="col" id="employeeUserAssign">
                              Sin asignar
                              <br>
                              <c:if test="${employee.nameTeam eq project.teamNameAssign}">
                                <a href="#" id="assignToMeLinkProject" class="color-cyan">
                                  <i class="fa-solid fa-pencil "></i>
                                  Asignarme a mi</a>
                              </c:if>
                            </dd>
                          </c:when>
                          <c:otherwise>
                            <dd class="col" id="employeeUserAssign">${project.employeeUserAssign}
                              <br>
                              <c:if test="${employee.userEmployee eq project.employeeUserAssign}">
                                  <a href="#" id="unassignFromMeLinkProject" class="color-cyan">
                                      <i class="fa-solid fa-trash"></i>
                                      Desasignarme</a>
                              </c:if>
                            </dd>
                          </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="row">
                        <dt class="col">Equipo Asignado:</dt>
                        <dd class="col" id="teamNameAssign">${project.teamNameAssign}</dd>
                    </div>
                    <div class="row">
                      <dt class="col">Empleado creador:</dt>
                      <dd class="col" id="employeeUserCreation">${project.employeeUserCreation}</dd>
                    </div>
                    
                  </div>
                </div>
                <div class="col">
                  <div class="ticket-info">

                    <!-- Input oculto para la fecha inicial -->
                    <input type="hidden" id="initialDateHidden" value="${project.initialDate}" />

                    <!-- Input oculto para la fecha de modificación -->
                    <input type="hidden" id="modifyDateHidden" value="${project.modifyDate}" />

                    <!-- Input oculto para la fecha de finalización -->
                    <input type="hidden" id="finishDateHidden" value="${project.finishDate}" />


                    <div class="ticket-detail-heading color-cyan">
                      Fechas
                    </div>
                    <div class="row">
                        <dt class="col">Creación:</dt>
                        <dd class="col" id="initialDate"></dd>

                    </div>
                    <div class="row">
                      <dt class="col">Última modificación:</dt>
                      <dd class="col" id="modifyDate"></dd>
                  </div>
                    <div class="row">
                        <dt class="col">Finalización:</dt>
                        <dd class="col" id="finishDate"></dd>
                    </div>
                  </div>
                </div>
              </div>

              <div class="d-flex">

                <div class="width70">
                  <div class="ticket-info h-100 mr-5">
                    <div class="ticket-detail-heading color-cyan">
                        Descripcion
                    </div>
                    <div class="row h-100">
                      <textarea class="form-control mb-4 mt-3" id="descripcionProject" readonly>${project.descriptionProject}</textarea>
                    </div>
                    
                  </div>
                  
                </div>

                <div class="width30">
                  <div class="wrapper">
                    <header>
                      <div class="current-date"></div>
                      <div class="icons">
                        <span id="prev" class="material-symbols-rounded">
                          <i class="fa-solid fa-chevron-left"></i>
                        </span>
                        <span id="next" class="material-symbols-rounded">
                          <i class="fa-solid fa-chevron-right"></i>
                        </span>
                      </div>
                    </header>
                    <div class="calendar">
                      <ul class="weeks">
                        <li>D</li>
                        <li>L</li>
                        <li>M</li>
                        <li>X</li>
                        <li>J</li>
                        <li>V</li>
                        <li>S</li>
                      </ul>
                      <ul class="days"></ul>
                    </div>
                  </div>
                  
                </div>
              </div>

            </div>  
          </div>
        </div>
        <!-- Footer  -->
        <%@ include file="footer.jsp" %>
      </section>

      <!-- Modal para editar el ticket -->
      <%@ include file="modalEditProject.jsp" %>

      <!-- Modal para asignar el ticket -->
      <%@ include file="modalAssignProject.jsp" %>
          
      <!-- Modal para crear el ticket -->
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

      <!-- Modal para clonar el ticket -->
      <%@ include file="modalCloneProject.jsp" %>

      <!-- Modal para eliminar el ticket -->
      <%@ include file="modalDeleteProject.jsp" %>

    </main>
  </div>

</body>
</html>



