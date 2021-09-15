var router= require('express').Router();
var User =require ('../models/user');
var Job = require('../models/job');

Job.createMapping(function(err,mapping){
	if(err){
		console.log('error creatingMapping');
		console.log(err);
	} else {
		console.log('Mapping created');
		console.log(mapping);
	}
});

var stream =Job.synchronize();
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

router.get('/',function(req,res){
	res.render('main/index');
});
router.get('/about',function(req,res){
	res.render('main/about');
});

router.get('/employers',function(req,res){
	res.render('main/forEmployers');
});

router.post('/search',function(req,res,next){
	res.redirect('/search?q='+req.body.q);
});

router.get('/search',function(req,res,next){
	if(req.query.q){
		Job.search({
			query_string:{query:req.query.q}
		},function(err,results){
			if(err) return next(err);
			var data=results.hits.hits.map(function(hit){
				return hit;
			});
			res.render('main/search-result',{
				query:req.query.q,
				data:data
			});
		});
	}
});

router.get('/jobs/:id',function(req,res,next){
	Job
	.find({field:req.params.id})
	.populate('field')
	.exec(function(err,jobs){
		if(err) return next(err);
		res.render('main/findjob',{jobs:jobs});
	});
});

router.get('/job/:id',function(req,res,next){
	Job.findById({_id:req.params.id},function(err,job){
		if(err) return next(err);
		res.render('main/job',{
			job:job
		});
	});
});

router.get('/edit/:id',function(req,res,next){
	Job.findById({_id:req.params.id},function(err,job){
		if(err) return next(err);
		res.render('main/edit-job',{
			job:job
		});
	});
});


router.post('/edit/:id',function(req,res,next){

	var job = new Job();
	/*job._id = req.body._id;
	job.title = req.body.title;
	job.field = req.body.field;
	job.type = req.body.type;
	job.address = req.body.address;
	job.startDate = req.body.startDate;
	job.endDate = req.body.endDate;
	job.description = req.body.description;*/

	Job.update({ _id:req.params.id },
		{ title : req.body.title,
			field : req.body.field,
			type : req.body.type,
			company : req.body.company,
			address : req.body.address,
			startDate : req.body.startDate,
			endDate : req.body.endDate,
			description : req.body.description
		}, function(err, results) {
			if(err) {
				console.log(err);
			}
			else {
				console.log(results);
				res.redirect('/job/' + req.params.id);
			}
		});
	});

	module.exports=router;
