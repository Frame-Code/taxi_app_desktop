package SHARED.UTILS;

import lombok.extern.apachecommons.CommonsLog;
import org.mindrot.jbcrypt.BCrypt;

@CommonsLog
public class PasswordUtils {
    private static final int DEFAULT_LOG_ROUNDS = 12;
    private static final int MIN_LOG_ROUNDS = 10;
    private static final int MAX_LOG_ROUNDS = 15;

    public static String hashPassword(String password) {
        return hashPassword(password, DEFAULT_LOG_ROUNDS);
    }

    public static String hashPassword(String password, int logRounds) {
        if (logRounds < MIN_LOG_ROUNDS || logRounds > MAX_LOG_ROUNDS) {
            throw new IllegalArgumentException("Log rounds will can be between " + MIN_LOG_ROUNDS + " and " + MAX_LOG_ROUNDS);
        }
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public static boolean verifyPassword(String password, String hash) {
        if (password == null || hash == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(password, hash);
        } catch (Exception e) {
            log.warn("Error verifying password: {}", e);
            return false;
        }
    }
}
