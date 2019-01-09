// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions

'use strict' ;

const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

//Now we're going to create a function that listens to when a 'Notifications' node changes and send a notificcation
//to all devices subscribed to a topic

exports.sendNotification = functions.database.ref('/Dates')
.onWrite(event => {
    //This will be the notification model that we push to firebase
    
    const topic = "Dates" ;
    const payload = {
        notification:{
//            username: request.username,
//            imageUrl: request.imageUrl,
//            email: request.email,
//            uid: request.uid,
	    title: 'Important',
            body: 'Election dates declared'
        }
    };

    //The topic variable can be anything from a username, to a uid
    //I find this approach much better than using the refresh token
    //as you can subscribe to someone's phone number, username, or some other unique identifier
    //to communicate between

    //Now let's move onto the code, but before that, let's push this to firebase

    return admin.messaging().sendToTopic(topic, payload)
//    .then(function(response){
//        console.log("Successfully sent message: ", response);
//    })
//    .catch(function(error){
//        console.log("Error sending message: ", error);
//    })
});
//And this is it for building notifications to multiple devices from or to one.
