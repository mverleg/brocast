"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const ManagerPathSeperator_1 = require("./ManagerPathSeperator");
class ManagerPath {
    constructor() {
        this.users = "users";
        this.chats = "chats";
        this.createNewChat = "createNewChat";
        this.retrievePhoneNumbers = "retrievePhoneNumbers";
        this.serverFunctions = "server";
        this.serverFunctionsAddFakeUsers = "fakeUsers";
    }
    getPathToServerFunctions() {
        return this.serverFunctions;
    }
    getPathFakeUsers() {
        return this.serverFunctionsAddFakeUsers;
    }
    getPathToUsersCollection() {
        return this.users;
    }
    getPathToCreateNewChat() {
        return this.createNewChat;
    }
    getPathToUserDocument(uid) {
        const paths = [this.users, uid];
        return ManagerPathSeperator_1.managerPathSeperator.addSlashBetweenPaths(paths);
    }
    getPathToChatDocument(uid) {
        const paths = [this.chats, uid];
        return ManagerPathSeperator_1.managerPathSeperator.addSlashBetweenPaths(paths);
    }
    getPathToChatUsersCollection(uid) {
        const paths = [this.chats, uid, this.users];
        return ManagerPathSeperator_1.managerPathSeperator.addSlashBetweenPaths(paths);
    }
    getPathToChatUserDocument(uidChat, uidUser) {
        const paths = [this.chats, uidChat, this.users, uidUser];
        return ManagerPathSeperator_1.managerPathSeperator.addSlashBetweenPaths(paths);
    }
    getPathToRetrievePhoneNumbers() {
        return this.retrievePhoneNumbers;
    }
}
exports.managerPath = new ManagerPath();
//# sourceMappingURL=ManagerPath.js.map