package sorces;

import controler.Menu;
import model.Profile;
import model.TypeProfil;

public class Result {
    private Profile profile;

    public Result(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
