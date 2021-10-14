package letscode.accounts;

import letscode.DataBase.DBService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private static final String homeDirectory = "D:\\test\\users\\";
    private static final DBService _dbService;
    private static final Map<String, UserProfile> loginToProfile ;
    private static final Map<String, UserProfile> sessionIdToProfile ;

    static{
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        _dbService = new DBService();
        initializeDBService();
    }

    private static void initializeDBService() {
        try{
            ResultSet rs = _dbService.getStatement().executeQuery("select * from users");
            while (rs.next()){
                loginToProfile.put(
                        rs.getString(1),
                        new UserProfile(rs.getString(1),rs.getString(2),rs.getString(3))
                );
                String login = rs.getString(1);
                System.out.println("Login: " + login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createNewUser(String login, String email, String pass){
        try{
            String sql = String.format("insert into users (login, email, password) values ('%s', '%s', '%s');", login, email, pass);
            _dbService.getStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getHomeDirectory(){ return homeDirectory; }

    public static void addNewUser(UserProfile userProfile) {
        createNewUser(userProfile.getLogin(), userProfile.getEmail(), userProfile.getPass());
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
