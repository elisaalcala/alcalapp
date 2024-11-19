$(document).ready(function() {
     // Array de IDs de los elementos a controlar
     const cardBodyIds = [
        { toggleLinkId: 'toggleBodyPrjToDo', cardBodyId: 'cardBodyPrjToDo', iconId: 'toggleIconPrjToDo' },
        { toggleLinkId: 'toggleBodyTckToDo', cardBodyId: 'cardBodyTckToDo', iconId: 'toggleIconTckToDo' },
        { toggleLinkId: 'toggleBodyPrjToDeploy', cardBodyId: 'cardBodyPrjToDeploy', iconId: 'toggleIconPrjToDeploy' },
        { toggleLinkId: 'toggleBodyTckToDeploy', cardBodyId: 'cardBodyTckToDeploy', iconId: 'toggleIconTckToDeploy' },
        { toggleLinkId: 'toggleBodyPrjFinish', cardBodyId: 'cardBodyPrjFinish', iconId: 'toggleIconPrjFinish' },
        { toggleLinkId: 'toggleBodyTckFinish', cardBodyId: 'cardBodyTckFinish', iconId: 'toggleIconTckFinish' },
        { toggleLinkId: 'toggleBodyProp', cardBodyId: 'cardBodyProp', iconId: 'toggleIconProp' },
        { toggleLinkId: 'toggleBodyTime', cardBodyId: 'cardBodyTime', iconId: 'toggleIconTime' }
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

$(document).ready(function() {
  const ctx = document.getElementById('myChartBar');
  
  const ctxLine = document.getElementById('myChartLine');

  const ctxLineTck = document.getElementById('myChartLineTck');

  const ctxLineTckCreation = document.getElementById('myChartLineTckCreation');

  const DATA_COUNT = 6;
  const NUMBER_CFG = {count: DATA_COUNT, min: 0, max: 50};

  // Obtener los valores dinámicos que fueron pasados por el controlador
  const labels = JSON.parse(document.getElementById('labelsJson').value);
  const tckData = JSON.parse(document.getElementById('tckDataJson').value);
  const prjData = JSON.parse(document.getElementById('prjDataJson').value);
  const tckDataLine = JSON.parse(document.getElementById('tckDataLineJson').value);
  const prjDataLine = JSON.parse(document.getElementById('prjDataLineJson').value);
  const tckDataCreationClosedJson = JSON.parse(document.getElementById('tckDataCreationClosedJson').value);
  const tckDataCreationResolvedJson = JSON.parse(document.getElementById('tckDataCreationResolvedJson').value);


  // Definir colores directamente en el script
  const tckBorderColor = 'rgb(255, 99, 132)';
  const prjBorderColor = 'rgb(54, 162, 235)';
  const tckBackgroundColor = 'rgba(255, 99, 132, 0.5)';
  const prjBackgroundColor = 'rgba(54, 162, 235, 0.5)';

  const dataBar = {
  labels: labels,
  datasets: [
      {
      label: 'TCK',
      data: tckData,
      borderColor: tckBorderColor,
      backgroundColor: tckBackgroundColor,
      },
      {
      label: 'PRJ',
      data: prjData,
      borderColor: prjBorderColor,
      backgroundColor: prjBackgroundColor,
      }
  ]
  };

  const dataLinePrj = {
  labels: labels,
  datasets: [
      {
      label: 'PRJ',
      data: prjDataLine,
      borderColor: prjBorderColor,
      backgroundColor: prjBackgroundColor,
      }
  ]
  };

  const dataLineTck = {
  labels: labels,
  datasets: [
      {
      label: 'TCK',
      data: tckDataLine,
      borderColor: tckBorderColor,
      backgroundColor: tckBackgroundColor,
      }
  ]
  };
  
const dataLineTckCreation = {
  labels: labels,
  datasets: [
  {
      label: 'Closed',
      data: tckDataCreationClosedJson,
      backgroundColor: tckBackgroundColor,
  },
  {
      label: 'Resolved',
      data: tckDataCreationResolvedJson,
      backgroundColor: 'rgba(75, 192, 192, 0.5)',
  },
  ]
};

  new Chart(ctx, {
  type: 'bar',
  data: dataBar,
  options: {
      responsive: true,
      plugins: {
      legend: {
          position: 'top',
      },
      title: {
          display: true,
          text: 'Número de tareas finalizadas'
      }
      }
  }
  });

  new Chart(ctxLine, {
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

  new Chart(ctxLineTck, {
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

  new Chart(ctxLineTckCreation, {
  type: 'bar',
  data: dataLineTckCreation,
  options: {
      plugins: {
      title: {
          display: true,
          text: 'Tickets Creados: Resueltos - Cerrados'
      },
      },
      responsive: true,
      scales: {
      x: {
          stacked: true,
      },
      y: {
          stacked: true
      }
      }
  }
  });



});