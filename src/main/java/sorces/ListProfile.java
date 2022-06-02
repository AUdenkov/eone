package sorces;

import model.Profile;

import java.util.ArrayList;

public class ListProfile {
    private ArrayList<Profile> profiles;

    public ListProfile() {
        profiles=new ArrayList<>();
    }

    public void addProfile(Profile profile){
        profiles.add(profile);
    }

    public void removeProfile(Profile profile){
        profiles.remove(profile);
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }
}
