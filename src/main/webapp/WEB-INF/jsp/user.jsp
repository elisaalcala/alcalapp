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


        <div class="card custom-card shadow ">
          <div class="card-header d-flex align-items-center justify-content-between bg-base">
            <h6 class="m-0 font-weight-bold text-uppercase text-white">Employee - ${employeeSelect.userEmployee}</h6>
            <div>
              <button type="button" class="btn bg-base-light" id="editButton" data-bs-toggle="modal" data-bs-target="#editModalEmployee">
                <i class="fa-solid fa-pen-to-square"></i>
                
              </button>
              <button type="button" class="btn btn-danger ml-1" id="deleteButton" data-bs-toggle="modal" 
                data-bs-target="#deleteModalEmployee">
                <i class="fa-solid fa-trash"></i>
                
              </button>
            </div>
          </div>
          <div class="card-body">

            
          
            

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



