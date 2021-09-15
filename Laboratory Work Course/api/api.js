var router =require('express').Router();
var async = require('async');
var faker = require('faker');
var Category = require('../models/category');
var Job=require('../models/job');

router.get('/:field',function(req,res,next){
  async.waterfall([
    function (callback){
      Category.findOne({field:req.params.field},function(err,category){
        if(err) return next(err);
        callback(null,category);
      });
    },

    function(category,callback){
      for(var i=0;i<30;i++){
        var job=new Job();
        job.field=category._id;
        job.type=faker.name.jobTitle();
        job.title=faker.name.jobTitle();
        job.phrase=faker.lorem.paragraph();
        job.description=faker.lorem.paragraphs();
        job.company=faker.company.companyName();
        job.address=faker.address.city();
        job.startDate=faker.date.past();
        job.endDate=faker.date.future();

        job.save();
      }
    }
  ]);
  res.json({message:'Success'});
});

module.exports=router;
