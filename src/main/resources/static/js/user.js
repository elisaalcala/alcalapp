document.getElementById("newUserButton").addEventListener("click", function() {
    // Recolectar datos del formulario
    const employeeName = document.getElementById("employeeName").value;
    const employeeLastName = document.getElementById("employeeLastName").value;
    const employeeDni = document.getElementById("employeeDni").value;
    const birthDate = document.getElementById("birthDate").value;
    const nameTeam = document.getElementById("nameTeam").value;
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
