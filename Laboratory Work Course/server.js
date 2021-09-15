var express= require('express');
var morgan = require ('morgan');
var mongoose= require('mongoose');
var bodyParser= require ('body-parser');
var ejs= require('ejs');
var ejsmate=require('ejs-mate');
var session=require ('express-session');
var cookieParser=require('cookie-parser');
var flash =require('express-flash');
var MongoStore= require('connect-mongo')(session);
var passport=require('passport');
var secret =require ('./config/secret');
var User= require('./models/user');

var app =express();
mongoose.connect(secret.database,function(err){
	if(err){
		console.log(err);
	}else {
		console.log("connected to the database");
	}

});

//middleware
app.use(express.static(__dirname+'/public'));
app.use(morgan('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.engine('ejs',ejsmate);
app.set('view engine','ejs');
app.use(cookieParser());
app.use(session({
	resave:true,
	saveUninitialized:true,
	secret:secret.secretKey,
	store:new MongoStore({ url:secret.database, autoReconnect:true})
}));
app.use(flash());
app.use(passport.initialize());
app.use(passport.session());
app.use(function(req,res,next){
	res.locals.user=req.user;
	next();
});

app.use(function(req, res, next) {
	Category.find({}, function(err, categories) {
		if (err) return next(err);
		res.locals.categories = categories;
		next();
	});
});


var mainRoutes=require('./routes/main');
app.use(mainRoutes);
var userRoutes=require('./routes/user');
app.use(userRoutes);
var adminRoutes=require('./routes/admin');
app.use(adminRoutes);
var Category = require('./models/category');
var apiRoutes=require('./api/api');
app.use('/api',apiRoutes);



app.get('/',function(req,res){
	res.render('main/index');
});
app.listen(secret.port, function(err){
	if(err) throw err;
	console.log("server is running on port "+ secret.port);

});
