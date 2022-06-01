package boostbrain;

import controler.Menu;
import sorces.ConnectionManager;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    private static java.sql.Connection Connection;

    public static void main(String[] args) {
//        Menu menu=new Menu();
        String password = "admin";
        String userName = "postgres";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Class<Driver> driverClass = Driver.class;
        try (  var connection = DriverManager.getConnection(url,userName,password)) {
            System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
