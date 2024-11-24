<!-- Modal para editar el Team -->
<div class="modal fade" id="editModalTeam" tabindex="-1" aria-labelledby="editModalLabelTeam" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabelTeam">Editar Equipo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Formulario para editar el Team -->
                <form id="editTeamForm">
                    <div class="mb-3">
                        <label for="editTeamName" class="form-label">Nombre del Equipo:</label>
                        <textarea class="form-control" id="editTeamName">${team.nameTeam}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="editTeamDescription" class="form-label">Descripcion del Equipo:</label>
                        <textarea class="form-control" id="editTeamDescription">${team.descriptionTeam}</textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn bg-cyan-800" id="saveChangesButtonTeam">Guardar Cambios</button>
            </div>
        </div>
    </div>
</div>
