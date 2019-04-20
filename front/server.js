// link modules
const express = require('express');
const bodyParser = require('body-parser');
const MongoClient = require('mongodb').MongoClient;
const Request = require('request');

// create server
const app = express();
const port = 3000;

// server settings
app.set('view engine', 'pug');
app.use(express.static(__dirname + '/public' ));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

// server's request handlers
app.get('/', function (req, resp) {
    resp.sendFile(__dirname + '/public/pages/master.html');
});

app.get('/confirm-order', function (req, resp) {
    // make ajax-request to java-server to get info about doctors and use it result to render template file
    Request.post({
      url: 'http://localhost:8080/back_war/vet-clinic-server',
      form: {
        code: 'get-order-info',
        service: `${req.query.id}`
      }
    }, function(error, responce, body) {
      if (!error) {
        resp.render(__dirname + '/public/templates/confirm-order.pug', {
            id: req.query.id,
            name: req.query.name,
            cost: req.query.cost,
            doctors: JSON.parse(responce.body)
        });
      }
    });
});

// when error happens server redirecting user to generated from .pug page
app.get('/404-error', function(req, resp) {
  resp.render(__dirname + '/public/templates/404-page.pug', {});
});

// saving report about the error in the db
app.post('/save-error-report', function(req, resp) {
  let mongoClient = new MongoClient('mongodb://localhost:27017/', {useNewUrlParser: true});
  mongoClient.connect(function(error, client) {
    if (error) {
      resp.send('connection to database error!');
    } else {
      client.db('vet-clinic-re').collection('error-reports').insertOne(req.body, function(error) {
        if (error) {
          resp.send('saving to database error!');
        } else {
          resp.send('data successfully saved to db!');
        }
      });
    }
  });
});

app.get('/registration', function(req, resp) {
  resp.sendFile(__dirname + '/public/pages/registration.html');
});

app.post('/registration', function(req, resp) {
  Request.post({
    url: 'http://localhost:8080/back_war/vet-clinic-server',
    form: {
      code: 'user-registration',
      login: req.body.login,
      password: req.body.password,
      mobile: req.body.mobile,

      email: req.body.email,
      surname: req.body.surname,
      name: req.body.name,
      patronum: req.body.patronum
    }
  }, function(error, responce, body) {
    if (!error) {
      resp.sendFile(__dirname + '/public/pages/master.html');
    }
  });
});

app.get('/user-cab', function(req, resp) {
  // make request to java server to get info 'bout this user
  Request.post({
    url: 'http://localhost:8080/back_war/vet-clinic-server',
    form: {
      code: 'get-orders-by-client',
      cl_id: req.query.id
    }
  }, function (error, responce, body) {
    let jsonedResponce = JSON.parse(responce.body);

    resp.render(__dirname + '/public/templates/user-cab.pug', {
        nick: jsonedResponce[0]['cl_login'],
        name: jsonedResponce[0]['cl_name'],
        surname: jsonedResponce[0]['cl_surname'],
        mobile: jsonedResponce[0]['cl_phone'],
        orders: jsonedResponce[1]
    });
  });
});

// gets info 'bout doctors from db and puts it in 'doctors.pug' page
app.get('/doctors', function(req, resp) {
  // request to databases to get info 'bout doctors
  Request.post({
    url: 'http://localhost:8080/back_war/vet-clinic-server',
    form: {
      code: 'get-doctors'
    }
  }, function(error, responce, body) {
    // put responced data in the page
    let jsonedResponce = JSON.parse(responce.body);

    let mongoClient = new MongoClient('mongodb://localhost:27017/', {useNewUrlParser: true});

    mongoClient.connect(function(error, client) {
      if (error) {
        console.log('connection to database error!');
      } else {
        client.db('vet-clinic-re').collection('doctors').find({}, function(error, ress) {
          ress.toArray(function(err, items) {
            for (let j = 0; j < items.length; j++) {
              for (let i = 0; i < jsonedResponce.length; i++) {
                if (items[j].id === jsonedResponce[i].id) {
                  jsonedResponce[i]['info'] = items[j].info;
                }
              }
            }
            resp.render(__dirname + '/public/templates/doctors.pug', {
              doctors: jsonedResponce
            });
          });
        });
      }
    });
  });
});

/*insert info 'bout doctor from user to db'*/
app.post('/save-doc-eval', function(req, resp) {
  let mongoClient = new MongoClient('mongodb://localhost:27017/', {useNewUrlParser: true});
  mongoClient.connect(function(error, client) {
    if (error) {
      console.log('connection to database error!');
    } else {
      client.db('vet-clinic-re').collection('doc-reports').insertOne({
        docId: req.body.doc_id,
        docEval: req.body.doc_eval,
        docReport: req.body.doc_report
      }, function(err, result) {
        if (err) {
          resp.send('error!');
        } else {
          resp.send('success!');
        }
      });
    }
  });
});

app.post('/check-doctor', function(req, resp) {
  let mongoClient = new MongoClient('mongodb://localhost:27017/', {useNewUrlParser: true});
  mongoClient.connect(function(error, client) {
    if (error) {
      console.log('connection to database error!');
    } else {
      client.db('vet-clinic-re').collection('doc-reports').find({docId: req.body.doctor}).toArray(function(err, result) {
        if (err) {
          console.log('error');
        } else {
          let sumEval = 0;
          result.forEach(function(item) {
            sumEval += parseInt(item.docEval);
          });
          resp.send({
            eval: Math.floor(sumEval/result.length)
          });
        }
      });
    }
  });
});

app.get('/clinic-stats', function(req, resp) {
  /*send responce to the server for analytics data and on success render template file with it*/
  resp.sendFile(__dirname + '/public/pages/clinic-stats.html');
});

app.post('/doctor-about-by-id', function(req, resp) {
  let mongoClient = new MongoClient('mongodb://localhost:27017/', {useNewUrlParser: true});
  mongoClient.connect(function(error, client) {
    if (error) {
      console.log('connection to database error!');
    } else {
      client.db('vet-clinic-re').collection('doctors').findOne({id: req.body.doc_id}, function(err, result) {
        if (err) {
          console.log(err);
        }
        resp.send(result.info);
      });
    }
  });
});

// start server to listen the port
app.listen(port, function () {
   console.log(`server listening port-${port}...`);
});
