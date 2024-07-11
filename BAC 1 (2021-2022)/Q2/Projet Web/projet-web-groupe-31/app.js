const express = require('express');

const createError = require('http-errors');
const path = require('path');

const session = require('express-session')
const logger = require('morgan');

const config = require('./config');

const indexRouter = require('./routes/index');
const questionsRouter = require('./routes/questions');
const answersRouter = require('./routes/answers');
const usersRouter = require('./routes/users');
const adminRouter = require('./routes/admin');
const memberRouter = require('./routes/members');

const app = express();
const port = 3000;

const hbs = require('hbs');
hbs.registerPartials(__dirname + '/views/partials');

hbs.registerHelper('eq', function (a, b) {
  return a === b;
});

// Setup views folder and handlebar engine
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

app.use(logger('dev')); // Log each request
app.use(express.urlencoded({ extended: false })); // Decode form values
app.use(express.static(path.join(__dirname, 'public'))); // Get static files from public folder

app.use(session({ secret: config.secret, resave: false, saveUninitialized: false }))

app.use('/', indexRouter);
app.use('/questions', questionsRouter);
app.use('/answers', answersRouter);
app.use('/users', usersRouter);
app.use('/admin', adminRouter);
app.use('/members', memberRouter);

// Create error on page not found
app.use((req, res, next) => next(createError(404)) ); 

// Show error hbs page
app.use((error, req, res, next) => {
  res.status(error.status || 500);
  res.render('error', { error });
  console.log(error);
});

// Launch server
app.listen(port, () => console.log('App listening on port ' + port) );
