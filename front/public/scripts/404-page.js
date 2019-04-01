$('#neon-this').novacancy({
    'reblinkProbability': 0.3,
    'blinkMin': 0.1,
    'blinkMax': 0.2,
    'loopMin': 1,
    'loopMax': 2,
    'color': 'red',
    'glow': ['0 0 80px red', '0 0 30px red', '0 0 6px red'],
    'off': 3,
    'blink': 0,
    'autoOn': true
});

$('#send-report-btn').on('click', function() {
  $.ajax({
    url: `http://localhost:3000/save-error-report`,
    type: 'post',
    data: {
      name: `${$('#name-field').val()}`,
      age: `${$('#age-field').val()}`,
      reason: `${$('#reason-field').val()}`,
      info: `${$('#info-field').val()}`
    },
    success: function(data) {
      console.log(data);
    },
    error: function(error) {
      console.log(error);
    }
  });
});
