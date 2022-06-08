package sorces;

import model.Profile;
import model.TypeProfil;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Connect {

    private Connect() {
    }

    public static boolean isEmpty() {
        Connection connection = null;
        boolean cheack;
        try {
            String sql = "SELECT * FROM users.user ";
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                cheack = false;
            } else {
                cheack = true;
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
                System.out.println("Пользователь создан");
            } catch (PSQLException e) {
                System.out.println("Пользователь с таким именем существует");
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


    public static Profile searchProfile(String nick) {
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
                if (typeProfil.equals("ADMIN")) {
                    profile = new Profile(firstName, lastName, nick, age, TypeProfil.ADMIN, passwordDB);
                } else
                    profile = new Profile(firstName, lastName, nick, age, TypeProfil.USER, passwordDB);

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
        if (profile == null) {
            System.out.println("Данные не верны");
        }
        return profile;
    }

    public static int checkUser() {
        Connection connection = null;
        int result = 0;
        try {
            String sql = "SELECT * FROM users.user WHERE typeprofile = 'ADMIN'";
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                result++;
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
        return result;
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

    public static void deleteProfile(String nick) {
        Connection connection = null;
        try {
            String sql = String.format("DELETE FROM users.user WHERE nick = '%s'", nick);
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            if (result == 1) {
                System.out.println("you delete " + nick);
            } else System.out.println("No Profile");
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

    public static void editTypeProfile(String nick, TypeProfil typeProfil) {
        Connection connection = null;
        try {
            String sqlCheck = String.format("SELECT  * FROM users.user WHERE nick='%s'", nick);
            String sql = String.format("UPDATE users.user SET typeprofile = '%s'  WHERE nick='%s'", typeProfil, nick);
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCheck);
            if (resultSet.next()) {
                statement.executeUpdate(sql);
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

    }


    public static int countAdmin() {
        Connection connection = null;
        int result = 0;
        try {
            String sql = "SELECT COUNT(1) FROM users.user WHERE typeprofile = 'ADMIN'";
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

}
