package PetAppointments.databaseAccess;

import PetAppointments.dataLayout.Appointment;
import PetAppointments.dataLayout.ReportType;
import PetAppointments.dataLayout.ReportSales;
import PetAppointments.dataLayout.ReportCities;
import PetAppointments.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Get Report data from the database
 */
public class ReportAccess extends Appointment {

    public ReportAccess(int appointmentID, int petID, int groomerID, int serviceTypeID, String appointmentDescription, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, double serviceCost, String serviceLocation, Boolean appointmentStatus) {
        super(appointmentID, petID, groomerID, serviceTypeID, appointmentDescription, appointmentStart, appointmentEnd, serviceCost, serviceLocation, appointmentStatus);
    }

    /**
     * Get service type amount from database.
     *
     * @throws SQLException
     * @see SQLException
     * @return serviceTypeList
     */
    public static ObservableList<ReportType> getServiceTypeAmount() throws SQLException {
        ObservableList<ReportType> serviceTypeList = FXCollections.observableArrayList();

        String sql = "SELECT servicetype.servicetype_description, count(*) as service_count from servicetype inner join appointment on appointment.servicetype_id = servicetype.servicetype_id WHERE appointment.servicetype_id = servicetype.servicetype_id AND extract(year from appointment.start_datetime) = extract(year from CURRENT_TIMESTAMP) OR extract(year from appointment.start_datetime) = extract(year from CURRENT_TIMESTAMP) -1 group by servicetype.servicetype_id order by count(*) desc";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String serviceType = rs.getString("servicetype_description");
            int serviceCount = rs.getInt("service_count");
            ReportType serviceTypes = new ReportType(serviceType, serviceCount);
            serviceTypeList.add(serviceTypes);
        }
        return serviceTypeList;
    }

    /**
     * Get groomer ID and total appointments for each service type in the current year.
     *
     * @throws SQLException
     * @see SQLException
     * @return salesList
     */
    public static ObservableList<ReportSales> getGroomerAppointmentAmount() throws SQLException {
        ObservableList<ReportSales> salesList = FXCollections.observableArrayList();

        String sql = "SELECT groomer.groomer_name, count(appointment_id) as groomer_count from groomer inner join appointment on appointment.groomer_id = groomer.groomer_id WHERE appointment.groomer_id = groomer.groomer_id AND extract(year from appointment.start_datetime) = extract(year from CURRENT_TIMESTAMP) OR extract(year from appointment.start_datetime) = extract(year from CURRENT_TIMESTAMP) -1 group by groomer.groomer_id order by count(*) desc";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String groomerName = rs.getString("groomer_name");
            int totalAppointments = rs.getInt("groomer_count");
            ReportSales sales = new ReportSales(groomerName, totalAppointments);
            salesList.add(sales);
        }
        return salesList;
    }

    /**
     * Get cities and total appointments per city for the current year.
     *
     * @throws SQLException
     * @see SQLException
     * @return citiesList
     */
    public static ObservableList<ReportCities> getCities() throws SQLException {
        ObservableList<ReportCities> citiesList = FXCollections.observableArrayList();

        String sql = "SELECT appointment.service_location, count(service_location) as appointment_count from appointment inner join city on appointment.service_location = city.city_name inner join country on country.country_id = city.country_id WHERE appointment.service_location = city.city_name AND extract(year from appointment.start_datetime) = extract(year from CURRENT_TIMESTAMP) OR extract(year from appointment.start_datetime) = extract(year from CURRENT_TIMESTAMP) -1 group by city.country_id order by count(*) desc";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String cityName = rs.getString("service_location");
            int countryCount = rs.getInt("appointment_count");
            ReportCities cities = new ReportCities(cityName, countryCount);
            citiesList.add(cities);
        }
        return citiesList;
    }
}