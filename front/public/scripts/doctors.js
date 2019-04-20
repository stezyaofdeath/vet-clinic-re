/*header appearing*/
$('#menu-icon').click(function () {
  $('header').animate({top: '-1'}, 500);
  $('#menu-icon').animate({top: '6vh', opacity: 0,}, 500);
});

/*header disappearing*/
$('header').on('click', function() {
  $('header').animate({top: '-7vh'}, 500);
  $('#menu-icon').animate({top: '2vh', opacity: 1}, 500);
});

$('#spec-filter').on('click', function(e) {
  e.stopPropagation();
});

$('#spec-filter-btn').on('click', function(e) {
  e.stopPropagation();
  // send ajax to the server to get doctors with desirable parameter
  $.ajax({
    url: 'http://localhost:8080/back_war/vet-clinic-server',
    type: 'post',
    data: {
        code: 'get-doctors-by-spec',
        doc_spec: $('#spec-filter').val()
    },
    success: function(data) {
      let jsonedData = JSON.parse(data);

      // clear page from the latest doctors info
      $('.doctor-card').remove();

      // fill page with data with parameter from the server
      console.log(data);
      jsonedData.forEach(function(item) {
        console.log(item, item.id);
        $.ajax({
          url: 'http://localhost:3000/doctor-about-by-id',
          type: 'post',
          data: {
            doc_id: item.id
          },
          success: function(docAbout) {
            $('#main-area').append(`
              <div class="doctor-card">
                <div class="main-info">
                  <div class="doctor-img-cont card">
                    <img class="doctor-img" src="../resources/images/system/user.jpg">
                  </div>
                  <ul class="contacts card">
                    <li>${item.name} ${item.surname}</li>
                    <li>${item.spec_name}</li>
                  </ul>
                </div>
                <div class="about-info card">
                  <p>
                    ${docAbout}
                  </p>
                </div>
              </div>
            `);
          }
        });
      });
    },
    error: function(err) {
      console.log(err);
    }
  });
});
