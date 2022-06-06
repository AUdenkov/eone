package sorces;

import model.Profile;
import model.TypeProfil;

import java.sql.*;

public class GetProfile {


    public Profile getProfile() {
        Connection connection = null;
        Profile profile = null;
        try {
            String sql = "SELECT * FROM users.user WHERE firstname = 'sadasd'";
            connection = ConnectionManager.connectOLD();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String nick = resultSet.getString("nick");
                int age = resultSet.getInt("age");
                String typeProfil = resultSet.getString("typeprofile");
                String password = resultSet.getString("password");
                if (typeProfil.equals("ADMIN")) {
                    profile = new Profile(firstName, lastName, nick, age, TypeProfil.ADMIN, password);
                } else
                    profile = new Profile(firstName, lastName, nick, age, TypeProfil.USER, password);
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
        return profile;
    }
}
/*try {
        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();

        // выбираем данные с БД
        ResultSet rs = statement.executeQuery(selectTableSQL);

        // И если что то было получено то цикл while сработает
        while (rs.next()) {
        String userid = rs.getString("USER_ID");
        String username = rs.getString("USERNAME");

        System.out.println("userid : " + userid);
        System.out.println("username : " + username);
        }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
       */
