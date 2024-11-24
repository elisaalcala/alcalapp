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
  <script>
    
    //New Team
    document.addEventListener("DOMContentLoaded", function () {
    const newTeamForm = document.getElementById("newTeamForm");
    const newTeamButton = document.getElementById("newTeamButton");

    newTeamButton.addEventListener("click", function () {
        // Validar formulario antes de enviar
        if (!newTeamForm.checkValidity()) {
            newTeamForm.reportValidity();
            return;
        }

        // Recoger datos del formulario
        const teamData = {
            nameTeam: document.getElementById("nameTeam").value,
            descriptionTeam: document.getElementById("descriptionTeam").value
        };

        // Enviar datos al controlador
        fetch("/createTeam", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(teamData)
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error("Error al crear el equipo");
                }
            })
            .then(message => {
                alert(message);
                // Reiniciar formulario después de crear el equipo
                newTeamForm.reset();
                // Cerrar el modal
                const modal = bootstrap.Modal.getInstance(document.getElementById("newTeamModal"));
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                modal.hide();
            })
            .catch(error => {
                console.error(error);
                alert("Hubo un error al crear el equipo. Por favor, intenta nuevamente.");
            });
    });
});

  </script>
  