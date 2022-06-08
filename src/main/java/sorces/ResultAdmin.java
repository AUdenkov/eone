package sorces;

import controler.Menu;
import model.Profile;

public class ResultAdmin extends Result {

    public ResultAdmin(Profile profile) {
        super(profile);
        goAdmin();
    }

    public void goAdmin() {
        System.out.println("Добрый день " + getProfile().getNick());
        if (!ManagerProfile.menuAdminResult()) {
            setProfile(null);
            ManagerProfile.menuOneResult();
        } else {
            goAdmin();
        }
    }
}
