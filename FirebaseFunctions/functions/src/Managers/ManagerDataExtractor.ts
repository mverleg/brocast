import * as admin from "firebase-admin";
import * as functions from 'firebase-functions';

class ManagerDataExtractor { 

    private addTimestamp(to: Object): Object { 
        to["tsCreated"] = Date.now();
        return to;
    }

    getDataFromFirstLogin(user: admin.auth.UserRecord): Object { 
        const uid = user.uid;
        const phoneNumber = user.phoneNumber
        const object = { "uid": uid, 
                 "phoneNumber": phoneNumber };
        return this.addTimestamp(object);
    }

    getDataFromUserForAddingToNewChat(uid: string): Object { 
        return this.addTimestamp({"uid" : uid})
    }

    getDataFromNewChat(snap: FirebaseFirestore.DocumentData): Object { 
        const data = snap.data();
        const chatName = data.name;
        const chatCreator = data.creator;
        const object = { "name": chatName, "creator": chatCreator };
        return this.addTimestamp(object);
    }

    getUserInGroupDataFromNewChat(snap: FirebaseFirestore.DocumentData): string[] { 
        const data = snap.data();
        const usersInGroup: string[] = data.usersInGroup;
        return usersInGroup;
    }

}

export const managerDataExtractor = new ManagerDataExtractor()