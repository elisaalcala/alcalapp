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
    <script src="/js/profile.js"></script>

  </head>
  <body>
    <div class="container">
      <!-- Topbar -->
      <%@ include file="navbar.jsp" %>
  
      <main class="d-flex">
        <!-- Sidebar -->
        <%@ include file="sidebar.jsp" %>
  
        <section class="container-fluid">
          <!-- Encabezado del perfil -->
          <div class="titulo mb-4">
            <h2>Mi perfil</h2>
          </div>
          <div class="columnas">

            <div class="width50 ">

              <div class="d-flex ">

                  <div class="profile-container ml-5 d-flex align-items-center flex-column width30">
                    <!-- Imagen de perfil o ícono de reemplazo -->
                    <div class="profile-image-container">
                      <img src="https://via.placeholder.com/300?text=Foto+de+Perfil" onerror="this.src='https://via.placeholder.com/300?text=Foto+de+Perfil';" alt="Foto de perfil" class="profile-image-large">
                    </div>
  
                    <!-- Contenedor de nombre de usuario con tooltip y estilo de flexbox -->
                    <div id="copy-username-button" class="profile-info d-flex justify-content-center align-items-center mt-3 nav-link nav-colorlink" style="cursor: pointer;" data-toggle="tooltip" data-placement="top" onclick="copyUsername()" data-bs-original-title="Copiar">
                      <!-- Nombre de usuario con opción de copiar -->
                      <h4 id="user-username" class="d-inline-block m-0">${employee.userEmployee}</h4>
                      <!-- Icono de copiar -->
                      <i class="fa fa-clipboard ml-1"></i>
                    </div>
  
                    <!-- Contenedor de correo electrónico con tooltip y estilo de flexbox -->
                    <div id="copy-email-button" class="profile-info d-flex justify-content-center align-items-center mt-2 nav-link nav-colorlink" style="cursor: pointer;" data-toggle="tooltip" data-placement="top" onclick="copyEmail()" data-bs-original-title="Copiar">
                      <!-- Correo con opción de copiar -->
                      <h4 id="user-email" class="d-inline-block m-0">${employee.userEmployee}@alcalapp.com</h4>
                      <!-- Icono de copiar -->
                      <i class="fa fa-clipboard ml-1"></i>
                    </div>
  
                    
                  </div>
                  
                  <!-- Información profesional y personal en columnas -->
                  <div class="ml-5 width70" >
                    <!-- Información profesional -->
                    <div class="row m-0" style="display: flex;">
                      <div class="card shadow mb-4 p-0">
                        <div class="card-header d-flex align-items-center justify-content-between bg-charts">
                          <h6 class="m-0 font-weight-bold text-uppercase text-white">Información Profesional</h6>
                        </div>
                        <div class="card-body">
                          <p><strong>ID Empleado:</strong> ${employee.employeeId}</p>
                          <p><strong>Fecha de alta:</strong> ${employee.hireDate}</p>
                          <p><strong>Puesto:</strong> ${employee.employeePosition}</p>
                        </div>
                      </div>
                    </div>
                    
                    <!-- Información personal -->
                    <div class="row m-0">
                      <div class="card shadow mb-4 p-0">
                        <div class="card-header d-flex align-items-center justify-content-between bg-charts">
                          <h6 class="m-0 font-weight-bold text-uppercase text-white">Información personal</h6>
                        </div>
                        <div class="card-body">
                          <p><strong>Nombre y Apellidos:</strong> ${employee.employeeName} ${employee.employeeLastName}</p>
                          <p><strong>DNI:</strong> ${employee.employeeDni}</p>
                          <p><strong>Fecha Nacimiento:</strong> ${employee.birthDate}</p>
                        </div>
                      </div>
                    </div>
                  </div>

                <!-- Contenedor de perfil con imagen a la izquierda y nombre e información a la derecha -->
                
              
              </div>

              <!-- Área de actividades recientes o proyectos -->
              <div class="row pl-3 pr-2">
                <div class="card shadow m-column p-0 ">

                  <div class=" card-header d-flex align-items-center justify-content-between bg-finish">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Ultimas Tareas</h6>
                    
                    <a href="#" id="toggleBodyTck" class="text-white">
                      <i id="toggleIconTck" class="fas fa-chevron-up fa-fw text-gray-400"></i>
                    </a>

                  </div>

                  <!-- DataTales Example -->
                  <div class="card shadow" id="cardBodyTck">
                    <div class="card-body">
                      <table class="table table-bordered table-striped" id="recomendations" width="100%" cellspacing="0">
                        <thead>
                          <tr>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Prioridad</th>
                            <th>Estado</th>
                            <th>Fecha de modificación</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${recomendations}" var="ticket">
                            <tr data-ticket-id="${ticket.idTicket}" class="ticket-row task-hover">
                              
                                <td>${ticket.nameTicket}</td>
                                <td>${ticket.descriptionTicket}</td>
                                <td>${ticket.priorityTicket}</td>
                                <td>${ticket.statusTicket}</td>
                                <td>${ticket.modifyDate}</td>
                              
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                        
                    </div>
                </div>
                  
                </div>
              </div>

            </div>
            
            <div class="width50">
              <div class="row ml-4">

                <div class="card shadow m-right p-0 ">

                  <div class=" card-header d-flex align-items-center justify-content-between bg-projects">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Equipo - ${team.nameTeam}</h6>
                  </div>

                  <!-- DataTales Example -->
                  <div class="card shadow" id="">
                    <div class="card-body">
                      <span>${team.descriptionTeam}</span>
                        <br>
                        <br>
                      <span>Componenetes del equipo:</span>

                      <c:forEach items="${employeesPerTeam}" var="employeesPerTeam">
                        <span> ${employeesPerTeam.userEmployee} - ${employeesPerTeam.employeePosition}</span>
                      </c:forEach>
                      
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <!-- Footer -->
          <footer class="sticky-footer bg-white">
            <div class="copyright text-center my-auto">
              <span>Copyright © Alcalapp 2024</span>
            </div>
          </footer>
          <!-- End of Footer -->
        </section>

        <!-- Modal para crear el ticket -->
        <%@ include file="modalCreate.jsp" %>
        <%@ include file="modalLogout.jsp" %>
        <%@ include file="modalCreateRelease.jsp" %>
  
      </main>
    </div>
  </body>
  
</html>



