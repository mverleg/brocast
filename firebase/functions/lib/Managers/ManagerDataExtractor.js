"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class ManagerDataExtractor {
    addTimestamp(to) {
        to["tsCreated"] = Date.now();
        return to;
    }
    getDataFromFirstLogin(user) {
        const uid = user.uid;
        const phoneNumber = user.phoneNumber;
        const object = { "uid": uid,
            "phoneNumber": phoneNumber };
        return this.addTimestamp(object);
    }
    getDataFromUserForAddingToNewChat(uid) {
        return this.addTimestamp({ "uid": uid });
    }
    getDataFromNewChat(snap) {
        const data = snap.data();
        const chatName = data.name;
        const chatCreator = data.creator;
        const object = { "name": chatName, "creator": chatCreator };
        return this.addTimestamp(object);
    }
    getUserInGroupDataFromNewChat(snap) {
        const data = snap.data();
        const usersInGroup = data.usersInGroup;
        return usersInGroup;
    }
}
exports.managerDataExtractor = new ManagerDataExtractor();
//# sourceMappingURL=ManagerDataExtractor.js.map