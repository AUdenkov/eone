package controler;

import error.MyException;
import model.Profile;
import model.TypeProfil;
import sorces.*;

import java.util.Scanner;

public class Menu {

    private static Scanner scannerIn;

    public Menu() {
        scannerIn = new Scanner(System.in);
        ManagerProfile managerProfile=new ManagerProfile();
        ManagerProfile.menuOneResult();
    }

    public static int menuOne() {
        System.out.println("1) Войти");
        System.out.println("2) Регистрация");
        return Integer.parseInt(scannerIn.next());
    }


    public static String[] menuIn() {
        System.out.println("Введите ник");
        String nick = scannerIn.next();
        check(nick);
        System.out.println("Введите пороль");
        String password = scannerIn.next();
        check(password);
        String[] nickAndPassword = {nick, password};
        return nickAndPassword;
    }


    public static Object[] registration(TypeProfil typeProfil) {
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
        Object [] dateProfile={firstName,lastName,nick,age,typeProfil,password};
        return dateProfile;
    }

    public static int menuAdmin() {
        System.out.println("1) Получить список");
        System.out.println("2) удалить пользователя");
        System.out.println("3) изменить права полтзователя");
        System.out.println("4) выйти");
        return Integer.parseInt(scannerIn.next());
    }

    public static int menuUser() {
        System.out.println("1) удалить пользователя");
        System.out.println("2) выйти");
        return scannerIn.nextInt();
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

    public static int answerTypeProfile(String nick) {
        System.out.println("Какой вы уровень доступа хотите дать пользователю " + nick);
        System.out.println("1) ADMIN");
        System.out.println("2) USER");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }
}
