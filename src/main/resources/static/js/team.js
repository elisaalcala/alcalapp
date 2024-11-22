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
