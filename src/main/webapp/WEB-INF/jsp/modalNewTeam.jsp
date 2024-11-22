<div class="modal fade" id="newTeamModal" tabindex="-1" aria-labelledby="newTeamModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="newTeamModalLabel">Crear Nuevo Equipo</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="newTeamForm">
            <div class="mb-3">
              <!-- Nombre del equipo -->
              <label for="nameTeam" class="form-label">Nombre del Equipo:</label>
              <input type="text" class="form-control" id="nameTeam" name="nameTeam" required>
            </div>
  
            <div class="mb-3">
              <!-- Descripción del equipo -->
              <label for="descriptionTeam" class="form-label">Descripcion del Equipo:</label>
              <textarea class="form-control" id="descriptionTeam" name="descriptionTeam" rows="3" required></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          <button type="button" class="btn bg-cyan-800" id="newTeamButton">Crear</button>
        </div>
      </div>
    </div>
  </div>
  <script src="/js/team.js"></script>
  