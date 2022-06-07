package sorces;

import controler.Menu;
import model.Profile;
import model.TypeProfil;

public class Result {
    private Profile profile;

    public Result(Profile profile) {
        this.profile = profile;
        if (profile.getTypeProfil() == TypeProfil.ADMIN) {
            goAdmin();
        } else goUser();
    }

    public void goAdmin() {
        if (!Menu.menuAdmin()) {
            profile = null;
            Menu.menuOne();
        } else {
            goAdmin();
        }
    }

    public void goUser() {
        System.out.println(profile);
        Menu.menuUser();
    }
}
