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
