"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class ManagerPathSeperator {
    addSlashBetweenPaths(paths) {
        var finalPath = "";
        for (const path in paths) {
            finalPath += paths[path] + "/";
        }
        return finalPath;
    }
    removeLastSlash(path) {
        const lastCharacter = path.substr(path.length - 1);
        var finalPath = path;
        if (lastCharacter == "/") {
            finalPath = path.substring(0, path.length - 1);
        }
        return finalPath;
    }
}
exports.managerPathSeperator = new ManagerPathSeperator();
//# sourceMappingURL=ManagerPathSeperator.js.map