/*init settings*/
$(document).ready(function() {
  let curUser = JSON.parse(sessionStorage['user']);
  if (curUser['stat'] < 0.9) {
    $('#status-circle').attr('class', 'stat-good');
  } else if (curUser['stat'] > 1.1) {
    $('#status-circle').attr('class', 'stat-bad');
  } else {
    $('#status-circle').attr('class', 'stat-ok');
  }

  $('#stat-coef').text(curUser['stat']);
});

/*show the doctor evaluating form when user hovers doctors id*/
$('.doctor').on('click', function() {
  let docId = $(this).text();
  $.ajax({
    url: 'http://localhost:8080/back_war/vet-clinic-server',
    type: 'post',
    data: {
      code: 'get-doctor-by-id',
      doc_id: docId
    },
    success: function(data) {
      let jsonedData = JSON.parse(data);
      $('#doctor-report-window').css({
        'display': 'flex'
      });

      $('#doctor-name').text(`${jsonedData.name} ${jsonedData.surname}`);

      $('#send-doctor-report-btn').on('click', function() {
        $.ajax({
          url: 'http://localhost:3000/save-doc-eval',
          type: 'post',
          data: {
            doc_id: docId,
            doc_eval: $('#doc-eval').val(),
            doc_report: $('#doc-report').val()
          },
          success: function() {
            console.log('success!');
          },
          error: function() {
            console.log('error!');
          }
        });
      });
    },
    error: function() {
      console.log('error!');
    }
  });
});

/*hide the doctor evaluation menu*/
$('#doctor-report-window').on('click', function() {
  $(this).css({
    'display': 'none'
  });
  $('#send-doctor-report-btn').off();
});

/*don't hide the doctor evaluation menu when the form clicked*/
$('#doctor-report-form').on('click', function(e) {
  e.stopPropagation();
});

/*header appearing*/
$('#menu-icon').click(function () {
  $('header').animate({top: 0}, 500);
  $('#menu-icon').animate({top: '6vh', opacity: 0}, 500);
});

/*header disappearing*/
$('header').on('click', function() {
  $('header').animate({top: '-6vh'}, 500);
  $('#menu-icon').animate({top: '2vh', opacity: 1}, 500);
});

/*on clicking status of the order*/
$('.st-active, .st-inactive').on('click', function() {
  let selfRow = $(this);
  $.ajax({
    url: 'http://localhost:8080/back_war/vet-clinic-server',
    type: 'post',
    data: {
      code: 'change-order-status',
      st_id: selfRow.parent().find('td').first().text()
    },
    success: function() {
      if (selfRow.text() === 'active') {
        selfRow.text('inactive');
        selfRow.removeClass('st-active').addClass('st-inactive');
      } else {
        selfRow.text('active');
        selfRow.removeClass('st-inactive').addClass('st-active');
      }
    },
    error: function() {
      console.log('error');
    }
  });
});
