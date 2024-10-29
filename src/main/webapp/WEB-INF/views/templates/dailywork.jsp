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


    
  </head>
<body>
  <div class="container" id="dailywork">

    <!-- Topbar  -->
    <%@ include file="navbar.jsp" %>
    
    <main  class="d-flex">
      
      <!-- Sidebar -->
      <%@ include file="sidebar.jsp" %>
    
      <section class="container-fluid">
        <div class="titulo">
          <h2>Trabajo diario - ${team.nameTeam}</h2>
        </div>
        <div class="columnas">

          <div class="width70"> 
            <div class="columnas-izquierda">

              <div class="card shadow m-column m-right">
                <div class=" card-header d-flex align-items-center justify-content-between bg-incidences">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias</h6>
                  <a href="#" id="toggleBodyTckToDo" class="text-white">
                    <i id="toggleIconTckToDo" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                  </a>
                </div>
                <!-- Card Body -->
                <div class="card-body padding-0" id="cardBodyTckToDo">
                  <div class="chart-area">
                    <table>
                      <thead>
                        <tr>
                          <th>Nombre</th>
                          <th>Titulo</th>
                          <th>Prioridad</th>
                          <th>Estado</th>
                          <th>Fecha creación</th>
                          <th>Encargado</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ticketsNotCompleted}" var="ticket">
                          <tr data-ticket-id="${ticket.idTicket}" class="ticket-row  task-hover">
                            
                              <td>${ticket.nameTicket}</td>
                              <td>${ticket.titleTicket}</td>
                              <td>${ticket.priorityTicket}</td>
                              <td>${ticket.statusTicket}</td>
                              <td>${ticket.initialDate}</td>
                              <c:choose>
                                <c:when test="${empty proyecto.employeeUserAssign}">
                                  <td>
                                    <a href="#" class="nav-colorlink assignMeTicketLink">
                                      <i class="fa-solid fa-pencil "></i>
                                      Asignarme a mi
                                    </a>
                                  </td>  
                                </c:when>
                                <c:otherwise>
                                  <td>${proyecto.employeeUserAssign}</td>
                                </c:otherwise>
                              </c:choose>
                            
                          </tr>
                        </c:forEach>
                    </table>
                  </div>
                </div> 
              </div>

             

              <c:forEach items="${projectsTables}" var="table">

                <div class="card shadow m-column m-right">
                  <div class=" card-header d-flex align-items-center justify-content-between bg-projects">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos release ${table.releaseName}</h6>
                      <div class="dropdown">
                            <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                                <a class="dropdown-item nav-colorlink" href="/releases/${table.idRelease}">Ver Release</a>
                                <a class="dropdown-item createProjectLink nav-colorlink" data-release-id="${table.idRelease}" data-target="#createModalProject" >Añadir Proyecto</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item deleteProjectLink nav-colorlink text-danger" data-release-id="${table.idRelease}" data-target="#deleteModalRelease" href="#">Eliminar Release</a>
                            </div>
                      </div>
                  </div>
    
                    <!-- Card Body -->
                    <div class="card-body padding-0">
                        <div class="chart-area">
                            <div class="columna-izquierda">
                              <table>
                                <thead>
                                  <tr>
                                    <th>Nombre</th>
                                    <th>Titulo</th>
                                    <th>Estado</th>
                                    <th>Encargado</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <c:forEach items="${table.projects}" var="proyecto">
                                    <tr data-proyecto-id="${proyecto.idProject}" class="proyecto-row  task-hover">
                                      
                                        <td>${proyecto.nameProject}</td>
                                        <td>${proyecto.titleProject}</td>
                                        <td>${proyecto.statusProject}</td>
                                        <c:choose>
                                          <c:when test="${empty proyecto.employeeUserAssign}">
                                            <td>
                                              <a href="#" class="nav-colorlink assignMeProjectLink">
                                                <i class="fa-solid fa-pencil "></i>
                                                Asignarme a mi
                                              </a>
                                            </td>  
                                          </c:when>
                                          <c:otherwise>
                                            <td>${proyecto.employeeUserAssign}</td>
                                          </c:otherwise>
                                        </c:choose>
                                        
                                      
                                    </tr>
                                  </c:forEach>
                              </table>
                              </div>
                        </div>
                    </div>
                </div>

              </c:forEach>


              <div class="card shadow m-column m-right">
                <div class=" card-header d-flex align-items-center justify-content-between bg-blocked">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias Bloqueadas</h6>
                  <a href="#" id="toggleBodyTckBlocked" class="text-white">
                    <i id="toggleIconTckBlocked" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                  </a>
                </div>
                <!-- Card Body -->
                <div class="card-body padding-0" id="cardBodyTckBlocked">
                  <div class="chart-area">
                    <table>
                      <thead>
                        <tr>
                          <th>Nombre</th>
                          <th>Titulo</th>
                          <th>Prioridad</th>
                          <th>Estado</th>
                          <th>Fecha creación</th>
                          <th>Encargado</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ticketsBlocked}" var="ticket">
                          <tr data-ticket-id="${ticket.idTicket}" class="ticket-row  task-hover">
                            
                              <td>${ticket.nameTicket}</td>
                              <td>${ticket.titleTicket}</td>
                              <td>${ticket.priorityTicket}</td>
                              <td>${ticket.statusTicket}</td>
                              <td>${ticket.initialDate}</td>
                              <td>${ticket.employeeUserAssign}</td>
                            
                          </tr>
                        </c:forEach>
                    </table>
                  </div>
                </div> 
              </div>



              <div class="card shadow m-column m-right">
                <div class=" card-header d-flex align-items-center justify-content-between bg-incidences">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias por desplegar</h6>
                  <a href="#" id="toggleBodyTckToDeploy" class="text-white">
                    <i id="toggleIconTckToDeploy" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                  </a>
                </div>
                <!-- Card Body -->
                <div class="card-body padding-0" id="cardBodyTckToDeploy">
                  <div class="chart-area">
                    <table>
                      <thead>
                        <tr>
                          <th>Nombre</th>
                          <th>Titulo</th>
                          <th>Prioridad</th>
                          <th>Estado</th>
                          <th>Fecha creación</th>
                          <th>Encargado</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ticketsReadyToDeploy}" var="ticket">
                          <tr data-ticket-id="${ticket.idTicket}" class="ticket-row  task-hover">
                            
                              <td>${ticket.nameTicket}</td>
                              <td>${ticket.titleTicket}</td>
                              <td>${ticket.priorityTicket}</td>
                              <td>${ticket.statusTicket}</td>
                              <td>${ticket.initialDate}</td>
                              <td>${ticket.employeeUserAssign}</td>
                            
                          </tr>
                        </c:forEach>
                    </table>
                  </div>
                </div> 
              </div>
            

              <div class="card shadow m-column m-right">
                <div class=" card-header d-flex align-items-center justify-content-between bg-projects">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos por desplegar</h6>
                  <a href="#" id="toggleBodyPrjToDeploy" class="text-white">
                    <i id="toggleIconPrjToDeploy" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                  </a>
                </div>
                <div class="card-body padding-0" id="cardBodyPrjToDeploy">
                      <div class="chart-area">
                          <div class="columna-izquierda">
                            <table>
                              <thead>
                                <tr>
                                  <th>Nombre</th>
                                  <th>Titulo</th>
                                  <th>Estado</th>
                                  <th>Release</th>
                                  <th>Encargado</th>
                                </tr>
                              </thead>
                              <tbody>
                                <c:forEach items="${projectsReadyToDeploy}" var="proyecto">
                                  <tr data-proyecto-id="${proyecto.idProject}" class="proyecto-row  task-hover">
                                    
                                      <td>${proyecto.nameProject}</td>
                                      <td>${proyecto.titleProject}</td>
                                      <td>${proyecto.statusProject}</td>
                                      <td>${proyecto.releaseName}</td>
                                      <td>${proyecto.employeeUserAssign}</td>
                                    
                                  </tr>
                                </c:forEach>
                            </table>
                            </div>
                      </div>
                  </div>
              </div>

            </div>
          </div>


          <div class="width30"> 
            <div class="columnas-derecha align_columns">

              <div class="card shadow m-column m-left width100">
                <div class=" card-header d-flex align-items-center justify-content-between bg-charts">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Carga de trabajo</h6>
                  <a href="#" id="toggleBodyCarga" class="text-white">
                    <i id="toggleIconCarga" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                  </a>
                </div>
                <div class="card-body padding-0 carga-style" id="cardBodyCarga" >
                  <div class="chart-container" style=" width: 45%;  height: 45%;">
                    <canvas id="chart-carga-trabajo" user='${userPerEmployee}' load='${loadPerEmployee}' style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"></canvas>
                  </div>
                  <div class="chart-area width50">
                    <c:forEach items="${workLoad.listWorkPerEmployee}" var="workPerEmployee">
                      <div class="carga">
                        <a class="carga nav-link dropdown-toggle collapsed" href="#" data-toggle="collapse" data-target="#collapseCargaTrabajo">
                          <span class="carga carga-tittle">
                            ${workPerEmployee.userEmployee}
                          </span> 
                        </a>
                        <div id="collapseCargaTrabajo" class="collapse">
                            <ul class="list-group">
                                <c:forEach items="${workPerEmployee.projectInProgress}" var="projectInProgress">
                                    <li class="list-group-item">
                                        <a class="nav-colorlink" href="/projects/${projectInProgress.idProject}">
                                            ${projectInProgress.nameProject} - ${projectInProgress.titleProject}
                                        </a>
                                    </li>
                                </c:forEach>
                                <c:forEach items="${workPerEmployee.ticketInProgress}" var="ticketsInProgress">
                                    <li class="list-group-item">
                                        <a class="nav-colorlink" href="/tickets/${ticketsInProgress.idTicket}">
                                            ${ticketsInProgress.nameTicket} - ${ticketsInProgress.titleTicket}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    </c:forEach>
                  </div>
                </div> 
              </div>

              
              <div class="card shadow m-column m-left width100">
                <div class=" card-header d-flex align-items-center justify-content-between bg-charts">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Estadisticas Equipo</h6>
                  <a href="#" id="toggleBodyBar" class="text-white">
                    <i id="toggleIconBar" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                  </a>
                </div>
                <div id="cardBodyBar">

                  <!-- Input ocultos para pasar los datos del servidor al JS -->
                  <input type="hidden" id="employeesTeam" value='${employeesTeam}' />
                  <input type="hidden" id="employeesTicketsResolved" value='${employeesTicketsResolved}' />
                  <input type="hidden" id="employeesProjectsResolved" value='${employeesProjectsResolved}' />

                  <input type="hidden" id="monthsJson" value='${monthsJson}' />
                  <input type="hidden" id="projectsCompletedByTeamJson" value='${projectsCompletedByTeamJson}' />
                  <input type="hidden" id="ticketsCompletedByTeamJson" value='${ticketsCompletedByTeamJson}' />
                  <input type="hidden" id="ticketsCompletedByTeamJsonPro" value='${ticketsCompletedByTeamJsonPro}' />


                  <div>
                    <canvas id="teamChartBar" style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"> </canvas>
                  </div>
                  
                  <div>
                    <canvas id="teamChartLineTck" style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"> </canvas>
                  </div>

                  <div>
                    <canvas id="teamChartLinePrj" style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"> </canvas>
                  </div>
                </div>

              </div>



            </div>

            
          </div>

        </div>
        <!-- Footer  -->
        <%@ include file="footer.jsp" %>
      </section>
      <%@ include file="modalNewUser.jsp" %>
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

      <script src="/js/dailywork.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    </main>
  </div>

</body>
</html>



