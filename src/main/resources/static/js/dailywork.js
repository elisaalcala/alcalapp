

    //Dropdown Dailywork
    document.addEventListener('DOMContentLoaded', (event) => {

        document.querySelectorAll('.createProjectLink').forEach(link => {
            link.addEventListener('click', function(e) {
                var releaseId = this.getAttribute('data-release-id');
                
                // Guardar un marcador para abrir el modal después de la redirección
                localStorage.setItem('openCreateProjectModal', 'true');

                // Redirigir a la página deseada
                window.location.href = `/releases/` + releaseId;
            });
        });


    
        document.querySelectorAll('.deleteProjectLink').forEach(link => {
            link.addEventListener('click', function(e) {
                var releaseId = this.getAttribute('data-release-id');

                // Guardar un marcador para abrir el modal después de la redirección
                localStorage.setItem('openDeleteModalRelease', 'true');

                // Redirigir a la página
                window.location.href = `/releases/`+ releaseId;
            });
        });


        document.querySelectorAll('.assignMeProjectLink').forEach(link => {
            link.addEventListener('click', function(e) {
                // Guardar un marcador para abrir el modal después de la redirección
                var releaseId = this.getAttribute('data-release-id');

                localStorage.setItem('openAssignMeProjectModal', 'true');

                window.location.href = `/releases/`+ releaseId;


            });
        });

        document.querySelectorAll('.assignMeTicketLink').forEach(link => {
            link.addEventListener('click', function(e) {

                localStorage.setItem('openAssignMeTicketModal', 'true');

            });
        });
        

    });
    

    document.addEventListener("DOMContentLoaded", function () {
        // Obtener todos los enlaces que controlan el colapso
        var panelLinks = document.querySelectorAll('.nav-link[data-bs-toggle="collapse"]');
    
        panelLinks.forEach(function (panelLink) {
            // Obtener el target del colapso
            var targetId = panelLink.getAttribute('data-bs-target');
            var collapseElement = document.querySelector(targetId);
    
            if (collapseElement) {
                // Agregar evento de clic
                panelLink.addEventListener('click', function (event) {
                    event.preventDefault(); // Prevenir comportamiento por defecto del enlace
    
                    // Comprobar si el colapso actual está abierto
                    var isShown = collapseElement.classList.contains('show');
    
                    // Cerrar todos los colapsos abiertos, incluyendo el actual si corresponde
                    document.querySelectorAll('.collapse.show').forEach(function (openCollapse) {
                        openCollapse.classList.remove('show'); // Cerrar
                        var associatedLink = document.querySelector(`[data-bs-target="#${openCollapse.id}"]`);
                        if (associatedLink) {
                            associatedLink.classList.add('collapsed'); // Marcar como colapsado
                        }
                    });
    
                    // Si el colapso actual no estaba abierto, abrirlo
                    if (!isShown) {
                        collapseElement.classList.add('show'); // Abrir
                        panelLink.classList.remove('collapsed'); // Marcar como expandido
                    }
                });
            }
        });
    });
    

    
    
    
    
    

    $(document).ready(function() {

        // Array de IDs de los elementos a controlar
        const cardBodyIds = [
         { toggleLinkId: 'toggleBodyTckBlocked', cardBodyId: 'cardBodyTckBlocked', iconId: 'toggleIconTckBlocked' },
         { toggleLinkId: 'toggleBodyTckToDo', cardBodyId: 'cardBodyTckToDo', iconId: 'toggleIconTckToDo' },
         { toggleLinkId: 'toggleBodyCarga', cardBodyId: 'cardBodyCarga', iconId: 'toggleIconCarga' },
         { toggleLinkId: 'toggleBodyBar', cardBodyId: 'cardBodyBar', iconId: 'toggleIconBar' },
         { toggleLinkId: 'toggleBodyPrjToDeploy', cardBodyId: 'cardBodyPrjToDeploy', iconId: 'toggleIconPrjToDeploy' },
         { toggleLinkId: 'toggleBodyTckToDeploy', cardBodyId: 'cardBodyTckToDeploy', iconId: 'toggleIconTckToDeploy' }
       ];
     
       // Añadir el evento de clic para cada tarjeta
       cardBodyIds.forEach(item => {
         const toggleLink = document.getElementById(item.toggleLinkId);
         const cardBody = document.getElementById(item.cardBodyId);
         const toggleIcon = document.getElementById(item.iconId);
     
         // Si el enlace de toggle y el body existen, añadir el evento
         if (toggleLink && cardBody && toggleIcon) {
           toggleLink.addEventListener('click', function (event) {
             event.preventDefault();
             cardBody.classList.toggle('d-none');
     
             // Cambiar la dirección de la flecha según la visibilidad del cardBody
             if (cardBody.classList.contains('d-none')) {
               toggleIcon.classList.remove('fa-chevron-up');
               toggleIcon.classList.add('fa-chevron-down');
             } else {
               toggleIcon.classList.remove('fa-chevron-down');
               toggleIcon.classList.add('fa-chevron-up');
             }
           });
         }
       });

   });


   //Chart Carga Trabajo
   $(document).ready(function() {
        
    const canvas = document.getElementById("chart-carga-trabajo");
    const ctx = canvas.getContext('2d');
    const userEmployee = JSON.parse(canvas.getAttribute('user'));
    const load = JSON.parse(canvas.getAttribute('load'));

    function generateRandomBrightColors(count) {
        const predefinedColors = [
            'rgb(19, 78, 83)', 
            'rgb(160, 217, 217)', 
            'rgb(217, 197, 137)', 
            'rgb(191, 151, 101)',
            'rgb(129, 26, 3)',  
            'rgb(178, 197, 104)', 
            'rgb(120, 147, 66)'   
        ];


        const randomColors = [];
        for (let i = 0; i < count; i++) {
            const randomIndex = Math.floor(Math.random() * predefinedColors.length);
            randomColors.push(predefinedColors[randomIndex]);
        }

        return randomColors;
    }


    // Llamamos a la función para generar colores aleatorios
    const randomColors = generateRandomBrightColors(userEmployee.length);

    // Creamos el gráfico con los colores aleatorios
    const myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: userEmployee,
            datasets: [{
                label: 'Carga por Persona',
                data: load,
                backgroundColor: randomColors, 
                hoverOffset: 4
            }]
        }
    });

    const teamChartLineTck = document.getElementById('teamChartLineTck');
    const teamChartLinePrj = document.getElementById('teamChartLinePrj');
    const teamChartBar = document.getElementById('teamChartBar');

    const employeesTeam = JSON.parse(document.getElementById('employeesTeam').value);
    const employeesTicketsResolved = JSON.parse(document.getElementById('employeesTicketsResolved').value);
    const employeesProjectsResolved = JSON.parse(document.getElementById('employeesProjectsResolved').value);
    
    const monthsJson = JSON.parse(document.getElementById('monthsJson').value);
    const projectsCompletedByTeamJson = JSON.parse(document.getElementById('projectsCompletedByTeamJson').value);
    const ticketsCompletedByTeamJson = JSON.parse(document.getElementById('ticketsCompletedByTeamJson').value);
    const ticketsCompletedByTeamJsonPro = JSON.parse(document.getElementById('ticketsCompletedByTeamJsonPro').value);


    // Definir colores directamente en el script
    const tckBorderColor = 'rgb(255, 99, 132)';
    const prjBorderColor = 'rgb(54, 162, 235)';
    const tckBackgroundColor = 'rgba(255, 99, 132, 0.5)';
    const prjBackgroundColor = 'rgba(54, 162, 235, 0.5)';


    const ranking = {
    labels: employeesTeam,
    datasets: [
        {
        label: 'TCK',
        data: employeesTicketsResolved,
        borderColor: tckBorderColor,
        backgroundColor: tckBackgroundColor,
        },
        {
        label: 'PRJ',
        data: employeesProjectsResolved,
        borderColor: prjBorderColor,
        backgroundColor: prjBackgroundColor,
        }
    ]
    };

    new Chart(teamChartBar, {
    type: 'bar',
    data: ranking,
    options: {
        responsive: true,
        plugins: {
        legend: {
            position: 'top',
        },
        title: {
            display: true,
            text: 'Ranking de tareas finalizadas'
        }
        }
    }
    });

    const dataLinePrj = {
    labels: monthsJson,
    datasets: [
        {
        label: 'PRJ',
        data: projectsCompletedByTeamJson,
        borderColor: prjBorderColor,
        backgroundColor: prjBackgroundColor,
        }
    ]
    };

    const dataLineTck = {
    labels: monthsJson,
    datasets: [
        {
        label: 'No PRO',
        data: ticketsCompletedByTeamJson,
        borderColor: prjBorderColor,
        backgroundColor: prjBackgroundColor,
        },
        {
        label: 'PRO',
        data: ticketsCompletedByTeamJsonPro,
        borderColor: tckBorderColor,
        backgroundColor: tckBackgroundColor,
        }
    ]
    };

    new Chart(teamChartLinePrj, {
    type: 'line',
    data: dataLinePrj,
    options: {
        responsive: true,
        plugins: {
        legend: {
            position: 'top',
        },
        title: {
            display: true,
            text: 'Media de días en finalizar los PRJ'
        }
        }
    }
    });

    new Chart(teamChartLineTck, {
    type: 'line',
    data: dataLineTck,
    options: {
        responsive: true,
        plugins: {
        legend: {
            position: 'top',
        },
        title: {
            display: true,
            text: 'Media de minutos en finalizar los TCK'
        }
        }
    }
    });


});
