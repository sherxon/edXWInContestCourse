var mongo=require('mongoskin');
var express=require('express');
var crypto = require('crypto');
var algorithm = 'aes256';
var password = 'asaadsaad';
var app = express();
var db=mongo.db('mongodb://localhost:27017/local')


db.bind('homework7');

function decrypt(text){
    var decipher = crypto.createDecipher(algorithm,password)
    var dec = decipher.update(text,'hex','utf8')
    dec += decipher.final('utf8');
    return dec;
}

app.get('/', function(req, res, next){

    db.homework7.findOne({}, function(err, item){

        res.send(decrypt(item.message));
        db.close();
    })
})
app.listen(3000);
