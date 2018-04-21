import * as functions from 'firebase-functions';
import * as admin from "firebase-admin";
import { managerPath } from './Managers/ManagerPath';
import { managerDataExtractor } from './Managers/ManagerDataExtractor';

admin.initializeApp();
const db = admin.firestore();

exports.firstLogin = functions.auth.user().onCreate((user) => { 

   const dbPath = managerPath.getPathToUserDocument(user.uid);
   const userObject = managerDataExtractor.getDataFromFirstLogin(user);

   return db.doc(dbPath).set(userObject)
})

exports.createNewChat = functions.firestore.document(`${managerPath.getPathToCreateNewChat}/{documentId}`).onCreate((snap, context) => { 

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