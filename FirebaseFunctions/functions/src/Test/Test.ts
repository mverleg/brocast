class Test { 

    generateRandomString(withLenght: number): string {
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (var i = 0; i < withLenght; i++)
          text += possible.charAt(Math.floor(Math.random() * possible.length));
      
        return text;
      }

      generateFakeUser(): Object { 
        return {
          "tsCreated": Date.now(),
          "displayName": this.generateRandomString(25)
         }
      }

}

export const test = new Test();