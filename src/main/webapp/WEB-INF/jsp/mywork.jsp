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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="/js/mywork.js"></script>



  </head>
<body>
  <div class="container">
    <!-- Topbar  -->
    <%@ include file="navbar.jsp" %>

    <main  class="d-flex">
      
      <!-- Sidebar -->
      <%@ include file="sidebar.jsp" %>

      <section class="container-fluid">
        <div>
          <div class="titulo">
            <h2>Mi trabajo - ${employee.employeeName} ${employee.employeeLastName}</h2>
          </div>

          <div class="columnas">

            <div class="width70">
              <div class="columnas-izquierda">

                  <div class="card shadow m-column m-right">
                    <div class=" card-header d-flex align-items-center justify-content-between bg-projects">
                      <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos por hacer</h6>
                      <a href="#" id="toggleBodyPrjToDo" class="text-white">
                        <i id="toggleIconPrjToDo" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                      </a>
                    </div>
                    <div class="card-body p-0" id="cardBodyPrjToDo">
                            <div class="chart-area">
                                <div class="columna-izquierda">
                                  <table>
                                    <thead>
                                      <tr>
                                        <th>Nombre</th>
                                        <th>Titulo</th>
                                        <th>Estado</th>
                                        <th>Release</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach items="${projectsNotCompleted}" var="proyecto">
                                        <tr data-proyecto-id="${proyecto.idProject}" class="proyecto-row  task-hover">
                                            <td>${proyecto.nameProject}</td>
                                            <td>${proyecto.titleProject}</td>
                                            <td>${proyecto.statusProject}</td>
                                            <td>${proyecto.releaseName}</td>
                                          
                                        </tr>
                                      </c:forEach>
                                  </table>
                                  </div>
                            </div>
                        </div>
                  </div>

                  <div class="card shadow m-column m-right">
                    <div class=" card-header d-flex align-items-center justify-content-between bg-incidences">
                      <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias por hacer</h6>
                      <a href="#" id="toggleBodyTckToDo" class="text-white">
                        <i id="toggleIconTckToDo" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                      </a>
                    </div>
                    <div class="card-body p-0" id="cardBodyTckToDo">
                      <div class="chart-area">
                        <table>
                          <thead>
                            <tr>
                              <th>Nombre</th>
                              <th>Titulo</th>
                              <th>Prioridad</th>
                              <th>Estado</th>
                              <th>Fecha creación</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach items="${ticketsNotCompleted}" var="ticket">
                              <tr data-ticket-id="${ticket.idTicket}" class="ticket-row task-hover">
                                  <td>${ticket.nameTicket}</td>
                                  <td>${ticket.titleTicket}</td>
                                  <td>${ticket.priorityTicket}</td>
                                  <td>${ticket.statusTicket}</td>
                                  <td>${ticket.initialDate}</td>
                                
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
                      <div class="card-body p-0" id="cardBodyPrjToDeploy">
                              <div class="chart-area">
                                  <div class="columna-izquierda">
                                    <table>
                                      <thead>
                                        <tr>
                                          <th>Nombre</th>
                                          <th>Titulo</th>
                                          <th>Estado</th>
                                          <th>Release</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        <c:forEach items="${projectsReady}" var="proyecto">
                                          <tr data-proyecto-id="${proyecto.idProject}" class="proyecto-row task-hover">
                                              <td>${proyecto.nameProject}</td>
                                              <td>${proyecto.titleProject}</td>
                                              <td>${proyecto.statusProject}</td>
                                              <td>${proyecto.releaseName}</td>
                                          
                                          </tr>
                                        </c:forEach>
                                    </table>
                                    </div>
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
                    <div class="card-body p-0" id="cardBodyTckToDeploy">
                      <div class="chart-area">
                        <table>
                          <thead>
                            <tr>
                              <th>Nombre</th>
                              <th>Titulo</th>
                              <th>Prioridad</th>
                              <th>Estado</th>
                              <th>Fecha creación</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach items="${ticketsReady}" var="ticket">
                              <tr data-ticket-id="${ticket.idTicket}" class="ticket-row task-hover">
                                  <td>${ticket.nameTicket}</td>
                                  <td>${ticket.titleTicket}</td>
                                  <td>${ticket.priorityTicket}</td>
                                  <td>${ticket.statusTicket}</td>
                                  <td>${ticket.initialDate}</td>
                              
                              </tr>
                            </c:forEach>
                        </table>
                      </div>
                    </div> 
                  </div>


                  <div class="card shadow m-column m-right">

                    <div class=" card-header d-flex align-items-center justify-content-between bg-finish">
                      <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos acabados</h6>

                      <a href="#" id="toggleBodyPrjFinish" class="text-white">
                        <i id="toggleIconPrjFinish" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                      </a>

                    </div>

                    <!-- DataTales Example -->
                      <div class="card shadow" id="cardBodyPrjFinish">
                        <div class="card-body">
                          <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                              <tr>
                                <th>Nombre</th>
                                <th>Titulo</th>
                                <th>Estado</th>
                                <th>Release</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${projectsFinish}" var="proyecto">
                                <tr data-proyecto-id="${proyecto.idProject}" class="proyecto-row task-hover">
                                    <td>${proyecto.nameProject}</td>
                                    <td>${proyecto.titleProject}</td>
                                    <td>${proyecto.statusProject}</td>
                                    <td>${proyecto.releaseName}</td>
                                  
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                            
                        </div>
                    </div>
                    
                  </div>

                  <div class="card shadow m-column m-right">

                    <div class=" card-header d-flex align-items-center justify-content-between bg-finish">
                      <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias acabadas</h6>
                      
                      <a href="#" id="toggleBodyTckFinish" class="text-white">
                        <i id="toggleIconTckFinish" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                      </a>

                    </div>

                    <!-- DataTales Example -->
                      <div class="card shadow" id="cardBodyTckFinish">
                        <div class="card-body">
                          <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                              <tr>
                                <th>Nombre</th>
                                <th>Titulo</th>
                                <th>Prioridad</th>
                                <th>Estado</th>
                                <th>Fecha creación</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${ticketsFinish}" var="ticket">
                                <tr data-ticket-id="${ticket.idTicket}" class="ticket-row task-hover">
                                  
                                    <td>${ticket.nameTicket}</td>
                                    <td>${ticket.titleTicket}</td>
                                    <td>${ticket.priorityTicket}</td>
                                    <td>${ticket.statusTicket}</td>
                                    <td>${ticket.initialDate}</td>
                                  
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                            
                        </div>
                    </div>
                    
                  </div>
            
              </div>
            </div>
            <div class="width30"> 
              <div class="columnas-derecha columna-derecha-dailywork">


                <input type="hidden" id="labelsJson" value='${labelsJson}' />
                <input type="hidden" id="tckDataJson" value='${tckDataJson}' />
                <input type="hidden" id="prjDataJson" value='${prjDataJson}' />
                <input type="hidden" id="tckDataLineJson" value='${tckDataLineJson}' />
                <input type="hidden" id="prjDataLineJson" value='${prjDataLineJson}' />
                <input type="hidden" id="tckDataCreationClosedJson" value='${tckDataCreationClosedJson}' />
                <input type="hidden" id="tckDataCreationResolvedJson" value='${tckDataCreationResolvedJson}' />



                <div class="card shadow m-column m-left w-100">

                  <div class=" card-header d-flex align-items-center justify-content-between bg-charts">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Estadisticas individuales - proporciones</h6>

                    <a href="#" id="toggleBodyProp" class="text-white">
                      <i id="toggleIconProp" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                    </a>

                  </div>

                  <div class="d-flex flex-column align-items-center" id="cardBodyProp">
                    <div class="limit-area">
                      <canvas id="myChartBar" style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"> </canvas>
                    </div>
                    
                    <div class="limit-area">
                      <canvas id="myChartLineTckCreation" style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"> </canvas>
                    </div>
                  </div>

                </div>

                <div class="card shadow m-column m-left w-100">

                  <div class=" card-header d-flex align-items-center justify-content-between bg-charts">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Estadisticas individuales - tiempos</h6>

                    <a href="#" id="toggleBodyTime" class="text-white">
                      <i id="toggleIconTime" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                    </a>

                  </div>

                  <div class="d-flex flex-column align-items-center" id="cardBodyTime">

                    <div class="limit-area">
                      <canvas id="myChartLine" style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"> </canvas>
                    </div>

                    <div class="limit-area">
                      <canvas id="myChartLineTck" style="margin-top: 20px;margin-bottom: 20px;margin-left: 10px;margin-right: 10px;"> </canvas>
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
      
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>


    </main>
  </div>

</body>
</html>



