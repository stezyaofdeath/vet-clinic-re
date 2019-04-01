/*init settings*/
$(document).ready(function() {
  let curUser = sessionStorage['user'];
  if (curUser !== undefined) {
    curUser = JSON.parse(sessionStorage['user']);
    // work with cur user if it exist
    $('#profile-name').text(`${curUser['name']} ${curUser['surname']}`);

    $('#log-in').on('click', function (event) {
      event.stopPropagation();
      // make request to nodejs server to get user-cab form
      window.location.href = `http://localhost:3000/user-cab?id=${curUser['id']}`;
    });
  } else {
    // logging in if user didnt authorize
    $('#log-in').on('click', function (event) {
        event.stopPropagation();

        let modal = $('#my-modal');

        // make modal window appear
        modal.css({
            'display': 'block'
        });

        // closing auth window after clicking outside the auth window
        $(window).on('click', function (event) {
            if (event.target === modal.get(0)) {
                modal.css({
                    'display': 'none'
                });
            }
        });

        // closing auth window after clicking the cross
        $('#auth-modal-close').on('click', function () {
            modal.css({
                'display': 'none'
            });
        });


        // set event-handlers on it controls
        $('#auth-check').on('click', function () {
            $.ajax({
                url: 'http://localhost:8080/back_war/vet-clinic-server',
                type: 'post',
                data: {
                    code: 'authorization',
                    login: $('#auth-login').val(),
                    password: $('#auth-password').val()
                },
                success: function (data) {
                  let jsonedData = JSON.parse(data);

                  // set response data as profile
                  $('#profile-name').text(`${jsonedData['name']} ${jsonedData['surname']}`);
                  sessionStorage.clear();
                  sessionStorage.setItem('user', JSON.stringify(jsonedData));

                  $('#log-in').on('click', function (event) {
                    event.stopPropagation();
                    // make request to nodejs server to get user-cab form
                    window.location.href = `http://localhost:3000/user-cab?id=${JSON.parse(sessionStorage['user']['id'])}`;
                  });
                },
                error: function (error) {
                    console.log(error);
                }
            });
        });
    });
  }
});

/*scrolling*/
$(document).bind('keydown', function (event) {
    if (event.key === 'ArrowRight') {
        $('html, body').animate({scrollTop : `+=${window.innerHeight}`}, 500);
    }
    if (event.key === 'ArrowLeft') {
        $('html, body').animate({scrollTop : `-=${window.innerHeight}`}, 500);
    }
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

/*testings*/
$('#clinic-logo').on('click', function (event) {
    event.stopPropagation();

    /*let item = {
        'ID': '1',
        'name': 'Валера',
        'cost': '?'
    };

    window.location.href = `http://localhost:3000/confirm-order?id=${item['ID']}&name=${item['name']}&cost=${item['cost']}`;*/
    //window.location.href = `http://localhost:3000/404-error`;

    // make request to nodejs server to get user-cab form
    window.location.href = `http://localhost:3000/user-cab?id=${sessionStorage['user'].id}`;
});

/*making order event*/
let typingTimer;
let doneTypingInterval = 150;

$('#med-serv-search').on('keyup', function() {
    clearTimeout(typingTimer);
    typingTimer = setTimeout(function() {
        $.ajax({
            url: 'http://localhost:8080/back_war/vet-clinic-server',
            type: 'post',
            data: {
                'code': 'med-serv-search',
                'info': $('#med-serv-search').val()
            },
            success: function(data) {
                /// add responced info to the correct ul
                let jsonedData = JSON.parse(data);
                // remove all li's from the ul
                $('#results-block').empty();
                // create li for every result-array item and add it to #result-block ul
                jsonedData.forEach(function (item) {
                    $('#results-block').append(`
                        <li>
                            <span>${item['name']}</span>
                            <span>${item['cost']}$</span>
                        </li>
                    `);
                    // set handler on last dynamicly created li
                    $('#results-block li').last().on('click', function () {
                        window.location.href = `http://localhost:3000/confirm-order?id=${item['ID']}&name=${item['name']}&cost=${item['cost']}`;
                    });
                });
            },
            error: function() {
                console.log('error!');
            }
        })
    }, doneTypingInterval);
});

$('#med-serv-search').on('keydown', function() {
    clearTimeout(typingTimer);
});
