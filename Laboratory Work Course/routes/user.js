var router= require('express').Router();
var User= require('../models/user');
var passport=require('passport');
var passportConf=require('../config/passport');


router.get('/login',function(req,res){
	if(req.user) return res.redirect('/');
	res.render('accounts/login',{message:req.flash('loginMessage')});
});

router.post('/login', passport.authenticate('local-login', {
	successRedirect: '/profile',
	failureRedirect: '/login',
	failureFlash: true
}));


router.get('/profile',function(req,res,next){
	User.findOne({_id:req.user._id},function(err,user){
		if(err) return next(err);
		res.render('accounts/profile',{user:user});
	});

});

router.post('/signup',function(req,res,next){
	var user=new User();
	// Changed schema in database !!! Not all fields are being saved
	user.admin = false;	
	user.name = req.body.name;	
	user.email = req.body.email;
	user.password = req.body.password;
	user.skills = req.body.skills;
	user.keywords = req.body.keywords;
	user.dateOfBirth = req.body.dateOfBirth;
	user.country = req.body.country;
	user.gender = req.body.gender;
	user.field = req.body.field;
	user.yearOfStudies = req.body.yearOfStudies;
	user.typeOfStudies = req.body.typeOfStudies;
	user.typeOfJob = req.body.typeOfJob;
	//console.log(req.body.name);


	/*user.profile.name=req.body.name;
	user.password=req.body.password;
	user.email=req.body.email;*/
	User.findOne({email:req.body.email},function(err,existingUser){

		if(existingUser){
			req.flash('errors','Account with that email address already exists');

			return res.redirect('/signup');
		} else {
			user.save(function(err,user){
				if(err) return next (err);

				req.logIn(user,function(err){
					if(err) return next(err);
					res.redirect('/profile');
				});

			});
		}
	});

});
router.get('/signup', function(req, res, next) {
	res.render('accounts/signup',{
		errors: req.flash('errors')
	});

});

router.get('/logout',function(req,res,next){
	req.logout();
	res.redirect('/');
});


router.get('/editProfile',function(req,res,next){
	res.render('accounts/editProfile.ejs',{message: req.flash('success')});


});

//edit profile feature, might be modified according to user attributes and needs

router.post('/editProfile',function(req,res,next){
	var user = new User();	

	User.update({ email:req.body.email },
      { name : req.body.name,
				email : req.body.email,
				dateOfBirth : req.body.dateOfBirth,
				country : req.body.country,
				fieldOfStudy : req.body.fieldOfStudy,
				yearOfStudies : req.body.yearOfStudies,
				typeOfStudies : req.body.typeOfStudies,
				typeOfJob : req.body.typeOfJob,
				skills : req.body.skills,
				keywords : req.body.keywords
			}, function(err, results) {
				if(err) {
					console.log(err);
				}
				else {      
					console.log(results);
      		res.redirect('/profile');
				}
   });
});

module.exports= router;
