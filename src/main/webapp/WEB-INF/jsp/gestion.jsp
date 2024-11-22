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
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
              <div class="card-body">
                      <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
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


