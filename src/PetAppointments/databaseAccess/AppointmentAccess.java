package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.Appointment;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Get Appointment data from the database
 */
public class AppointmentAccess {
    public static ObservableList<Appointment> getAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("appointment_id");
            int petID = rs.getInt("pet_id");
            int groomerID = rs.getInt("groomer_id");
            int serviceTypeID = rs.getInt("servicetype_id");
            String appointmentDescription = rs.getString("appointment_description");
            LocalDateTime start = rs.getTimestamp("start_datetime").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("end_datetime").toLocalDateTime();
            double serviceCost = rs.getDouble("service_cost");
            String serviceLocation = rs.getString("service_location");
            boolean appointmentStatus = rs.getBoolean("appointment_status");

            Appointment appointment = new Appointment(appointmentID, petID, groomerID, serviceTypeID, appointmentDescription, start, end, serviceCost, serviceLocation, appointmentStatus);
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    /**
     * Delete appointment from database.
     * @param appointmentToDelete
     * @param connection
     * @throws SQLException
     * @see SQLException
     * @return result
     */
    public static int DeleteAppointment(int appointmentToDelete, Connection connection) throws SQLException {
        String sql = "DELETE FROM appointment WHERE appointment_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, appointmentToDelete);
        int result = ps.executeUpdate();
        ps.close();
        return result;
    }
}
