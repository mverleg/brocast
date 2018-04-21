import * as functions from 'firebase-functions';
import * as admin from "firebase-admin";
import { managerPath } from './ManagerPath';
const db = admin.database();

exports.firstLogin = functions.auth.user().onCreate((user) => { 

   const dbPath = managerPath.getPathToUser(user.uid);
asdasd
})