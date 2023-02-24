package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.City;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get City data from the database
 */
public class CityAccess extends City {

    public CityAccess(int cityID, String cityName, int country_ID) {
        super(cityID, cityName, country_ID);
    }

    /**
     * Get all cities in the database.
     * @throws SQLException
     * @see SQLException
     * @return citiesList
     */
    public static ObservableList<CityAccess> getCities() throws SQLException {
        ObservableList<CityAccess> citiesList = FXCollections.observableArrayList();
        String sql = "SELECT * from city";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int cityID = rs.getInt("city_id");
            String cityName = rs.getString("city_name");
            int countryID = rs.getInt("country_id");
            CityAccess cities = new CityAccess(cityID, cityName, countryID);
            citiesList.add(cities);
        }
        return citiesList;
    }
}
