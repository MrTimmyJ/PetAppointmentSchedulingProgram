package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.Country;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get Country data from the database
 */
public class CountryAccess extends Country {

    public CountryAccess(int countryID, String countryName) {
        super(countryID, countryName);
    }

    /**
     * Get all countries from the database.
     *
     * @return countriesObservableList
     * @throws SQLException
     */
    public static ObservableList<CountryAccess> getCountries() throws SQLException {
        ObservableList<CountryAccess> countriesObservableList = FXCollections.observableArrayList();

        String sql = "SELECT country_id, country_name from country";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryID = rs.getInt("country_id");
            String countryName = rs.getString("country_name");

            CountryAccess country = new CountryAccess(countryID, countryName);
            countriesObservableList.add(country);
        }
        return countriesObservableList;
    }
}