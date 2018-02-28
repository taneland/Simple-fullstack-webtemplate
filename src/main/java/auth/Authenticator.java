package auth;

import database.Database;
import database.DatabaseException;
import database.DatabaseHandler;
import role.Admin;
import role.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static security.PasswordSecurity.validatePassword;

public class Authenticator {

    public static boolean authenticateAdmin(String email, String password) {
        Admin existingAdmin;
        try {
            Database db = DatabaseHandler.getDatabase();
            existingAdmin = db.getSelector().getAdmin();
        } catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
        if (!existingAdmin.getEmail().equals(email)) {
            return false;
        }

        try {
            return validatePassword(password.toCharArray(), existingAdmin.getHashedPassword());
        } catch (InvalidKeySpecException |NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }




}
