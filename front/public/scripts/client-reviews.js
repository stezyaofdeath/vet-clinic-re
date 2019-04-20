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

$('.r-card').on('click', function() {
  console.log('jed');
  $.ajax({
      url: 'http://localhost:8080/back_war/vet-clinic-server',
      type: 'post',
      data: {
          code: 'get-doctor-by-id',
          doc_id: $(this).attr('data-doc')
      },
      success: function(doctor) {
        doctor = JSON.parse(doctor);
        console.log(doctor);

        $('.doctor-card').remove();

        $('.doctors-area').append(
          `<div class="doctor-card">
            <div class="image-side">
              <img src="../resources/images/system/user.jpg">
            </div>
            <div class="name-side">
              <span>${doctor.name} ${doctor.surname}</span>
            </div>
          </div>`
        );

        $('.doctor-card').last().on('click', function(e) {
          e.stopPropagation();
          // open page with more info about this doctor
          window.open(`http://localhost:3000/doctors#${doctor.ID}`, '_blank');
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
