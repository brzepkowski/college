var mongoose = require('mongoose');
var mongoosastic = require('mongoosastic');
var Schema= mongoose.Schema;


var JobSchema=new Schema({
  field:{type:Schema.Types.ObjectId, ref:'Category'},
  type: String,
  title:String,
  company:String,
  address:String,
  startDate:String,
  endDate:String,
  email:String,
  skills:String,
  description:String
});

JobSchema.plugin(mongoosastic,{
  hosts:[
    'localhost:9200'
  ]
});

module.exports=mongoose.model('Job',JobSchema);
