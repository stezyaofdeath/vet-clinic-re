let statisticData;

$(document).ready(function() {
  $.ajax({
    url: 'http://localhost:8080/back_war/vet-clinic-server',
    type: 'post',
    data: {
      code: 'get-clinic-stats'
    },
    success: function(data) {
      let jsonedData = JSON.parse(data);
      console.log(jsonedData);

      /*draw finances by mounthses chart*/
      let financesByMonthses = [
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0
      ];
      let financesByMonthsesReal = jsonedData[0];
      for (let key in financesByMonthsesReal) {
        financesByMonthses[parseInt(key) - 1] = parseInt(financesByMonthsesReal[key]);
      }
      let popCanvas = $('#finances-canvas');
      let financesChart = new Chart(popCanvas, {
        type: 'bar',
        data: {
          labels: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
          datasets: [{
            label: 'Прибыль за 2019',
            data: financesByMonthses,
            backgroundColor: financesByMonthses.map((item, index) => `rgba(${parseInt(Math.random()*255)}, ${parseInt(Math.random()*255)}, ${parseInt(Math.random()*255)}, 0.6)`)
          }]
        },
        options: {
          maintainAspectRatio: false,
        }
      });

      /*set the most expencieve order*/
      $('#expencievest-order').text(`${jsonedData[2]['expencievest']}$`);

      /*draw orders chart*/
      let ordersLabel = [];
      let ordersCount = [];
      let ordersCountReal = jsonedData[4];
      ordersCountReal.forEach(function(item) {
        ordersLabel.push(item['serv_name']);
        ordersCount.push(parseInt(item['serv_count']));
      });
      let servCanvas = $('#orders-canvas');
      let servChart = new Chart(servCanvas, {
        type: 'doughnut',
        data: {
          labels: ordersLabel,
          datasets: [{
            label: 'Структура заказов за 2019',
            data: ordersCount,
            backgroundColor: ordersCount.map((item, index) => `rgba(${parseInt(Math.random()*255)}, ${parseInt(Math.random()*255)}, ${parseInt(Math.random()*255)}, 0.6)`)
          }]
        },
        options: {
          maintainAspectRatio: false,
          legend: {
            display: null
          }
        }
      });

      /*draw specs chart*/
      let specsLabel = [];
      let specsCount = [];
      let specsCountReal = jsonedData[3];
      specsCountReal.forEach(function(item) {
        specsLabel.push(item['spec_name']);
        specsCount.push(parseInt(item['spec_count']));
      });
      let specCanvas = $('#specialisations-canvas');
      let specChart = new Chart(specCanvas, {
        type: 'doughnut',
        data: {
          labels: specsLabel,
          datasets: [{
            label: 'Структура специализаций за 2019',
            data: specsCount,
            backgroundColor: ordersCount.map((item, index) => `rgba(${parseInt(Math.random()*255)}, ${parseInt(Math.random()*255)}, ${parseInt(Math.random()*255)}, 0.6)`)
          }]
        },
        options: {
          maintainAspectRatio: false,
          legend: {
            display: null
          }
        }
      });

      /*set popularest doctor*/
      $('#popularest-doctor p').text(jsonedData['doctorest']);
    },
    error: function() {
      console.log('error!');
    }
  });
});
