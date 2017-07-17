package accounts;

import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 17.7.17.
 */
public class AccountService {
    private final Map<String, UsersDataSet> loginToProfile;
    private final Map<String, UsersDataSet> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public UsersDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UsersDataSet userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
