// init settings
$(document).ready(function() {
  // checks if the user authorized
  if (sessionStorage.isEmpty) {
    // if not, create input to make order with phone number only

  }
});

// get data from inputs and send in to the java-server to save it in the db
$('#confirm-order-btn').on('click', function() {

  if (!sessionStorage.isEmpty) {
    $.ajax({
      url: 'http://localhost:8080/back_war/vet-clinic-server',
      type: 'post',
      data: {
        code: 'confirm-order',
        order_id: $('#order-number').text(),
        doctor_id: $('#doctors option:selected').text(),
        date: $('#order-time').val(),
        client_id: JSON.parse(sessionStorage.user).id,
        real_cost: $('#order-cost').text()
      },
      success: function(data) {
        console.log(data);
      },
      error: function(error) {
        console.log(error);
      }
    });
  }
});

// check doctors status when he selected
$('#doctors').change(function() {
  let self = $('#doctors option:selected');
  $.ajax({
    url: 'http://localhost:3000/check-doctor',
    type: 'post',
    data: {
      doctor: self.text()
    },
    success: function(data) {
      let eval = parseInt(data.eval);
      console.log(eval);
      if (eval < 30) {
        $('#doctor-label').css({
          'color': 'red'
        });
      } else if (eval < 70) {
        $('#doctor-label').css({
          'color': 'yellow'
        });
      } else {
        $('#doctor-label').css({
          'color': 'green'
        });
      }
    },
    error: function() {
      console.log('error');
    }
  });
});
