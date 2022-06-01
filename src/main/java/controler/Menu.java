package controler;

import model.Profile;
import model.TypeProfil;

import java.util.Scanner;

public class Menu {
    private Scanner scannerIn;

    public Menu() {
        scannerIn = new Scanner(System.in);
        menuOne();
    }

    public void menuOne() {
        System.out.println("1) Войти");
        System.out.println("2) Регистрация");
        int num = Integer.parseInt(scannerIn.next());
        switch (num) {
            case 1 -> menuIn();

            case 2 -> registration();
        }
    }

    public void menuIn() {




    }

    public void registration() {
        System.out.println("Введите имя");
        String firstName=scannerIn.next();
        System.out.println("Введите Фамилию");
        String lastName=scannerIn.next();
        System.out.println("Введите Под каким ником будите заходить");
        String nick=scannerIn.next();
        System.out.println("Введите количество лет");
        int age= Integer.parseInt(scannerIn.next());
        System.out.println("Введите пороль");
        String password=scannerIn.next();
        Profile profile=new Profile(firstName,lastName,nick,age, TypeProfil.USER,password);
    }
}
