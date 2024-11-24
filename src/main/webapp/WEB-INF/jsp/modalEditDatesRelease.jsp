<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="modal fade" id="editModalRelease" tabindex="-1" aria-labelledby="editModalLabelRelease" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editModalLabelProject">Editar Release</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="editForm">
            <div class="mb-3">
                <label for="editInitialDate" class="form-label">Fecha de inicio:</label>
                <input type="date" class="form-control" id="editInitialDate" name="initialDate"
                       value="<fmt:formatDate value='${release.initialDate}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="mb-3">
                <label for="editRequirementsDate" class="form-label">Fecha de requerimientos:</label>
                <input type="date" class="form-control" id="editRequirementsDate" name="requirementsDate"
                       value="<fmt:formatDate value='${release.requirementsDate}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="mb-3">
                <label for="editPrjAssignmentDate" class="form-label">Fecha de asignación de proyecto:</label>
                <input type="date" class="form-control" id="editPrjAssignmentDate" name="prjAssignmentDate"
                       value="<fmt:formatDate value='${release.prjAssignmentDate}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="mb-3">
                <label for="editDevelopDate" class="form-label">Fecha de desarrollo:</label>
                <input type="date" class="form-control" id="editDevelopDate" name="developDate"
                       value="<fmt:formatDate value='${release.developDate}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="mb-3">
                <label for="editTstDate" class="form-label">Fecha de pruebas:</label>
                <input type="date" class="form-control" id="editTstDate" name="tstDate"
                       value="<fmt:formatDate value='${release.tstDate}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="mb-3">
                <label for="editUatDate" class="form-label">Fecha de aceptación de usuario:</label>
                <input type="date" class="form-control" id="editUatDate" name="uatDate"
                       value="<fmt:formatDate value='${release.uatDate}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="mb-3">
                <label for="editProDate" class="form-label">Fecha de producción:</label>
                <input type="date" class="form-control" id="editProDate" name="proDate"
                       value="<fmt:formatDate value='${release.proDate}' pattern='yyyy-MM-dd'/>">
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" class="btn bg-cyan-800" id="updateReleaseButton">Actualizar</button>
      </div>
    </div>
  </div>
</div>

