

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
    
    // Función para controlar los paneles de colapso del la barra lateral
    document.addEventListener("DOMContentLoaded", function() {
        function togglePanel(panelLinkSelector, panelSelector) {
            var panelLink = document.querySelector(panelLinkSelector);
            var panel = document.querySelector(panelSelector);

            panelLink.addEventListener('click', function() {
                if (panel.classList.contains('show')) {
                    panel.classList.remove('show');
                    panelLink.classList.add('collapsed');
                } else {
                    panel.classList.add('show');
                    panelLink.classList.remove('collapsed');
                }
            });
        }

        // Controlar el panel de Estadísticas
        togglePanel('.nav-link[data-target="#collapseCargaTrabajo"]', '#collapseCargaTrabajo');
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
