
    //Edit Project
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveChangesButtonProject').addEventListener('click', function() {
            // Obtener los valores editados del formulario
            var projectId = document.getElementById('projectId').value;
            var url = `/projects/`+ projectId + `/edit`;
            
            var editProjectTitle = document.getElementById('editProjectTitle').value;
            var editProjectDescription = document.getElementById('editProjectDescription').value;
            var editProjectPriority = document.getElementById('editProjectPriority').value;
            var editProjectType = document.getElementById('editProjectType').value;
            var editProjectEnvironment = document.getElementById('editProjectEnvironment').value;

            // Crear un objeto con los datos actualizados del ticket
            var editProject = {
                titleProject: editProjectTitle,
                descriptionProject: editProjectDescription,
                typeProject: editProjectType,
                priorityProject: editProjectPriority,
                environmentProject: editProjectEnvironment
            };
            
            // Realizar la solicitud PUT para actualizar el ticket
            fetch(url, {
                method: 'PUT',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(editProject)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al actualizar el ticket');
                }
                // Si la actualización es exitosa, cerrar el modal
                var editModalProject = bootstrap.Modal.getInstance(document.getElementById('editModalProject'));
                editModalProject.hide();
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

    //Asignar Project Modal
    document.addEventListener('DOMContentLoaded', (event) => {

        document.getElementById('saveAssignButtonProject').addEventListener('click', function() {
            
            var employeeAssign = document.querySelector('.assignedEmployee').value;
            var projectId = document.getElementById('projectId').value;
            var url = `/projects/`+ projectId + `/assign`;
            
            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeAssign)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al asignar el ticket');
                }
                // Si la asignación es exitosa, obtener la URL de redirección desde la respuesta JSON
                return response.json();
            })
            .then(data => {
                // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });

        // Script para el botón "Asignarme a mí"
        document.getElementById('assignToMeButtonProject').addEventListener('click', function() {
            
            var selectElement = document.querySelector('.assignedEmployee');
            
            var employeeName = document.getElementById('employeeNameAssignToMe').value;
            var employeeLastName = document.getElementById('employeeLastNameAssignToMe').value;
            
            for (var i = 0; i < selectElement.options.length; i++) {
                if (selectElement.options[i].text === employeeName + ` ` + employeeLastName) {
                    selectElement.options[i].selected = true;
                    break;
                }
            }
        });

        // Script para el enlace "Quitar Asignación"
        document.getElementById('quitAssignProject').addEventListener('click', function() {
            var selectElement = document.querySelector('.assignedEmployee');
            var optionExists = false;

            // Itera sobre las opciones y busca la opción seleccionada
            for (var i = 0; i < selectElement.options.length; i++) {
                if (selectElement.options[i].textContent === 'Sin asignar') {
                    selectElement.options[i].selected = true;
                    optionExists = true;
                    break;
                }
            }

            // Si no existe la opción, la crea
            if (!optionExists) {
                var newOption = document.createElement('option');
                newOption.value = 'Sin asignar';
                newOption.textContent = 'Sin asignar';
                newOption.selected = true;
                selectElement.appendChild(newOption);
            }
        });

        // Evento para ocultar la alerta cuando se pulse cualquier otra parte del documento
        document.addEventListener('click', function(event) {
            var clickedElement = event.target;
            var assignToMeButton = document.getElementById('assignToMeButtonProject');
            var quitAssignButton = document.getElementById('quitAssignProject');
            var selectElement = document.querySelector('.assignedEmployee');

            // Verificar si el clic no proviene del botón "Asignarme a mí", el botón "Quitar Asignación" ni el select
            if (clickedElement !== assignToMeButton && clickedElement !== quitAssignButton && clickedElement !== selectElement) {
                alertMessageAssignProject.style.display = 'none';
            }
        });


    });
    
    //Clone Project
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('cloneProjectButton').addEventListener('click', function() {
                
            // Obtener los valores editados del formulario
            var url = `/projects/create`;
            var cloneProjectTitle = document.getElementById('cloneProjectTitle').value;
            var cloneProjectDescription = document.getElementById('cloneProjectDescription').value;
            var cloneProjectPriority = document.getElementById('cloneProjectPriority').value;
            var cloneProjectType = document.getElementById('cloneProjectType').value;
            var cloneProjectReleaseName = document.getElementById('cloneProjectReleaseName').value;
            var cloneProjectEnvironment = document.getElementById('cloneProjectEnvironment').value;
            var cloneProjectTeamNameAssign = document.getElementById('cloneProjectTeamNameAssign').value;
            
            // Crear un objeto con los datos actualizados del ticket
            var cloneProject = {
                titleProject: cloneProjectTitle,
                descriptionProject: cloneProjectDescription,
                typeProject: cloneProjectType,
                priorityProject: cloneProjectPriority,
                releaseName: cloneProjectReleaseName,
                environmentProject: cloneProjectEnvironment,
                teamNameAssign: cloneProjectTeamNameAssign
            };
            
            // Realizar la solicitud POST para crear el ticket
            fetch(url, {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(cloneProject)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al crear el ticket');
                }
                // Si es exitosa, cerrar el modal
                var cloneModalProject = bootstrap.Modal.getInstance(document.getElementById('cloneModalProject'));
                cloneModalProject.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                return response.json();
            })
            .then(data => {
                // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });

    //Date Format
    document.addEventListener("DOMContentLoaded", function() {
        // Función para formatear la fecha en el formato deseado
        function formatDateTime(dateTime) {
            var options = { 
                year: 'numeric', 
                month: '2-digit', 
                day: '2-digit', 
                hour: '2-digit', 
                minute: '2-digit', 
                second: '2-digit' 
            };
            return new Date(dateTime).toLocaleString(undefined, options);
        }

        // Función para comprobar si la fecha es nula y devolver un mensaje apropiado
        function formatFinishDate(finishDate) {
            if (finishDate == ``) {
                return "No finalizado";
            } else {
                return formatDateTime(finishDate);
            }
        }
        // Formatear las fechas
        var formattedInitialDate = formatDateTime(document.getElementById('initialDateHidden').value);
        var formattedModifyDate = formatDateTime(document.getElementById('modifyDateHidden').value);
        var formattedFinishDate = formatFinishDate(document.getElementById('finishDateHidden').value);

        // Actualizar el contenido HTML con las fechas formateadas
        document.getElementById("initialDate").textContent = formattedInitialDate;
        document.getElementById("modifyDate").textContent = formattedModifyDate;
        document.getElementById("finishDate").textContent = formattedFinishDate;

    });

    //Cambiar estado Project
    document.addEventListener('DOMContentLoaded', function() {
        var changeStatusButtonsProject = document.querySelectorAll('.collapse-item.project-item');
        
        changeStatusButtonsProject.forEach(function(button) {
            button.addEventListener('click', function(event) {
                var status = event.target.textContent.trim();
                var projectId = document.getElementById('projectId').value;
                var url = `/projects/`+ projectId + `/edit`;

                var updatedProject = {
                    statusProject: status
                };

                fetch(url, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatedProject)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al actualizar el Project');
                    }
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
    });

    //Delete Project
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('deleteTicketButtonProject').addEventListener('click', function() {
            var projectId = document.getElementById('projectId').value;
            var url = `/projects/`+ projectId + `/delete`;
            
            // Realizar la solicitud DELETE para eliminar el ticket
            fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(projectId)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el ticket');
                }
                var modalHeader = document.getElementById('modal-header-delete-project');
                var modalBody = document.getElementById('modal-body-delete-project');
                var modalFooter = document.getElementById('modal-footer-delete-project');
                modalBody.classList.add('d-none'); // Ocultar el modal
                modalFooter.classList.add('d-none'); 
                modalHeader.classList.add('d-none');

                // Si la eliminación es exitosa, mostrar mensaje de éxito y ocultar el cuerpo y el pie de página del modal
                document.getElementById('successMessageDeleteProject').style.display = 'block';

                // Cerrar el modal después de 3 segundos y redirigir a la página relevante
                setTimeout(function(){
                    var deleteModalProject = new bootstrap.Modal(document.getElementById('deleteModalProject'));
                    deleteModalProject.hide();
                    
                }, 3000);
                return response.json();
                
            })
            .then(data => {
                // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });    

    //Asignar Project Link
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('assignToMeLinkProject').addEventListener('click', function() {
            var employeeAssignToMe = document.getElementById('userEmployee').value;
            var projectId = document.getElementById('projectId').value;
            var url = `/projects/`+ projectId + `/assign`;
            
            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeAssignToMe)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al asignar el proyecto');
                }
                return response.json();
            })
            .then(data => {
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });


        // Verificar si debemos hacer clic en el botón "Eliminar"
        if (localStorage.getItem('openAssignMeProjectModal') === 'true') {
            // Eliminar la bandera del localStorage para evitar clics adicionales en futuras visitas
            localStorage.removeItem('openAssignMeProjectModal');
            debugger
            // Simular el clic en el botón con id `deleteButton`
            var assignToMeLinkProject = document.getElementById('assignToMeLinkProject');
            if (assignToMeLinkProject) {
                assignToMeLinkProject.click();
            }
        }

    });

    //DesAsignar Ticket Link
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('unassignFromMeLinkProject').addEventListener('click', function() {
            
            var employeeDesAssignToMe = 'Sin asignar';
            var projectId = document.getElementById('projectId').value;
            var url = `/projects/`+ projectId + `/assign`;
            
            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeDesAssignToMe)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al asignar el proyecto');
                }
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

    //Calendar
document.addEventListener('DOMContentLoaded', function () {

    var initialDateHidden = document.getElementById('initialDateHidden').value;
    var tstDateHidden = document.getElementById('tstDate').value;
    var proDateHidden = document.getElementById('proDate').value;

    const daysTag = document.querySelector(".days"),
    currentDate = document.querySelector(".current-date"),
    prevNextIcon = document.querySelectorAll(".icons span");

    let date = new Date(),
    currYear = date.getFullYear(),
    currMonth = date.getMonth();

    
    const months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio","Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    
    function isSameDay(date1, date2) {
        return date1.getFullYear() === date2.getFullYear() &&
            date1.getMonth() === date2.getMonth() &&
            date1.getDate() === date2.getDate();
    }


    const renderCalendar = () => {
        
        let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(), 
        lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(), 
        lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(), 
        lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate(); 
        let liTag = "";

        for (let i = firstDayofMonth; i > 0; i--) { // creating li of previous month last days
            let ad = lastDateofLastMonth - i + 1;
            liTag += '<li class="inactive">' + ad + '</li>';
        }

        for (let i = 1; i <= lastDateofMonth; i++) {
            // Comparación de fecha para cada una de las fechas del proyecto
            let isInitialDate = isSameDay(new Date(currYear, currMonth, i), new Date(initialDateHidden));
            let isTstDate = isSameDay(new Date(currYear, currMonth, i), new Date(tstDateHidden));
            let isProDate = isSameDay(new Date(currYear, currMonth, i), new Date(proDateHidden));


            // Agregar clases de acuerdo a las fechas del proyecto
            let classForDate = '';
            if (isInitialDate) classForDate = 'initial-date" data-tooltip="Inicio del proyecto ';
            if (isTstDate) classForDate = 'tst-date" data-tooltip="Despliegue en TST';
            if (isProDate) classForDate = 'pro-date" data-tooltip="Despliegue a PRO';

            // Agregar clase 'active' si es el día actual
            let isToday = i === date.getDate() && currMonth === new Date().getMonth() && currYear === new Date().getFullYear() ? 'active' : '';

            // Concatenar las clases y el día en la etiqueta li
            liTag += '<li class="' + isToday + ' ' + classForDate + '">' + i + '</li>';
        }


        for (let i = lastDayofMonth; i < 6; i++) { // creating li of next month first days
            let d = i - lastDayofMonth + 1;
            liTag += '<li class="inactive">' + d  + '</li>';
        }
        currentDate.innerText = months[currMonth] +'  '+ currYear; // passing current mon and yr as currentDate text
        daysTag.innerHTML = liTag;
    }
    renderCalendar();

    prevNextIcon.forEach(icon => { 
        icon.addEventListener("click", () => { 
            // if clicked icon is previous icon then decrement current month by 1 else increment it by 1
            
            currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;

            if(currMonth < 0 || currMonth > 11) { // if current month is less than 0 or greater than 11
                // creating a new date of current year & month and pass it as date value
                date = new Date(currYear, currMonth, new Date().getDate());
                currYear = date.getFullYear(); 
                currMonth = date.getMonth(); 
            } else {
                date = new Date(); 
            }
            renderCalendar();
        });
    });
});
