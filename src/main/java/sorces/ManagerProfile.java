package sorces;

import controler.CheackProfile;
import controler.Menu;
import model.Profile;
import model.TypeProfil;

import java.util.Scanner;

public class ManagerProfile {
    private static Profile profile;
    private static Scanner scannerIn;

    public ManagerProfile() {
        scannerIn = new Scanner(System.in);

    }

    public static boolean menuAdminResult() {
        switch (Menu.menuAdmin()) {
            case 1 -> {
                Connect.getSpisok().stream().forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Введите ник");
                String nick = scannerIn.next();
                if (profile.getTypeProfil().equals(TypeProfil.ADMIN) && Connect.countAdmin() == 1) {
                    System.out.println("нельзя удаляьб последнего админа");
                } else {
                    Connect.deleteProfile(nick);
                    if (profile.getNick().equals(nick)) {
                        System.out.println("Вы удалили себя");
                        profile = null;
                        return false;
                    }
                }
            }
            case 3 -> {
                System.out.println("Введите ник");
                String nick = scannerIn.next();
                Profile profile1=Connect.searchProfile(nick);
                if (profile1.getTypeProfil().equals(TypeProfil.ADMIN) && Connect.countAdmin() == 1) {
                    System.out.println("нельзя менять уровень доступа у последнего админа");
                } else {
                    Connect.editTypeProfile(nick, answerTypeProfileResult(nick));
                    if (profile.getNick().equals(nick)){
                        profile=null;
                        return false;
                    }
                }
            }
            case 4 -> {
                profile = null;
                return false;
            }
        }
        return true;
    }

    public static boolean menuUserResult() {
        switch (Menu.menuUser()) {
            case 1 -> {
                Connect.deleteProfile(profile.getNick());
                System.out.println("Вы удалили себя");
                profile = null;
                return false;
            }
            case 2 -> {
                profile = null;
                return false;
            }
        }
        return true;
    }

    public static void menuOneResult() {
        switch (Menu.menuOne()) {
            case 1 -> {
                if (!isEmpty()) {
                    menuInResult();
                } else {
                    System.out.println("У вас нету пользователей");
                    menuOneResult();
                }
            }
            case 2 -> {
                if (Connect.checkUser() != 0)
                    registrationResult(TypeProfil.USER);
                else {
                    System.out.println("Вы создаёте 1 админа");
                    registrationResult(TypeProfil.ADMIN);
                }
            }
            default -> Menu.menuOne();
        }
    }

    public static boolean isEmpty() {
        return Connect.isEmpty();
    }

    public static void menuInResult() {
        String[] nickAndPassword = Menu.menuIn();
        if (CheackProfile.cheackProfili(nickAndPassword[0], nickAndPassword[1])) {
            profile = CheackProfile.getProfile();
            if (profile.getTypeProfil() == TypeProfil.ADMIN) {
                ResultAdmin resultAdmin = new ResultAdmin(profile);
            } else {
                ResultUser resultUser = new ResultUser(profile);
            }
        } else menuOneResult();
    }

    private static TypeProfil answerTypeProfileResult(String nick) {
        TypeProfil typeProfil = null;
        switch (Menu.answerTypeProfile(nick)) {
            case 1 -> typeProfil = TypeProfil.ADMIN;
            case 2 -> typeProfil = TypeProfil.USER;
        }
        return typeProfil;
    }

    public static void registrationResult(TypeProfil typeProfil) {
        Object[] objects = Menu.registration(typeProfil);
        Profile profile = new Profile((String) objects[0], (String) objects[1], (String) objects[2], (Integer) objects[3], (TypeProfil) objects[4], (String) objects[5]);
        Connect.addProfileInDataBase(profile);
        menuOneResult();
    }

}
