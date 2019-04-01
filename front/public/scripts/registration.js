$('.main-info').on('keyup', function() {
  if ($('.main-info').filter((index, item) => item.value === '').length === 0) {
    $('#registration-btn').animate({backgroundColor: 'green', color: '#fcfcfc'}, 500, 'linear');
    $('#registration-btn').attr("type", "submit");
  } else {
    $('#registration-btn').animate({backgroundColor: '#d5d5d5', color: '#777777'}, 500, 'linear');
    $('#registration-btn').attr("type", "button");
  }
});

$('#registration-btn').on('click', function() {
  // refresh sessionStorage with inputed info
  sessionStorage.clear();
  sessionStorage.setItem('user', JSON.stringify({
    login: $('#login').val(),
    password: $('#password').val(),
    mobile: $('#mobile').val(),
    email: $('#login').val(),
    surname: $('#surname').val(),
    name: $('#name').val(),
    patronum: $('#patronum').val()
  }));
});
