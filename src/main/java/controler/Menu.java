package controler;

import error.MyException;
import model.Profile;
import model.TypeProfil;
import sorces.Connect;
import sorces.Result;

import java.util.Scanner;

public class Menu {

    private static Scanner scannerIn;

    public Menu() {
        scannerIn = new Scanner(System.in);
        menuOne();
    }

    public static void menuOne() {
        System.out.println("1) Войти");
        System.out.println("2) Регистрация");
        int num = Integer.parseInt(scannerIn.next());
        switch (num) {
            case 1 -> {
                menuIn();
            }
            case 2 -> {
                if (Connect.checkUser())
                    registration(TypeProfil.USER);
                else {
                    registration(TypeProfil.ADMIN);
                }
            }
            case 3 -> {
                System.out.println(Connect.getSpisok());
                menuOne();
            }
        }
    }


    public static void menuIn() {
        System.out.println("Введите ник");
        String nick = scannerIn.next();
        check(nick);
        System.out.println("Введите пороль");
        String password = scannerIn.next();
        check(password);
        if (CheackProfile.cheackProfili(nick, password)) {
            Result result = new Result(CheackProfile.getProfile());
        }
    }


    public static void registration(TypeProfil typeProfil) {
        System.out.println("Введите имя");
        String firstName = scannerIn.next();
        check(firstName);
        System.out.println("Введите Фамилию");
        String lastName = scannerIn.next();
        check(lastName);
        System.out.println("Введите Под каким ником будите заходить");
        String nick = scannerIn.next();
        check(nick);
        System.out.println("Введите количество лет");
        int age = 0;
        try {
            age = Integer.parseInt(scannerIn.next());
        } catch (NumberFormatException e) {
            System.err.println("error age");
        }
        System.out.println("Введите пороль");
        String password = scannerIn.next();
        check(password);
        Profile profile = new Profile(firstName, lastName, nick, age, typeProfil, password);
        Connect.addProfileInDataBase(profile);
        menuOne();

    }

    public static void menuAdmin() {
        System.out.println("1) Получить список");
        System.out.println("2) удалить пользователя");
        System.out.println("3) изменить права полтзователя");
        int x = Integer.parseInt(scannerIn.next());
        switch (x) {
            case 1 -> {
                Connect.getSpisok();
                menuAdmin();
            }
            case 2 -> {
//                Connect.deleteProfele();
                menuAdmin();
            }
            case 3 -> {
//                Connect.changeTypeProfile();
                menuAdmin();
            }
        }
    }

    public static void menuUser() {
        System.out.println("удалить пользователя");

    }


    private static void check(String string) {
        if (string.length() < 2) {
            System.out.println("S");
            try {
                throw new MyException("No correct");
            } catch (MyException e) {
                System.err.println(e.getMessage());
            }
        }

    }
}
