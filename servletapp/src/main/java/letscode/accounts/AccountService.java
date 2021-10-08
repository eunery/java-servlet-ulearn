package letscode.accounts;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private static final Path homeDirectory = Paths.get("D:\\test\\users\\");

    private static final Map<String, UserProfile> loginToProfile ;
    private static final Map<String, UserProfile> sessionIdToProfile ;

    static{
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();

        loginToProfile.put("admin", new UserProfile("admin"));
    }

    public static Path getHomeDirectory(){ return homeDirectory; }

    public static void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public static UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public static UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public static void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
