package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.Pet;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Get Pet data from the database
 */
public class PetAccess {
    /**
     * Get All Pet data from the database
     *
     * @throws SQLException
     * @see SQLException
     * @return customersObservableList
     */
    public static ObservableList<Pet> getPets(Connection connection) throws SQLException {
        ObservableList<Pet> petsObservableList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM pet";
        // join with pettype
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int petID = rs.getInt("pet_id");
            int petTypeID = rs.getInt("pettype_id");
            String petName = rs.getString("pet_name");
            String ownerName = rs.getString("owner_name");
            int petAddressID = rs.getInt("address_id");
            LocalDate petBirthdate = rs.getTimestamp("pet_birthdate").toLocalDateTime().toLocalDate();
            boolean petStatus = rs.getBoolean("pet_status");

            Pet pet = new Pet(petID, petTypeID, petName, ownerName, petAddressID, petBirthdate, petStatus);
            petsObservableList.add(pet);
        }
        return petsObservableList;
    }
}