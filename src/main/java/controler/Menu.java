package controler;

import error.MyException;
import model.Profile;
import model.TypeProfil;
import sorces.ListProfile;
import sorces.Result;

import java.util.Scanner;

public class Menu {
    private static ListProfile listProfile;
    private static Scanner scannerIn;

    public Menu() {
        listProfile = new ListProfile();
        scannerIn = new Scanner(System.in);
        menuOne();
    }

    public static void menuOne() {
        System.out.println("1) Войти");
        System.out.println("2) Регистрация");
        int num = Integer.parseInt(scannerIn.next());
        switch (num) {
            case 1 -> {
                if (listProfile.getProfiles().size() != 0) {
                    menuIn();
                } else System.out.println("Создайте пользователей");
                menuOne();
            }
            case 2 -> {
                if (listProfile.getProfiles().size() != 0) {
                    registration(TypeProfil.USER);
                } else
                    registration(TypeProfil.ADMIN);
            }
            case 3 -> {
                System.out.println(listProfile.getProfiles());
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
        for (int i = 0; i < listProfile.getProfiles().size(); i++) {
            if (listProfile.getProfiles().get(i).getNick().equals(nick)) {
                if (listProfile.getProfiles().get(i).getPassword().equals(password)) {
                    System.out.println("Все верно");
                    Result result = new Result(listProfile.getProfiles().get(i));
                } else {
                    System.out.println("password error");
                }
            } else {
                System.out.println("nick error");
            }
        }
        menuOne();
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
        listProfile.addProfile(new Profile(firstName, lastName, nick, age, typeProfil, password));
        menuOne();

    }

    public static void menuAdmin() {
        System.out.println("Полцчить список");
        System.out.println("удалить пользователя");
        System.out.println("изменить права полтзователя");
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
