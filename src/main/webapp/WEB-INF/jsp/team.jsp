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
    <script src="/js/team.js"></script>
    
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
        <input type="hidden" id="teamId" value="${team.idTeam}" />


        <div class="card custom-card shadow " style="height: 100%;">
          <div class="card-header d-flex align-items-center justify-content-between bg-base px-3">
            <h6 class="m-0  font-weight-bold text-uppercase text-white">Equipo - ${team.nameTeam}</h6>
            <div>
              <button type="button" class="btn bg-base-light" id="editButton" data-bs-toggle="modal" data-bs-target="#editModalTeam">
                <i class="fa-solid fa-pen-to-square"></i>
                
              </button>
              <button type="button" class="btn btn-danger ml-1" id="deleteButton" data-bs-toggle="modal" 
                data-bs-target="#deleteModalTeam" ${borrado ? '' : 'disabled'}>
                <i class="fa-solid fa-trash"></i>
                
              </button>
            </div>
          </div>
          <div class="card-body">



            <div class="m-4" style="height: 80%;">
                <div class="d-flex" style="gap: 3em; height: 100%;">
                    <div class="width60 d-flex" style="flex-direction: column; gap: 1em; height: 100%;">
                      <!--Empleados por equipo-->
                      <div class="ticket-detail-heading color-cyan">
                        Usuarios:
                      </div>
                      <div class="card" style="height: 100%;">
                          <div class="card-body">
                              <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                                  <thead>
                                      <tr>
                                      <th>Usuario</th>
                                      <th>Nombre</th>
                                      <th>Apellido</th>
                                      <th>Puesto</th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                  <c:forEach items="${empleados}" var="empleado">
                                  <tr data-empleado-row-per-team-2="${empleado.employeeId}" class="empleado-row-per-team-2 pointer-row">
                                      <td>${empleado.userEmployee}</td>
                                      <td>${empleado.employeeName}</td>
                                      <td>${empleado.employeeLastName}</td>
                                      <td>${empleado.employeePosition}</td>
                                  </tr>
                                  </c:forEach>
                              </table>
                            
                          </div>
                      </div>
                    </div>
                    
                    <div class="width40">
                        <div class="ticket-info h-100 ">
                            <div class="ticket-detail-heading color-cyan">
                                Descripcion:
                            </div>
                            <div class="row h-100 m-0">
                              <textarea class="form-control mb-4 mt-3" id="descripcionTeam" readonly>${team.descriptionTeam}</textarea>
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
      <%@ include file="modalEditTeam.jsp" %>
      <!-- Modal para eliminar el ticket -->
      <%@ include file="modalDeleteTeam.jsp" %>
          
      <!-- Modal para crear el ticket -->
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

    </main>
  </div>

</body>
</html>



