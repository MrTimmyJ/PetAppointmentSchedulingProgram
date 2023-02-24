package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.PetType;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get Pet Type data from the database
 */
public class PetTypeAccess extends PetType {

    public PetTypeAccess(int petTypeID, String petTypeDescription) {
        super(petTypeID, petTypeDescription);
    }

    /**
     * Get all pet type ID's from database
     * @throws SQLException
     * @see SQLException
     * @return petTypeObservableList
     */
    public static ObservableList<PetTypeAccess> getPetsTypeID() throws SQLException {
        ObservableList<PetTypeAccess> petTypeObservableList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM pettype";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int petTypeID = rs.getInt("pettype_id");
            String petTypeDescription = rs.getString("pettype_description");

            PetTypeAccess petType = new PetTypeAccess(petTypeID, petTypeDescription);
            petTypeObservableList.add(petType);
        }
        return petTypeObservableList;
    }
}