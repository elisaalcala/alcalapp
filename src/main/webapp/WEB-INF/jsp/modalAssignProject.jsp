<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div class="modal fade" id="assignModalProject" tabindex="-1" aria-labelledby="assignModalLabelProject" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="assignModalLabelProject">Asignar Proyecto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulario para asignar el ticket -->
                    <form id="assignFormProject">
                        <div class="mb-2">
                            <label for="assignedEmployee" class="form-label">Empleado Asignado:</label>
                            
                            <div class="div-col">
                                <select class="assignedEmployee m-right" name="state">
                                    <option value="Sin asignar" selected disabled>Sin asignar</option>
                                    <c:forEach items="${allEmployees}" var="employeelist"> 
                                        <option value="${employeelist.userEmployee}"
                                                <c:if test="${employeelist.userEmployee == project.employeeUserAssign}">selected</c:if>>
                                            ${employeelist.employeeName} ${employeelist.employeeLastName}
                                        </option>
                                    </c:forEach>
                                </select>
                                <button type="button" class="btn bg-cyan-800" id="assignToMeButtonProject"
                                        <c:if test="${!(project.teamAssign.nameTeam eq employee.team.nameTeam)}">disabled</c:if>>
                                    Asignarme a mí
                                </button>
                            </div>
                            
                            
                        </div>
                        <div id="alertMessageAssignProject" class="alert alert-danger m-top" role="alert" style="display: none;"></div>
                        <a href="#" class="color-cyan" id="quitAssignProject">Quitar Asignación</a>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn bg-cyan-800" id="saveAssignButtonProject">Asignar</button>
                </div>
            </div>
        </div>
      </div>