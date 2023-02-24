package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.Address;
//import PetAppointments.dataLayout.City;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get Address data from the database
 */
public class AddressAccess extends Address {

    public AddressAccess(int addressID, String addressLineOne, String addressLineTwo, int cityID, String postalCode, String phone) {
        super(addressID, addressLineOne, addressLineTwo, cityID, postalCode, phone);
    }

    /**
     * Get all address's from database.
     *
     * @throws SQLException
     * @see SQLException
     * @return contactsObservableList
     */
    public static ObservableList<AddressAccess> getAllAddressID() throws SQLException {
        ObservableList<AddressAccess> contactsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from address";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int addressID = rs.getInt("address_id");
            String addressLineOne = rs.getString("addressline_1");
            String addressLineTwo = rs.getString("addressline_2");
            int cityID = rs.getInt("city_id");
            String postalCode = rs.getString("postal_code");
            String phone = rs.getString("phone");
            AddressAccess address = new AddressAccess(addressID, addressLineOne, addressLineTwo, cityID, postalCode, phone);
            contactsObservableList.add(address);
        }
        return contactsObservableList;
    }

    /**
     * Find Address ID from city name.
     * @param addressID
     * @throws SQLException
     * @see SQLException
     * @return contactID
     */
    public static String getAddressIDFromCity(String addressID) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM address WHERE city_name = ?");
        ps.setString(1, addressID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            addressID = rs.getString("city_name");
        }
        return addressID;
    }
}