package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.Groomer;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get Groomer data from the database
 */
public class GroomerAccess extends Groomer {

    public GroomerAccess(int groomerID, String groomerName, String groomerPassword, boolean groomerStatus) {
        super(groomerID, groomerName, groomerPassword, groomerStatus);
    }

    /**
     * Verify that the user's login credetials are correct
     *
     * @param username
     * @param password
     * @return groomer_id || -1
     */
    public static int validateUser(String username, String password) {
        try {
            String sql = "SELECT * FROM groomer WHERE groomer_name = '" + username + "' AND groomer_password = '" + password + "'";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            rs.next();
            if (rs.getString("groomer_name").equals(username)) {
                if (rs.getString("groomer_password").equals(password)) {
                    return rs.getInt("groomer_id");
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return -1;
    }

    /**
     * Get username for Log
     *
     * @return groomer_name || null
     */
    public static String getUser() {
        try {
            String sql = "SELECT groomer_name FROM groomer";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("groomer_name");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all groomers from the database
     *
     * @return usersObservableList
     * @throws SQLException
     * @see SQLException
     */
    public static ObservableList<GroomerAccess> getGroomers() throws SQLException {
        ObservableList<GroomerAccess> usersObservableList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM groomer";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int groomerID = rs.getInt("groomer_id");
            String groomerName = rs.getString("groomer_name");
            String groomerPassword = rs.getString("groomer_password");
            boolean groomerStatus = rs.getBoolean("groomer_status");

            GroomerAccess groomer = new GroomerAccess(groomerID, groomerName, groomerPassword, groomerStatus);
            usersObservableList.add(groomer);
        }
        return usersObservableList;
    }
}