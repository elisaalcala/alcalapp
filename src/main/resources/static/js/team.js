


// Edit Team
document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('saveChangesButtonTeam').addEventListener('click', function() {
        // Obtener los valores editados del formulario
        var teamId = document.getElementById('teamId').value;
        var url = `/teams/` + teamId + `/edit`;

        var editTeamName = document.getElementById('editTeamName').value;
        var editTeamDescription = document.getElementById('editTeamDescription').value;

        // Crear un objeto con los datos actualizados del equipo
        var editTeam = {
            nameTeam: editTeamName,
            descriptionTeam: editTeamDescription
        };

        // Realizar la solicitud PUT para actualizar el equipo
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editTeam)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar el equipo');
            }
            // Si la actualización es exitosa, cerrar el modal
            var editModalTeam = bootstrap.Modal.getInstance(document.getElementById('editModalTeam'));
            editModalTeam.hide();
            document.body.classList.remove('modal-open');
            document.querySelector('.modal-backdrop').remove();
            return response.json();
        })
        .then(data => {
            window.location.href = data.redirectUrl;
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});

// Delete Team
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('deleteTeamButton').addEventListener('click', function() {
        var teamId = document.getElementById('teamId').value;  // Asegúrate de que tienes un campo con este ID para el teamId
        var url = `/teams/` + teamId + `/delete`;  // URL para eliminar el equipo
        
        // Realizar la solicitud DELETE para eliminar el equipo
        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(teamId)  // Enviar el ID del equipo en el cuerpo de la solicitud
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar el equipo');
            }

            // Ocultar el cuerpo, pie y encabezado del modal
            var modalHeader = document.getElementById('modal-header-delete-team');
            var modalBody = document.getElementById('modal-body-delete-team');
            var modalFooter = document.getElementById('modal-footer-delete-team');
            modalBody.classList.add('d-none');  // Ocultar el cuerpo del modal
            modalFooter.classList.add('d-none'); 
            modalHeader.classList.add('d-none');

            // Mostrar mensaje de éxito
            document.getElementById('successMessageDeleteTeam').style.display = 'block';

            // Cerrar el modal después de 3 segundos y redirigir a la página relevante
            setTimeout(function(){
                var deleteModalTeam = new bootstrap.Modal(document.getElementById('deleteModalTeam'));
                deleteModalTeam.hide();  // Cerrar el modal
            }, 3000);

            return response.json();
        })
        .then(data => {
            // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
            window.location.href = data.redirectUrl;  // Redirigir a la URL proporcionada
        })
        .catch(error => {
            console.error('Error:', error);  // Manejo de errores
        });
    });
});

 // Agregar evento click a todas las filas de la tabla DataTable
 document.addEventListener("DOMContentLoaded", function() {
    //employee
    document.querySelectorAll('.empleado-row-per-team-2').forEach(row => {
      row.addEventListener('click', () => {
        // Obtener el ID del ticket desde el atributo data-ticket-id de la fila
        const employeeId = row.getAttribute('data-empleado-row-per-team-2');
        if (employeeId) {
          // Redireccionar a la página correspondiente al ticket
          window.location.href = '/users/'+ employeeId;
        }
      });
    });

  });
