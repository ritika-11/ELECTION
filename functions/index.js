'use strict' ;

const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

exports.sendNotification = functions.database.ref('/Dates/Election date')
.onWrite(event => {
    
    const topic = "Dates" ;
    const payload = {
        notification:{
	    title: 'Important',
            body: 'Election dates declared'
        }
    };


    return admin.messaging().sendToTopic(topic, payload)
});

