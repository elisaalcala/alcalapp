


// Delete Employee
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('deleteEmployeeButton').addEventListener('click', function() {
        var employeeId = document.getElementById('employeeId').value; // Asegúrate de que tienes un campo con este ID para el employeeId
        var url = `/users/` + employeeId + `/delete`; // URL para eliminar el empleado
        
        // Realizar la solicitud DELETE para eliminar el empleado
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(employeeId) // Enviar el ID del empleado en el cuerpo de la solicitud
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar el empleado');
            }

            // Ocultar el cuerpo, pie y encabezado del modal
            var modalHeader = document.getElementById('modal-header-delete-employee');
            var modalBody = document.getElementById('modal-body-delete-employee');
            var modalFooter = document.getElementById('modal-footer-delete-employee');
            modalBody.classList.add('d-none'); // Ocultar el cuerpo del modal
            modalFooter.classList.add('d-none'); 
            modalHeader.classList.add('d-none');

            // Mostrar mensaje de éxito
            document.getElementById('successMessageDeleteEmployee').style.display = 'block';

            // Cerrar el modal después de 3 segundos y redirigir a la página relevante
            setTimeout(function(){
                var deleteModalEmployee = new bootstrap.Modal(document.getElementById('deleteModalEmployee'));
                deleteModalEmployee.hide(); // Cerrar el modal
            }, 3000);

            return response.json();
        })
        .then(data => {
            // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
            window.location.href = data.redirectUrl; // Redirigir a la URL proporcionada
        })
        .catch(error => {
            console.error('Error:', error); // Manejo de errores
        });
    });
});


// Edit Employee
document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('saveChangesButtonEmployee').addEventListener('click', function() {
        // Obtener los valores editados del formulario
        var employeeId = document.getElementById('employeeId').value;
        var username = document.getElementById('username').value;
        var url = `/users/` + employeeId + `/edit`;

        var editEmployeeName = document.getElementById('editEmployeeName').value;
        var editEmployeeLastName = document.getElementById('editEmployeeLastName').value;
        var editEmployeeDni = document.getElementById('editEmployeeDni').value;
        var editBirthDate = document.getElementById('editBirthDate').value;
        var editNameTeam = document.getElementById('editNameTeam').value;
        var editEmployeePosition = document.getElementById('editEmployeePosition').value;
        var editRole = document.getElementById('editRole').value;

        // Crear un objeto con los datos actualizados del empleado
        var editEmployee = {
            username: username,
            employeeName: editEmployeeName,
            employeeLastName: editEmployeeLastName,
            employeeDni: editEmployeeDni,
            birthDate: editBirthDate,
            nameTeam: editNameTeam,
            employeePosition: editEmployeePosition,
            role: editRole
        };

        // Realizar la solicitud PUT para actualizar el empleado
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editEmployee)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar el empleado');
            }
            // Si la actualización es exitosa, cerrar el modal
            var editModalEmployee = bootstrap.Modal.getInstance(document.getElementById('editModalEmployee'));
            editModalEmployee.hide();
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
