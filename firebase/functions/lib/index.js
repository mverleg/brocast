"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const functions = require("firebase-functions");
const admin = require("firebase-admin");
const ManagerPath_1 = require("./Managers/ManagerPath");
const ManagerDataExtractor_1 = require("./Managers/ManagerDataExtractor");
admin.initializeApp();
const db = admin.firestore();
exports.fillDatabaseWithFakeUsers = functions.firestore.document(`${ManagerPath_1.managerPath.getPathToServerFunctionsFakeUsers()}`).onCreate((snap, context) => {
    var promises = [];
    const dbPathToUserCollection = ManagerPath_1.managerPath.getPathToUsersCollection();
    for (var i = 0; i < 100; i++) {
        const name = Test.generateRandomString(25);
        promises.push(db.collection(dbPathToUserCollection).doc().set({
            "tsCreated": Date.now(),
            "displayName": name
        }));
    }
    return Promise.all(promises);
});
exports.firstLogin = functions.auth.user().onCreate((user) => {
    const dbPath = ManagerPath_1.managerPath.getPathToUserDocument(user.uid);
    const userObject = ManagerDataExtractor_1.managerDataExtractor.getDataFromFirstLogin(user);
    return db.doc(dbPath).set(userObject);
});
exports.createNewChat = functions.firestore.document(`${ManagerPath_1.managerPath.getPathToCreateNewChat}/{documentId}`).onCreate((snap, context) => {
    var promises = [];
    const pathToThisId = ManagerPath_1.managerPath.getPathToCreateNewChat() + snap.id;
    const pathToChat = ManagerPath_1.managerPath.getPathToChatDocument(snap.id);
    const chatObject = ManagerDataExtractor_1.managerDataExtractor.getDataFromNewChat(snap);
    const pathToChatUsers = ManagerPath_1.managerPath.getPathToChatUsersCollection(snap.id);
    const usersUidsInGroup = ManagerDataExtractor_1.managerDataExtractor.getUserInGroupDataFromNewChat(snap);
    promises.push(db.doc(pathToChat).set(chatObject));
    promises.push(db.doc(pathToThisId).delete());
    for (const userUid in usersUidsInGroup) {
        const userObject = ManagerDataExtractor_1.managerDataExtractor.getDataFromUserForAddingToNewChat(userUid);
        const pathToChatUser = ManagerPath_1.managerPath.getPathToChatUserDocument(snap.id, userUid);
        promises.push(db.doc(pathToChatUser).set(userObject));
    }
    return Promise.all(promises);
});
exports.getUsersForTelephoneNumbers = functions.firestore.document(`${ManagerPath_1.managerPath.getPathToRetrievePhoneNumbers}/{documentId`).onCreate((snap, context) => {
    console.log("...");
});
//# sourceMappingURL=index.js.map