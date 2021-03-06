import { managerPathSeperator } from './ManagerPathSeperator';

class ManagerPath { 

    private users: string = "users"
    private chats: string = "chats"
    private createNewChat: string = "createNewChat"
    private retrievePhoneNumbers: string = "retrievePhoneNumbers"
    private serverFunctions: string = "server"
    private serverFunctionsAddFakeUsers: string = "fakeUsers"
    private serverFunctionsAddFakeGroups: string = "fakeGroups"

    getPathToServerFunctions(): string { 
        return this.serverFunctions;
    }

    getPathFakeUsers(): string { 
        return this.serverFunctionsAddFakeUsers;
    }

    getPathFakeGroups(): string { 
        return this.serverFunctionsAddFakeGroups;
    }

    getPathToUsersCollection(): string { 
        return this.users;
    }

    getPathToCreateNewChat(): string { 
        return this.createNewChat;
    }

    getPathToUserDocument(uid: string): string {
        const paths: string[] = [this.users, uid]
        console.log("paths: " + paths)
        const test = managerPathSeperator.addSlashBetweenPaths(paths);
        console.log("test" + test)
        return test; 
    }

    getPathToChatDocument(uid: string): string { 
        const paths: string[] = [this.chats, uid]
        return managerPathSeperator.addSlashBetweenPaths(paths);
    }

    getPathToChatUsersCollection(uid: string): string { 
        const paths: string[] = [this.chats, uid, this.users];
        return managerPathSeperator.addSlashBetweenPaths(paths);
    }
    
    getPathToChatUserDocument(uidChat: string, uidUser: string): string {
        const paths: string[] = [this.chats, uidChat, this.users, uidUser]
        return managerPathSeperator.addSlashBetweenPaths(paths)
    }
    
    getPathToRetrievePhoneNumbers(): string { 
        return this.retrievePhoneNumbers;
    }

}

export const managerPath = new ManagerPath();