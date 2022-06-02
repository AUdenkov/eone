package sorces;

import model.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
            statement.executeUpdate();
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

    public static void searchProfile(String nick, String password) {
        Connection connection = null;
        try {
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            statement.execute("SELECT FROM users.\"user\" WHERE nick='asdasd' ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
