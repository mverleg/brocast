"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const functions = require("firebase-functions");
const admin = require("firebase-admin");
const ManagerPath_1 = require("./Managers/ManagerPath");
const ManagerDataExtractor_1 = require("./Managers/ManagerDataExtractor");
const Test_1 = require("./Test/Test");
admin.initializeApp();
const db = admin.firestore();
exports.firstLogin = functions.auth.user().onCreate((user) => {
    const dbPath = ManagerPath_1.managerPath.getPathToUserDocument(user.uid);
    const userObject = ManagerDataExtractor_1.managerDataExtractor.getDataFromFirstLogin(user);
    return db.doc(dbPath).set(userObject);
});
exports.createNewChat = functions.firestore.document(`${ManagerPath_1.managerPath.getPathToCreateNewChat()}/{documentId}`).onCreate((snap, context) => {
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
exports.createNewChatTest = functions.firestore.document(`${ManagerPath_1.managerPath.getPathToServerFunctions()}/${ManagerPath_1.managerPath.getPathToServerFunctions()}`).onCreate((snap, context) => {
    const name = Test_1.test.generateRandomString(25);
    const fakeUsers = [];
});
exports.fillDatabaseWithFakeUsers = functions.firestore.document(`${ManagerPath_1.managerPath.getPathToServerFunctions()}/${ManagerPath_1.managerPath.getPathFakeUsers()}`).onCreate((snap, context) => {
    var promises = [];
    const ownId = snap.id;
    const dbPathToUserCollection = ManagerPath_1.managerPath.getPathToUsersCollection();
    promises.push(db.collection(ManagerPath_1.managerPath.getPathToServerFunctions()).doc(ownId).delete());
    for (var i = 0; i < 20; i++) {
        const user = Test_1.test.generateFakeUser();
        promises.push(db.collection(dbPathToUserCollection).doc().set(user));
    }
    return Promise.all(promises);
});
//# sourceMappingURL=index.js.map