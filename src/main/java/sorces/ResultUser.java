package sorces;

import model.Profile;

public class ResultUser extends Result {
    public ResultUser(Profile profile) {
        super(profile);
        goUser();
    }

    public void goUser() {
        System.out.println("Добрый день " + getProfile().getNick());
        if (!ManagerProfile.menuUserResult()) {
            setProfile(null);
            ManagerProfile.menuOneResult();
        } else {
            goUser();
        }
    }
}
