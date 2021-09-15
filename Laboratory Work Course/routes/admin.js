var router = require('express').Router();
var Category = require('../models/category');
var JobOffer = require('../models/job');
var User = require('../models/user');
var nodemailer = require('nodemailer');
var smtpTransport = require('nodemailer-smtp-transport');

User.createMapping(function(err,mapping){
	if(err){
		console.log('error creatingMapping');
		console.log(err);
	} else {
		console.log('Mapping created');
		console.log(mapping);
	}
});

var stream =User.synchronize();
var count=0;
stream.on('data',function(){
	count++;
});

stream.on('close',function(){
	console.log("Indexed "+count+" documents");
});

stream.on('error',function(err){
	console.log(err);
});

var transporter = nodemailer.createTransport(smtpTransport({
		host: 'smtp.wp.pl',
    port: 465,
    secure: true, // use SSL
    auth: {
        user: 'will-black@wp.pl',
        pass: 'aA123456Bb'
    }
}));

router.get('/for-employers',function(req,res,next){
  res.render('admin/for-employers',{message:req.flash('success')});
});

router.get('/create-job-offer',function(req,res,next){
  res.render('admin/create-job-offer',{message:req.flash('success')});
});

router.post('/create-job-offer',function(req,res,next){
  var jobOffer = new JobOffer();
	jobOffer.title = req.body.title;
	jobOffer.field = req.body.field;
  jobOffer.type = req.body.type;
  jobOffer.company = req.body.company;
  jobOffer.address = req.body.address;
  jobOffer.startDate = req.body.startDate;
  jobOffer.endDate = req.body.endDate;
  jobOffer.email = req.body.email;
  jobOffer.skills = req.body.skills;
  jobOffer.description = req.body.description;

	User.search({
			query_string:{query:jobOffer.skills}
		},function(err,results){
			if(err) return next(err);
			var data=results.hits.hits.map(function(hit){
				return hit;
			});
			var i = 0;
			for (i; i < data.length; i++) {
				var email = data[i]._source.email;
				var mailOptions = {
    			from: '"LUT Collaborative Portal" <will-black@wp.pl>', // sender address
    			to: email, // list of receivers
    			subject: 'Job Offer', // Subject line
   				text: 'Hi! We have job offer, that might be suitable for you!', // plaintext body
				};
			//Send e-mail
			transporter.sendMail(mailOptions, function(error, info){
    		if(error){
     		   return console.log(error);
    		}
    		console.log('Message sent: ' + info.response);
		});
		}
	});

	jobOffer.save(function(err) {
    if (err) return next(err);
    req.flash('success', 'Successfully added a category');
    return res.redirect('job/' + jobOffer._id);
  });

});

router.get('/add-category',function(req,res,next){
  res.render('admin/add-category',{message:req.flash('success')});
});

router.post('/add-category', function(req, res, next) {
  var category = new Category();
  category.field = req.body.field;
  category.type=req.body.type;

  category.save(function(err) {
    if (err) return next(err);
    req.flash('success', 'Successfully added a category');
    return res.redirect('/add-category');
  });

  });



module.exports=router;
