
       //Funciones de la barra de navegacion superior
       $(document).ready(function() {
        $('.dropdown-toggle').click(function() {
            $(this).next('.dropdown-menu').toggleClass('show');
        });

        // Cerrar el menú desplegable cuando se hace clic fuera de él
        $(document).click(function(e) {
            if (!$(e.target).closest('.dropdown').length) {
                $('.dropdown-menu').removeClass('show');
            }
        });

        // Función para verificar la URL actual y mostrar u ocultar la barra de búsqueda
        function toggleSearchBar() {
            var currentPath = window.location.pathname;
            var showSearchBarURLs = ['/dailywork', '/private'];
            if (showSearchBarURLs.includes(currentPath)) {
                $('#searchContainer').show();
            } else {
                $('#searchContainer').hide();
            }
        }

        // Llamar a la función toggleSearchBar() al cargar la página
        toggleSearchBar();
    });

    //Personalizar DataTable
    $(document).ready(function() {
         $('table[id^="dataTable"]').DataTable({
            language :
                {
    "processing": "Procesando...",
    "lengthMenu": "Mostrar _MENU_ registros",
    "zeroRecords": "No se encontraron resultados",
    "emptyTable": "Ningún dato disponible en esta tabla",
    "infoEmpty": "No hay coincidencias",
    "infoFiltered": "(filtrado de un total de _MAX_ registros)",
    "search": "Buscar:",
    "loadingRecords": "Cargando...",
    "paginate": {
        "first": "<<",
        "last": ">>",
        "next": ">",
        "previous": "<"
    },
    "aria": {
        "sortAscending": ": Activar para ordenar la columna de manera ascendente",
        "sortDescending": ": Activar para ordenar la columna de manera descendente"
    },
    "buttons": {
        "copy": "Copiar",
        "colvis": "Visibilidad",
        "collection": "Colección",
        "colvisRestore": "Restaurar visibilidad",
        "copyKeys": "Presione ctrl o u2318 + C para copiar los datos de la tabla al portapapeles del sistema. <br \/> <br \/> Para cancelar, haga clic en este mensaje o presione escape.",
        "copySuccess": {
            "1": "Copiada 1 fila al portapapeles",
            "_": "Copiadas %ds fila al portapapeles"
        },
        "copyTitle": "Copiar al portapapeles",
        "csv": "CSV",
        "excel": "Excel",
        "pageLength": {
            "-1": "Mostrar todas las filas",
            "_": "Mostrar %d filas"
        },
        "pdf": "PDF",
        "print": "Imprimir",
        "renameState": "Cambiar nombre",
        "updateState": "Actualizar",
        "createState": "Crear Estado",
        "removeAllStates": "Remover Estados",
        "removeState": "Remover",
        "savedStates": "Estados Guardados",
        "stateRestore": "Estado %d"
    },
    "autoFill": {
        "cancel": "Cancelar",
        "fill": "Rellene todas las celdas con <i>%d<\/i>",
        "fillHorizontal": "Rellenar celdas horizontalmente",
        "fillVertical": "Rellenar celdas verticalmente"
    },
    "decimal": ",",
    "searchBuilder": {
        "add": "Añadir condición",
        "button": {
            "0": "Constructor de búsqueda",
            "_": "Constructor de búsqueda (%d)"
        },
        "clearAll": "Borrar todo",
        "condition": "Condición",
        "conditions": {
            "date": {
                "before": "Antes",
                "between": "Entre",
                "empty": "Vacío",
                "equals": "Igual a",
                "notBetween": "No entre",
                "not": "Diferente de",
                "after": "Después",
                "notEmpty": "No Vacío"
            },
            "number": {
                "between": "Entre",
                "equals": "Igual a",
                "gt": "Mayor a",
                "gte": "Mayor o igual a",
                "lt": "Menor que",
                "lte": "Menor o igual que",
                "notBetween": "No entre",
                "notEmpty": "No vacío",
                "not": "Diferente de",
                "empty": "Vacío"
            },
            "string": {
                "contains": "Contiene",
                "empty": "Vacío",
                "endsWith": "Termina en",
                "equals": "Igual a",
                "startsWith": "Empieza con",
                "not": "Diferente de",
                "notContains": "No Contiene",
                "notStartsWith": "No empieza con",
                "notEndsWith": "No termina con",
                "notEmpty": "No Vacío"
            },
            "array": {
                "not": "Diferente de",
                "equals": "Igual",
                "empty": "Vacío",
                "contains": "Contiene",
                "notEmpty": "No Vacío",
                "without": "Sin"
            }
        },
        "data": "Data",
        "deleteTitle": "Eliminar regla de filtrado",
        "leftTitle": "Criterios anulados",
        "logicAnd": "Y",
        "logicOr": "O",
        "rightTitle": "Criterios de sangría",
        "title": {
            "0": "Constructor de búsqueda",
            "_": "Constructor de búsqueda (%d)"
        },
        "value": "Valor"
    },
    "searchPanes": {
        "clearMessage": "Borrar todo",
        "collapse": {
            "0": "Paneles de búsqueda",
            "_": "Paneles de búsqueda (%d)"
        },
        "count": "{total}",
        "countFiltered": "{shown} ({total})",
        "emptyPanes": "Sin paneles de búsqueda",
        "loadMessage": "Cargando paneles de búsqueda",
        "title": "Filtros Activos - %d",
        "showMessage": "Mostrar Todo",
        "collapseMessage": "Colapsar Todo"
    },
    "select": {
        "cells": {
            "1": "1 celda seleccionada",
            "_": "%d celdas seleccionadas"
        },
        "columns": {
            "1": "1 columna seleccionada",
            "_": "%d columnas seleccionadas"
        },
        "rows": {
            "1": "1 fila seleccionada",
            "_": "%d filas seleccionadas"
        }
    },
    "thousands": ".",
    "datetime": {
        "previous": "Anterior",
        "hours": "Horas",
        "minutes": "Minutos",
        "seconds": "Segundos",
        "unknown": "-",
        "amPm": [
            "AM",
            "PM"
        ],
        "months": {
            "0": "Enero",
            "1": "Febrero",
            "10": "Noviembre",
            "11": "Diciembre",
            "2": "Marzo",
            "3": "Abril",
            "4": "Mayo",
            "5": "Junio",
            "6": "Julio",
            "7": "Agosto",
            "8": "Septiembre",
            "9": "Octubre"
        },
        "weekdays": {
            "0": "Dom",
            "1": "Lun",
            "2": "Mar",
            "4": "Jue",
            "5": "Vie",
            "3": "Mié",
            "6": "Sáb"
        },
        "next": "Próximo"
    },
    "editor": {
        "close": "Cerrar",
        "create": {
            "button": "Nuevo",
            "title": "Crear Nuevo Registro",
            "submit": "Crear"
        },
        "edit": {
            "button": "Editar",
            "title": "Editar Registro",
            "submit": "Actualizar"
        },
        "remove": {
            "button": "Eliminar",
            "title": "Eliminar Registro",
            "submit": "Eliminar",
            "confirm": {
                "_": "¿Está seguro de que desea eliminar %d filas?",
                "1": "¿Está seguro de que desea eliminar 1 fila?"
            }
        },
        "error": {
            "system": "Ha ocurrido un error en el sistema (<a target=\"\\\" rel=\"\\ nofollow\" href=\"\\\">Más información&lt;\\\/a&gt;).<\/a>"
        },
        "multi": {
            "title": "Múltiples Valores",
            "restore": "Deshacer Cambios",
            "noMulti": "Este registro puede ser editado individualmente, pero no como parte de un grupo.",
            "info": "Los elementos seleccionados contienen diferentes valores para este registro. Para editar y establecer todos los elementos de este registro con el mismo valor, haga clic o pulse aquí, de lo contrario conservarán sus valores individuales."
        }
    },
    "info": "Total : _TOTAL_ registros",
    "stateRestore": {
        "creationModal": {
            "button": "Crear",
            "name": "Nombre:",
            "order": "Clasificación",
            "paging": "Paginación",
            "select": "Seleccionar",
            "columns": {
                "search": "Búsqueda de Columna",
                "visible": "Visibilidad de Columna"
            },
            "title": "Crear Nuevo Estado",
            "toggleLabel": "Incluir:",
            "scroller": "Posición de desplazamiento",
            "search": "Búsqueda",
            "searchBuilder": "Búsqueda avanzada"
        },
        "removeJoiner": "y",
        "removeSubmit": "Eliminar",
        "renameButton": "Cambiar Nombre",
        "duplicateError": "Ya existe un Estado con este nombre.",
        "emptyStates": "No hay Estados guardados",
        "removeTitle": "Remover Estado",
        "renameTitle": "Cambiar Nombre Estado",
        "emptyError": "El nombre no puede estar vacío.",
        "removeConfirm": "¿Seguro que quiere eliminar %s?",
        "removeError": "Error al eliminar el Estado",
        "renameLabel": "Nuevo nombre para %s:"
    },
    "infoThousands": "."
} 
            
         });
    });
    

    // Agregar evento click a todas las filas de la tabla DataTable
    document.addEventListener("DOMContentLoaded", function() {

      //Tickets
      document.querySelectorAll('.ticket-row').forEach(row => {
        row.addEventListener('click', () => {
          // Obtener el ID del ticket desde el atributo data-ticket-id de la fila
          const ticketId = row.getAttribute('data-ticket-id');
          if (ticketId) {
            // Redireccionar a la página correspondiente al ticket
            window.location.href = 'tickets/'+ ticketId;
          }
        });
      });

      //Projects
      document.querySelectorAll('.proyecto-row').forEach(row => {
        row.addEventListener('click', () => {
          // Obtener el ID del ticket desde el atributo data-ticket-id de la fila
          const proyectoId = row.getAttribute('data-proyecto-id');
          if (proyectoId) {
            // Redireccionar a la página correspondiente al ticket
            window.location.href = 'projects/'+ proyectoId;
          }
        });
      });

      //Releases
      document.querySelectorAll('.release-row').forEach(row => {
        row.addEventListener('click', () => {
          // Obtener el ID del ticket desde el atributo data-ticket-id de la fila
          const releaseId = row.getAttribute('data-release-id');
          if (releaseId) {
            // Redireccionar a la página correspondiente al ticket
            window.location.href = 'releases/'+ releaseId;
          }
        });
      });

      //Team
      document.querySelectorAll('.team-row').forEach(row => {
        row.addEventListener('click', () => {
          // Obtener el ID del ticket desde el atributo data-ticket-id de la fila
          const teamId = row.getAttribute('data-team-id');
          if (teamId) {
            // Redireccionar a la página correspondiente al ticket
            window.location.href = 'teams/'+ teamId;
          }
        });
      });

      //employee
      document.querySelectorAll('.employee-row').forEach(row => {
        row.addEventListener('click', () => {
          // Obtener el ID del ticket desde el atributo data-ticket-id de la fila
          const employeeId = row.getAttribute('data-employee-id');
          if (employeeId) {
            // Redireccionar a la página correspondiente al ticket
            window.location.href = 'users/'+ employeeId;
          }
        });
      });

    });
    
    //Create Ticket
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveTicketButton').addEventListener('click', function() {
           
            // Obtener los valores editados del formulario
            var url = `/tickets/create`;
            var createTicketTitle = document.getElementById('createTicketTitle').value;
            var createTicketDescription = document.getElementById('createTicketDescription').value;
            var createTicketPriority = document.getElementById('createTicketPriority').value;
            var createTicketEnvironment = document.getElementById('createTicketEnvironment').value;
            var createTicketTeamNameAssign = document.getElementById('createTicketTeamNameAssign').value;

            // Crear un objeto con los datos actualizados del ticket
            var createTicket = {
                titleTicket: createTicketTitle,
                descriptionTicket: createTicketDescription,
                priorityTicket: createTicketPriority,
                environmentTicket: createTicketEnvironment,
                teamNameAssign: createTicketTeamNameAssign
            };
            
            // Realizar la solicitud POST para crear el ticket
            fetch(url, {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(createTicket)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al crear el ticket');
                }
                // Si es exitosa, cerrar el modal
                var createModal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                createModal.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                window.location.href = response.url;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });

    //Create Release
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveReleaseButton').addEventListener('click', function() {
           
            // Obtener los valores editados del formulario
            var url = `/releases/create`;
            var createInitialDate = document.getElementById('createInitialDate').value;
            var createRequirementsDate = document.getElementById('createRequirementsDate').value;
            var createPrjAssignmentDate = document.getElementById('createPrjAssignmentDate').value;
            var createDevelopDate = document.getElementById('createDevelopDate').value;
            var createTstDate = document.getElementById('createTstDate').value;
            var createUatDate = document.getElementById('createUatDate').value;
            var createProDate = document.getElementById('createProDate').value;

            // Crear un objeto con los datos actualizados del proyecto
            var createRelease = {
                initialDate: createInitialDate,
                requirementsDate: createRequirementsDate,
                prjAssignmentDate: createPrjAssignmentDate,
                developDate: createDevelopDate,
                tstDate: createTstDate,
                uatDate: createUatDate,
                proDate: createProDate
            };

            // Realizar la solicitud POST para crear el ticket
            fetch(url, {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(createRelease)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al crear el ticket');
                }
                // Si es exitosa, cerrar el modal
                var createModalRelease = bootstrap.Modal.getInstance(document.getElementById('createModalRelease'));
                createModalRelease.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                window.location.href = response.url;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });

    
    //Logout
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("logoutButton").addEventListener("click", function() {
            var url = `/logout`;
            fetch(url, {
                method: 'GET',
                headers: {
                'Content-Type': 'application/json'
                }
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al cerrar sesion');
                }
                
                var createModalRelease = bootstrap.Modal.getInstance(document.getElementById('logoutModal'));
                createModalRelease.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                window.location.href = response.url;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });

    document.addEventListener('DOMContentLoaded', function() {
        flatpickr("#calendar", {
            enableTime: false, 
            dateFormat: "Y-m-d", 
            showButtonPanel: true,
            inline: true,
            locale: "es",
        });
    });

    
    document.addEventListener('DOMContentLoaded', function() {
        const sidebarToggle = document.getElementById('sidebarToggle');
        const sidebar = document.getElementById('accordionSidebar');

        // Recuperar el estado del sidebar desde localStorage
        const isCollapsed = localStorage.getItem('sidebarCollapsed') === 'true';
        if (isCollapsed) {
            sidebar.classList.add('collapsed');
            sidebarToggle.innerHTML = '<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>';
        }

        sidebarToggle.addEventListener('click', function() {
            sidebar.classList.toggle('collapsed');
            
            // Cambiar el icono del toggle
            sidebarToggle.innerHTML = sidebar.classList.contains('collapsed')
                ? '<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>'
                : '<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>';
            
            // Guardar el estado en localStorage
            localStorage.setItem('sidebarCollapsed', sidebar.classList.contains('collapsed'));
        });
    });

    
// Migas de pan
document.addEventListener("DOMContentLoaded", function () {
    const breadcrumbContainer = document.getElementById('breadcrumb-container');
    const currentPage = document.getElementById('currentPage').value;
    const path = window.location.pathname.split('/').filter(segment => segment);

    function transformBreadcrumbs(breadcrumbs) {
        const replacements = {
            "projects": "PROYECTOS",
            "tickets": "INCIDENCIAS",
            "releases": "RELEASES",
            "users": "GESTION",
            "teams": "GESTION"
        };

        return breadcrumbs.map(item => replacements[item] || item);
    }

    const transformedList = transformBreadcrumbs(path);
    let breadcrumbHTML = `<nav aria-label="breadcrumb">
        <ol class="breadcrumb margin-bottom-0">
            <li class="breadcrumb-item"><a class="link-text" href="/dailywork">Trabajo Diario</a></li>`;

    let href = ``;

    if (currentPage !== "TRABAJO DIARIO") {
        // Verificar si la página actual es "MI PERFIL" o "MI EQUIPO" para omitir "USUARIOS" o "EQUIPOS"
        if (currentPage === "MI PERFIL" || currentPage === "MI EQUIPO") {
            breadcrumbHTML += `<li class="breadcrumb-item active" aria-current="page">${currentPage}</li>`;
        } else {
            for (let i = 0; i < path.length; i++) {
                href += `/` + path[i];

                if (i === path.length - 1) {
                    breadcrumbHTML += `<li class="breadcrumb-item active" aria-current="page">${currentPage}</li>`;
                } else {
                    // Si la miga actual es "GESTION", agrega el evento para ir atrás
                    if (transformedList[i] === "GESTION") {
                        breadcrumbHTML += `<li class="breadcrumb-item">
                            <a class="link-text" href="javascript:void(0);" onclick="history.back();">GESTION</a>
                        </li>`;
                    } else {
                        breadcrumbHTML += `<li class="breadcrumb-item">
                            <a class="link-text" href="${href}">${transformedList[i].charAt(0).toUpperCase() + transformedList[i].slice(1)}</a>
                        </li>`;
                    }
                }
            }
        }
    }

    breadcrumbHTML += '</ol></nav>';
    breadcrumbContainer.innerHTML = breadcrumbHTML;
});





