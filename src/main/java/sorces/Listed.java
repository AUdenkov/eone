package sorces;

import model.Profile;

import java.util.ArrayList;

public interface Listed {
    default void write(ArrayList<Profile> profiles) {
        for (int i = 0; i < profiles.size(); i++) {
            System.out.println(profiles.get(i));
        }
    }
}
