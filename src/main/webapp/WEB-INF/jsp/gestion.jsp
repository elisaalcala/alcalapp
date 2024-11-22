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
            <h2>Gestion Administrador</h2>
          </div>
          <div class="d-flex">
            <!-- DataTales Team -->
            <div class="card shadow m-4 w-100">
              <div class="card-header d-flex align-items-center justify-content-between bg-base">
                <h6 class="m-0 font-weight-bold text-uppercase text-white">Equipos</h6>
                <button type="button" class="btn bg-base-light" id="assignButton" data-bs-toggle="modal" data-bs-target="#newTeamModal">
                  <i class="fa-solid fa-plus"></i>
                </button>
              </div>
                <div class="card-body">
                        <table class="table table-bordered table-striped" id="dataTableTeam" width="100%" cellspacing="0">
                            <thead>
                              <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${teams}" var="team">
                                <tr data-team-id="${team.idTeam}" class="team-row pointer-row">
                                  <td>${team.idTeam}</td>
                                  <td>${team.nameTeam}</td>
                                </tr>
                              </c:forEach>
                          </table>
                    
                </div>
            </div>
            <!-- DataTales User -->
            <div class="card shadow m-4 w-100">
              <div class="card-header d-flex align-items-center justify-content-between bg-base">
                <h6 class="m-0 font-weight-bold text-uppercase text-white">Usuarios</h6>
                <button type="button" class="btn bg-base-light" id="assignButton" data-bs-toggle="modal" data-bs-target="#newUserModal">
                  <i class="fa-solid fa-plus"></i>
                </button>
              </div>
              <div class="card-body">
                <table class="table table-bordered table-striped" id="dataTableUser" width="100%" cellspacing="0">
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Usuario</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Equipo</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${employees}" var="employee">
                        <tr data-employee-id="${employee.employeeId}" class="employee-row pointer-row">
                          <td>${employee.employeeId}</td>
                          <td>${employee.userEmployee}</td>
                          <td>${employee.employeeName}</td>
                          <td>${employee.employeeLastName}</td>
                          <td>${employee.nameTeam}</td>
                        </tr>
                      </c:forEach>
                  </table>
              </div>
            </div>
          </div>

        </div>  
        <!-- Footer  -->
        <%@ include file="footer.jsp" %>
      </section>

      <%@ include file="modalNewTeam.jsp" %>
      <%@ include file="modalNewUser.jsp" %>
      
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

    </main>
  </div>

</body>
</html>



