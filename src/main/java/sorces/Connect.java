package sorces;

import model.Profile;
import model.TypeProfil;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

public class Connect {

    private Connect() {
    }

    public static void addProfileInDataBase(Profile profile) {
        Connection connection = null;
        try {
            String sql = "INSERT INTO users.user (firstName,lastName,nick,age,typeProfile,password) VALUES (?,?,?,?,?,?)";
            connection = ConnectionManager.connectOLD();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setString(3, profile.getNick());
            statement.setInt(4, profile.getAge());
            statement.setString(5, String.valueOf(profile.getTypeProfil()));
            statement.setString(6, profile.getPassword());
            try {
                statement.executeUpdate();
            } catch (PSQLException e) {
                System.out.println("Пользователь с таким именем существует");
            }
            System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Profile searchProfile(String nick, String password) {
        Connection connection = null;
        Profile profile = null;
        try {
            String sql = String.format("SELECT * FROM users.user WHERE nick = '%s'", nick);
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                String typeProfil = resultSet.getString("typeprofile");
                String passwordDB = resultSet.getString("password");
                if (password.equals(passwordDB)) {
                    if (typeProfil.equals("ADMIN")) {
                        profile = new Profile(firstName, lastName, nick, age, TypeProfil.ADMIN, password);
                    } else
                        profile = new Profile(firstName, lastName, nick, age, TypeProfil.USER, password);
                }
            }
            System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (profile == null) {
            System.out.println("Данные не верны");
        }
        return profile;
    }

    public static boolean checkUser() {
        Connection connection = null;
        boolean cheack;
        try {
            String sql = "SELECT * FROM users.user WHERE typeprofile = 'ADMIN'";
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                cheack = true;
            } else {
                cheack = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cheack;
    }

    public static ArrayList<String> getSpisok() {
        ArrayList<String> arrayList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "SELECT * FROM users.user";
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String nick = resultSet.getString("nick");
                int age = resultSet.getInt("age");
                String typeProfil = resultSet.getString("typeprofile");
                String person = firstName + " " + lastName + " " + nick + " " + age + " " + typeProfil;
                arrayList.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return arrayList;
    }
}
