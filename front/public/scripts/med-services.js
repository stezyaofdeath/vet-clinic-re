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

$('.ms-card').mouseenter(function() {
  $(this).find('.price-block').animate({opacity: '0.9'}, 400);
});

$('.ms-card').mouseleave(function() {
  $(this).find('.price-block').animate({opacity: '0'}, 400);
});

$('.ms-card').on('click', function() {
  $.ajax({
      url: 'http://localhost:8080/back_war/vet-clinic-server',
      type: 'post',
      data: {
          code: 'get-doctors-my-med-service',
          medService: $(this).attr('data-id')
      },
      success: function(doctors) {
        doctors = JSON.parse(doctors);

        $('.doctor-card').remove();

        doctors.forEach(function(item) {
          $('.doctors-area').append(
            `<div class="doctor-card">
              <div class="image-side">
                <img src="../resources/images/system/user.jpg">
              </div>
              <div class="name-side">
                <span>${item[1]} ${item[2]}</span>
              </div>
            </div>`
          );

          $('.doctor-card').last().on('click', function(e) {
            e.stopPropagation();
            // open page with more info about this doctor
            window.open(`http://localhost:3000/doctors#${item[0]}`, '_blank');
          });
        });
      },
      error: function(e) {
        console.log(e);
      }
    });
});

$('.doctors-area').on('click', function() {
  $('.doctor-card').remove();
});
