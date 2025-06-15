package util;

import dao.custom.ApplicationUserDao;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
    public static String encryptPassword(String passwordText) {
        return BCrypt.hashpw(passwordText, BCrypt.gensalt());
    }

    public static boolean decryptPassword(String hashedPassword, String passwordText) {
        return BCrypt.checkpw(passwordText, hashedPassword);
    }

}
