package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.ServiceType;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get Service Type data from the database
 */
public class ServiceTypeAccess extends ServiceType {

    public ServiceTypeAccess(int petTypeID, String petTypeDescription) {
        super(petTypeID, petTypeDescription);
    }

    /**
     * Get all service type ID's from database.
     * @throws SQLException
     * @see SQLException
     * @return serviceTypeObservableList
     */
    public static ObservableList<ServiceType> getServicesTypeID() throws SQLException {
        ObservableList<ServiceType> serviceTypeObservableList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM servicetype";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int serviceTypeID = rs.getInt("servicetype_id");
            String serviceTypeDescription = rs.getString("servicetype_description");

            ServiceTypeAccess serviceType = new ServiceTypeAccess(serviceTypeID, serviceTypeDescription);
            serviceTypeObservableList.add(serviceType);
        }
        return serviceTypeObservableList;
    }
}