class ManagerPathSeperator { 

    addSlashBetweenPaths(paths: string[]): string { 
        var finalPath = ""

        for (const path in paths) { 
            finalPath += path + "/"
        }

        return finalPath
    }

    removeLastSlash(path: string): string { 
        const lastCharacter = path.substr(path.length - 1);

        var finalPath = path;
        if (lastCharacter == "/") { 
            finalPath = path.substring(0, path.length - 1); 
        }

        return finalPath;
    }
    
}

export const managerPathSeperator = new ManagerPathSeperator();