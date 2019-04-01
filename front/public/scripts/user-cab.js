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
