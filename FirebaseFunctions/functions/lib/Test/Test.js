"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Test {
    generateRandomString(withLenght) {
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (var i = 0; i < withLenght; i++)
            text += possible.charAt(Math.floor(Math.random() * possible.length));
        return text;
    }
}
exports.test = new Test();
//# sourceMappingURL=Test.js.map