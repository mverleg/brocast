import * as functions from 'firebase-functions';
import * as admin from "firebase-admin";
import { managerPath } from './Managers/ManagerPath';
import { managerDataExtractor } from './Managers/ManagerDataExtractor';
import { test } from './Test/Test'

admin.initializeApp();
const db = admin.firestore();

exports.fillDatabaseWithFakeUsers = functions.firestore.document(`${managerPath.getPathToServerFunctions()}/${managerPath.getPathFakeUsers()}`).onCreate((snap, context) => { 

    var promises = [];
    const ownId = snap.id;
    const dbPathToUserCollection = managerPath.getPathToUsersCollection();

    promises.push(db.collection(managerPath.getPathToServerFunctions()).doc(ownId).delete())

    for (var i = 0; i < 20; i++) { 
        const user = test.generateFakeUser();
        promises.push(db.collection(dbPathToUserCollection).doc().set(user))
    }

    return Promise.all(promises);
})

exports.firstLogin = functions.auth.user().onCreate((user) => { 

   const dbPath = managerPath.getPathToUserDocument(user.uid);
   const userObject = managerDataExtractor.getDataFromFirstLogin(user);

   return db.doc(dbPath).set(userObject)
})

exports.createNewChat = functions.firestore.document(`${managerPath.getPathToCreateNewChat()}/{documentId}`).onCreate((snap, context) => { 

    var promises = [];
    const pathToThisId = managerPath.getPathToCreateNewChat() + snap.id;
    const pathToChat = managerPath.getPathToChatDocument(snap.id);
    const chatObject = managerDataExtractor.getDataFromNewChat(snap);
    const pathToChatUsers = managerPath.getPathToChatUsersCollection(snap.id);
    const usersUidsInGroup = managerDataExtractor.getUserInGroupDataFromNewChat(snap);

    promises.push(db.doc(pathToChat).set(chatObject));
    promises.push(db.doc(pathToThisId).delete());

    for (const userUid in usersUidsInGroup) { 
        
        const userObject = managerDataExtractor.getDataFromUserForAddingToNewChat(userUid);
        const pathToChatUser = managerPath.getPathToChatUserDocument(snap.id, userUid)

        promises.push(db.doc(pathToChatUser).set(userObject));
    }

    return Promise.all(promises);
})

exports.createNewChatTest = functions.firestore.document(`${managerPath.getPathToServerFunctions()}/${managerPath.getPathToServerFunctions()}`).onCreate((snap, context) => { 

    const name = test.generateRandomString(25);
    const fakeUsers = [];

    for (var i = 0; i < 10; i++) { 

    }

})