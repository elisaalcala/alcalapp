<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="editModalEmployee" tabindex="-1" aria-labelledby="editModalLabelEmployee" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="editModalLabelEmployee">Editar Empleado</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <!-- Formulario para editar el empleado -->
              <form id="editEmployeeForm">
                <div class="d-flex justify-content-evenly">
                    <div>
                        <!-- Nombre -->
                        <div class="mb-3">
                            <label for="editEmployeeName" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" id="editEmployeeName" name="employeeName" value="${employeeSelect.employeeName}" required>
                        </div>

                        <!-- Apellido -->
                        <div class="mb-3">
                            <label for="editEmployeeLastName" class="form-label">Apellido:</label>
                            <input type="text" class="form-control" id="editEmployeeLastName" name="employeeLastName" value="${employeeSelect.employeeLastName}" required>
                        </div>

                        <!-- DNI -->
                        <div class="mb-3">
                            <label for="editEmployeeDni" class="form-label">DNI:</label>
                            <input type="text" class="form-control" id="editEmployeeDni" name="employeeDni" value="${employeeSelect.employeeDni}" required>
                        </div>

                        <!-- Fecha de Nacimiento -->
                        <div class="mb-3">
                            <label for="editBirthDate" class="form-label">Fecha de Nacimiento:</label>
                            <input type="date" class="form-control" id="editBirthDate" name="birthDate" value="${employeeSelect.birthDate}" required>
                        </div>

                        
                    </div>

                    <div>
                        <!-- Equipo -->
                        <div class="mb-3">
                            <label for="editNameTeam" class="form-label">Equipo:</label>
                            <select class="form-select" id="editNameTeam" name="nameTeam" required>
                                <option value="" selected disabled>Seleccionar equipo</option>
                                <c:forEach items="${createTicketTeamsList}" var="team">
                                    <option value="${team.nameTeam}" ${team.nameTeam == employeeSelect.nameTeam ? 'selected' : ''}>
                                        ${team.nameTeam}
                                    </option>
                                </c:forEach>
                                <option value="${team.nameTeam}">${team.nameTeam}</option>
                            </select>
                        </div>

                        <!-- Puesto -->
                        <div class="mb-3">
                            <label for="editEmployeePosition" class="form-label">Puesto:</label>
                            <input type="text" class="form-control" id="editEmployeePosition" name="employeePosition" value="${employeeSelect.employeePosition}" required>
                        </div>

                        <!-- Rol -->
                        <div class="mb-3">
                            <label for="editRole" class="form-label">Rol del Empleado:</label>
                            <select class="form-select" id="editRole" name="role" required>
                                <option value="" selected disabled>Seleccionar rol</option>
                                <c:forEach items="${rolesList}" var="role">
                                    <option value="${role}">
                                        ${role}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="button" class="btn bg-cyan-800" id="saveChangesButtonEmployee">Guardar Cambios</button>
          </div>
      </div>
  </div>
</div>
<script src="/js/user.js"></script>
