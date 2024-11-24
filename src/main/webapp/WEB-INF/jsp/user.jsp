<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
    <script src="/js/user.js"></script>
    
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
        <input type="hidden" id="employeeId" value="${employeeSelect.employeeId}" />
        <input type="hidden" id="username" value="${employeeSelect.userEmployee}" />


        <div class="card custom-card shadow" style="display: flex; justify-content: center; ">
          <div class="card-header d-flex align-items-center justify-content-between bg-base">
            <h6 class="m-0 font-weight-bold text-uppercase text-white">Información de usuario - ${employeeSelect.userEmployee}</h6>
                <div>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('MANAGER')">
                        <button type="button" class="btn bg-base-light" id="editButton" data-bs-toggle="modal" data-bs-target="#editModalEmployee">
                            <i class="fa-solid fa-pen-to-square"></i>
                        </button>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <button type="button" class="btn btn-danger ml-1" id="deleteButton" data-bs-toggle="modal" 
                            data-bs-target="#deleteModalEmployee">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </sec:authorize>
                </div>
          </div>
          <div id="user_info" class="card-body" style="max-height: 100vh; padding: 2em;">
            <div class="row" style="display: flex; flex-direction: row; justify-content: center; gap: 4em; margin-right: 2em;">
                <!-- Profile Picture Section -->
                <div class="col-md-3 d-flex justify-content-center align-items-center">
                    <div class="rounded-circle bg-light d-flex justify-content-center align-items-center" style="width: 15em; height: 15em; border: 2px solid #ddd; overflow: hidden;">
                        <img src="/images/profile.svg" alt="Profile" style="width: 100%; height: 100%; object-fit: cover;">
                    </div>
                </div>

                <!-- User Information Section -->
                <div class="col-md-9" style="width: 50%">
                    <!-- Personal Information -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <strong>Nombre:</strong> <span>${employeeSelect.employeeName}</span>
                        </div>
                        <div class="col-md-6">
                            <strong>Apellido:</strong> <span>${employeeSelect.employeeLastName}</span>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <strong>DNI:</strong> <span>${employeeSelect.employeeDni}</span>
                        </div>
                        <div class="col-md-6">
                            <strong>Nacimiento:</strong> 
                            <span>
                                <fmt:formatDate value="${employeeSelect.birthDate}" pattern="dd/MM/yyyy" />
                            </span>
                        </div>
                    </div>

                    <!-- Divisor -->
                    <hr class="my-4" style="border: 0; border-top: 2px solid #49828ff5;">

                    <!-- User Account -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <strong>Correo:</strong>
                            <span>${employeeSelect.userEmployee}@outlook.es</span>
                        </div>
                        <div class="col-md-6">
                            <strong>Activo:</strong>
                            <span> ${employeeSelect.employeeActive ? 'Si' : 'No'} </span>
                        </div>
                    </div>

                    <!-- Divisor -->
                    <hr class="my-4" style="border: 0; border-top: 2px solid #49828ff5;">

                    <!-- Work Information -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <strong>Puesto:</strong> <span>${employeeSelect.employeePosition}</span>
                        </div>
                        <div class="col-md-6">
                            <strong>Equipo:</strong> <span>${employeeSelect.nameTeam}</span>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <strong>Contratación:</strong>
                            <span>
                                <fmt:formatDate value="${employeeSelect.hireDate}" pattern="dd/MM/yyyy" />
                            </span>
                        </div>
                        <div class="col-md-6">
                            <strong>Role:</strong>
                            <span> ${role} </span>
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
      <%@ include file="modalEditUser.jsp" %>
      <!-- Modal para eliminar el ticket -->
      <%@ include file="modalDeleteUser.jsp" %>
          
      <!-- Modal para crear el ticket -->
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

    </main>
  </div>

</body>
</html>



