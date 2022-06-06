package controler;

import model.Profile;
import sorces.Connect;

public class CheackProfile {
    private static Profile profile;

    public static boolean cheackProfili(String nick, String password) {
        profile = Connect.searchProfile(nick, password);
        if (profile == null) {
            return false;

        } else return true;
    }

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        CheackProfile.profile = profile;
    }
}
