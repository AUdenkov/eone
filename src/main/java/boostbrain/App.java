package boostbrain;


import controler.Menu;
import model.Profile;
import model.TypeProfil;
import sorces.Connect;
import sorces.GetProfile;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
//        Menu menu = new Menu();
//        Profile profile=new Profile("Veronika","Udenkova","VY",25, TypeProfil.USER,"asdasn");
//       Connect.addProfileInDataBase(profile);
//        Connect.searchProfile("vy","123");
        System.out.println(Connect.checkUser());
//        Connect.getSpisok();
    }
}
