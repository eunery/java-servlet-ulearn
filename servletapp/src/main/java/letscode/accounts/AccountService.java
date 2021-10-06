package letscode.accounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile = new HashMap<>();
    private final Map<String, UserProfile> sessionIdToProfile = new HashMap<>();
    private List<UserProfile> users = new ArrayList<UserProfile>() {};

    public AccountService() {
        loginToProfile.put("admin",new UserProfile("admin", "admin", "1@1.ru"));
    }

    public AccountService(UserProfile userProfile){
        users.add(userProfile);
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
    public boolean checkUserExist(UserProfile userProfile){
        for (UserProfile item: users) {
            if (getUserByLogin(item.getLogin()).equals(userProfile))
                return true;
        }
        return false;
    }
}
