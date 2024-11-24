<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="newUserModal" tabindex="-1" aria-labelledby="newUserModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="newUserModalLabel">Crear Nuevo Usuario</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form id="newUserForm">
                <div class="d-flex justify-content-evenly">
                    <div>
                        <!-- Nombre -->
                        <div class="mb-3">
                            <label for="employeeName" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" id="employeeName" name="employeeName" required>
                        </div>

                        <!-- Apellido -->
                        <div class="mb-3">
                            <label for="employeeLastName" class="form-label">Apellido:</label>
                            <input type="text" class="form-control" id="employeeLastName" name="employeeLastName" required>
                        </div>

                        <!-- DNI -->
                        <div class="mb-3">
                            <label for="employeeDni" class="form-label">DNI:</label>
                            <input type="text" class="form-control" id="employeeDni" name="employeeDni" required>
                        </div>

                        <!-- Fecha de Nacimiento -->
                        <div class="mb-3">
                        <label for="birthDate" class="form-label">Fecha de Nacimiento:</label>
                        <input type="date" class="form-control" id="birthDate" name="birthDate" required>
                        </div>

                        <!-- Divider -->
                        <hr class="my-4">

                        <!-- Equipo -->
                        <div class="mb-3">
                            <label for="nameTeam" class="form-label">Equipo:</label>
                            <select class="form-select" id="nameTeamUser" name="nameTeamUser" required>
                                <option value="" selected disabled>Seleccionar equipo</option>
                                <option value="${team.nameTeam}">${team.nameTeam}</option>
                                <c:forEach items="${createTicketTeamsList}" var="team">
                                    <option value="${team.nameTeam}">${team.nameTeam}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- Puesto -->
                        <div class="mb-3">
                            <label for="employeePosition" class="form-label">Puesto:</label>
                            <input type="text" class="form-control" id="employeePosition" name="employeePosition" required>
                        </div>

                    </div>

                    <div>
                        <!-- Nombre de Usuario -->
                        <div class="mb-3">
                            <label for="userEmployee" class="form-label">Nombre de Usuario:</label>
                            <input type="text" class="form-control" id="userEmployee" name="userEmployee" required>
                        </div>

                        <!-- Contraseña -->
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>

                        <!-- Confirmar Contraseña -->
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirmar Contraseña:</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                        </div>

                        <!-- Divider -->
                        <hr class="my-4">

                        <!-- Equipo -->
                        <div class="mb-3">
                            <label for="role" class="form-label">Rol de usuario:</label>
                            <select class="form-select" id="role" name="role" required>
                                <option value="" selected disabled>Seleccionar rol</option>
                                <c:forEach items="${rolesList}" var="role">
                                    <option value="${role}">${role}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>

                </div>

              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="button" class="btn bg-cyan-800" id="newUserButton">Crear</button>
          </div>
      </div>
  </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("newUserButton").addEventListener("click", function() {
        // Recolectar datos del formulario
        const employeeName = document.getElementById("employeeName").value;
        const employeeLastName = document.getElementById("employeeLastName").value;
        const employeeDni = document.getElementById("employeeDni").value;
        const birthDate = document.getElementById("birthDate").value;
        const nameTeam = document.getElementById("nameTeamUser").value;
        const employeePosition = document.getElementById("employeePosition").value;
        const userEmployee = document.getElementById("userEmployee").value;
        const password = document.getElementById("password").value;
        const role = document.getElementById("role").value;
    
        // Validar la confirmación de la contraseña
        const confirmPassword = document.getElementById("confirmPassword").value;
        if (password !== confirmPassword) {
            alert("Las contraseñas no coinciden.");
            return;
        }
    
        // Crear objeto User
        const userNew = {
            name: userEmployee,
            encodedPassword: password,
            roles: [role]
        };
    
        // Crear objeto Employee
        const employeeNew = {
            employeeName: employeeName,
            employeeLastName: employeeLastName,
            employeeDni: employeeDni,
            birthDate: birthDate,
            hireDate: new Date(),
            userEmployee: userEmployee,
            employeePosition: employeePosition,
            nameTeam: nameTeam
        };
    
        // Hacer la llamada POST
        fetch("/createUser", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
            user: userNew,
            employee: employeeNew 
        })
        })
        .then(response => {
            if (response.ok) {
                // Cerrar el modal
                $('#newUserModal').modal('hide');
    
                // Limpiar los campos del formulario
                document.getElementById("newUserForm").reset();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                alert("Usuario y empleado creados con éxito.");
            } else {
                // Manejar el error
                alert("Error al crear el usuario y el empleado.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Hubo un problema con la solicitud.");
        });
    });
});
</script>
    