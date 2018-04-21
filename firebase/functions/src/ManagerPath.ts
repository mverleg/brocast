class ManagerPath { 

    private users: string = "users"

    addSlashBetweenPaths(paths: string[]): string { 
        var finalPath = ""

        for (const path in paths) { 
            finalPath += path + "/"
        }

        const lastCharacter = finalPath.substr(finalPath.length - 1);

        if (lastCharacter == "/") { 
            finalPath = finalPath.substring(0, finalPath.length - 1); 
        }

        return finalPath
    }

    getPathToUser(uid: string): string {
        const paths: string[] = [this.users, uid]
        return this.addSlashBetweenPaths(paths)
    }

}

export const managerPath = new ManagerPath();