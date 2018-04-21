import { managerPathSeperator } from './ManagerPathSeperator';

class ManagerPath { 

    private users: string = "users"
    private chats: string = "chats"
    private createNewChat: string = "createNewChat"

    getPathToCreateNewChat(): string { 
        return this.createNewChat;
    }

    getPathToUserDocument(uid: string): string {
        const paths: string[] = [this.users, uid]
        return managerPathSeperator.addSlashBetweenPaths(paths)
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

}

export const managerPath = new ManagerPath();