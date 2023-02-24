package PetAppointments.forms;

import PetAppointments.dataLayout.*;
import PetAppointments.databaseAccess.*;
import PetAppointments.JDBC;

import PetAppointments.databaseAccess.ServiceTypeAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Appointment class allows the user to view appointments. User can add, update, and delete appointments. Dropdown menu allows for the user to quickly find appointments depending on the selected month.
 */
public class HomePage {

    //Appointment Page
    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<?, ?> appointmentID;
    @FXML
    private TableColumn<?, ?> petID;
    @FXML
    private TableColumn<?, ?> groomerID;
    @FXML
    private TableColumn<?, ?> serviceTypeID;
    @FXML
    private TableColumn<?, ?> appointmentDescription;
    @FXML
    public TableColumn<?, ?> appointmentStart;
    @FXML
    public TableColumn<?, ?> appointmentEnd;
    @FXML
    private TableColumn<?, ?> serviceCost;
    @FXML
    private TableColumn<?, ?> serviceLocation;
    @FXML
    private TableColumn<?, ?> appointmentStatus;

    @FXML
    private TextField updateAppointmentID;
    @FXML
    private TextField updatePetID;
    @FXML
    private TextField updateGroomerID;
    @FXML
    private TextField updateServiceTypeID;
    @FXML
    private TextField updateAppointmentDescription;
    @FXML
    private TextField updateServiceCost;
    @FXML
    private TextField updateServiceLocation;
    @FXML
    private TextField updateAppointmentStatus;

    @FXML
    public DatePicker updateAppointmentStartDate;
    @FXML
    public DatePicker updateAppointmentEndDate;

    @FXML
    public ComboBox<String> updateAppointmentStartTime;
    @FXML
    public ComboBox<String> updateAppointmentEndTime;

    @FXML
    private ComboBox<String> monthCombo;

    // Pet Record Page
    @FXML
    private TableView<Pet> petRecordsTable;
    @FXML
    private TableView<AddressAccess> petAddressTable;

    @FXML
    private TextField searchPetField;

    @FXML
    private TableColumn<?, ?> pet_ID;
    @FXML
    private TableColumn<?, ?> petTypeID;
    @FXML
    private TableColumn<?, ?> petName;
    @FXML
    private TableColumn<?, ?> ownerName;
    @FXML
    private TableColumn<?, ?> petAddressID;
    @FXML
    private TableColumn<?, ?> petBirthdate;
    @FXML
    private TableColumn<?, ?> petStatus;

    @FXML
    private TextField updatePet_ID;
    @FXML
    private TextField updatePetTypeID;
    @FXML
    private TextField updatePetName;
    @FXML
    private TextField updateOwnerName;
    @FXML
    private TextField updatePetAddressID;
    @FXML
    private TextField updatePetBirthdate;
    @FXML
    private TextField updatePetStatus;

    @FXML
    private TableColumn<?, ?> addressID;
    @FXML
    private TableColumn<?, ?> addressLineOne;
    @FXML
    private TableColumn<?, ?> addressLineTwo;
    @FXML
    private TableColumn<?, ?> cityID;
    @FXML
    private TableColumn<?, ?> postalCode;
    @FXML
    private TableColumn<?, ?> phone;

    @FXML
    private TextField updateAddressID;
    @FXML
    private TextField updateAddressLineOne;
    @FXML
    private TextField updateAddressLineTwo;
    @FXML
    private TextField updateCityID;
    @FXML
    private TextField updatePostalCode;
    @FXML
    private TextField updatePhone;

    // Report Page
    @FXML
    private TableView<ReportType> totalServiceTypeAppointments; // Report Type (was appointmentTotalsAppointmentType)
    @FXML
    private TableView<ReportSales> totalGroomerSales; // Report Month one (was appointmentTotalAppointmentByMonth)
    @FXML
    private TableView<ReportCities> totalAppointmentsByCity; //Reports one (was customerByCountry)

    @FXML
    private TableColumn<?, ?> serviceTypeDescription;
    @FXML
    private TableColumn<?, ?> serviceAmount;
    @FXML
    private TableColumn<?, ?> groomerName;
    @FXML
    private TableColumn<?, ?> groomerTotalSales;
    @FXML
    private TableColumn<?, ?> cityName;
    @FXML
    private TableColumn<?, ?> appointmentAmount;

    /**
     * When the form opens set each table cell with its value.
     */
    public void initialize() throws SQLException {
        appointmentTab();
    }

    /**
     * Set each table cell with its value.
     *
     * @throws SQLException
     * @see SQLException
     */
    public void appointmentTab() throws SQLException {
        try {
            ObservableList<Appointment> appointmentList = AppointmentAccess.getAppointments();

            ObservableList<String> appointmentTimesList = FXCollections.observableArrayList();

            appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            petID.setCellValueFactory(new PropertyValueFactory<>("petID"));
            groomerID.setCellValueFactory(new PropertyValueFactory<>("groomerID"));
            serviceTypeID.setCellValueFactory(new PropertyValueFactory<>("serviceTypeID"));
            appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
            appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
            serviceCost.setCellValueFactory(new PropertyValueFactory<>("serviceCost"));
            serviceLocation.setCellValueFactory(new PropertyValueFactory<>("serviceLocation"));
            appointmentStatus.setCellValueFactory(new PropertyValueFactory<>("appointmentStatus"));

            LocalTime start = LocalTime.MIN.plusHours(10);
            LocalTime end = LocalTime.MAX.minusHours(8);

            if (!start.equals(0) || !end.equals(0)) {
                while (start.isBefore(end)) {
                    appointmentTimesList.add(String.valueOf(start));
                    start = start.plusMinutes(30);
                }
            }

            appointmentTable.setItems(appointmentList);

            updateAppointmentStartTime.setItems(appointmentTimesList);
            updateAppointmentEndTime.setItems(appointmentTimesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set each table cell with its value.
     *
     * @throws SQLException
     * @see SQLException
     */
    public void petTab() throws SQLException {
        try {
            Connection connection = JDBC.getConnection();
            ObservableList<Pet> petsList = PetAccess.getPets(connection);
            ObservableList<AddressAccess> addressList = AddressAccess.getAllAddressID();

            pet_ID.setCellValueFactory(new PropertyValueFactory<>("petID"));
            petTypeID.setCellValueFactory(new PropertyValueFactory<>("petTypeID"));
            petName.setCellValueFactory(new PropertyValueFactory<>("petName"));
            ownerName.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
            petAddressID.setCellValueFactory(new PropertyValueFactory<>("petAddressID"));
            petBirthdate.setCellValueFactory(new PropertyValueFactory<>("petBirthdate"));
            petStatus.setCellValueFactory(new PropertyValueFactory<>("petStatus"));

            addressID.setCellValueFactory(new PropertyValueFactory<>("addressID"));
            addressLineOne.setCellValueFactory(new PropertyValueFactory<>("addressLineOne"));
            addressLineTwo.setCellValueFactory(new PropertyValueFactory<>("addressLineTwo"));
            cityID.setCellValueFactory(new PropertyValueFactory<>("cityID"));
            postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            petRecordsTable.setItems(petsList);
            petAddressTable.setItems(addressList);

            // Search Functionality with listener to change the table data
            FilteredList<Pet> filteredData = new FilteredList<>(petsList, b -> true);
            searchPetField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(petSearch -> {

                    // No input from user - show all records
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    // Set input to be lowercase for easier search
                    String searchInput = newValue.toLowerCase();

                    // Found matching search result
                    if (String.valueOf(petSearch.getPetID()).indexOf(searchInput) > -1) {
                        return true;
                    } else if (String.valueOf(petSearch.getPetTypeID()).indexOf(searchInput) > -1) {
                        return true;
                    } else if (petSearch.getPetName().toLowerCase().indexOf(searchInput) > -1) {
                        return true;
                    } else if (petSearch.getOwnerName().toLowerCase().indexOf(searchInput) > -1) {
                        return true;
                    } else if (String.valueOf(petSearch.getPetAddressID()).indexOf(searchInput) > -1) {
                        return true;
                    } else if (String.valueOf(petSearch.getPetBirthdate()).indexOf(searchInput) > -1) {
                        return true;
                    } else if (String.valueOf(petSearch.getPetStatus()).indexOf(searchInput) > -1) {
                        return true;
                    } else {
                        // No result found
                        return false;
                    }

                });
            });

            // Bind sorted data with pet records table
            SortedList<Pet> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(petRecordsTable.comparatorProperty());

            // Update Table with new search results
            petRecordsTable.setItems(sortedData);

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Set each table cell with its value.
     *
     * @throws SQLException
     * @see SQLException
     */
    public void reportTab() throws SQLException {
        try {
            appointmentsByServiceType();
            groomerSales();
            appointmentTotalByCity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Button that allows the user to submit the appointment data to the database.
     *
     * @param event
     * @throws IOException
     * @see IOException
     */
    @FXML
    void newAppointment(ActionEvent event) throws IOException {
        try {
            Connection connection = JDBC.getConnection();
            if (!updateAppointmentID.getText().isEmpty() && !updatePetID.getText().isEmpty() && !updateGroomerID.getText().isEmpty() && !updateServiceTypeID.getText().isEmpty() && !updateAppointmentDescription.getText().isEmpty() && updateAppointmentStartDate.getValue() != null && updateAppointmentStartTime.getValue() != null && updateAppointmentEndDate.getValue() != null && updateAppointmentEndTime.getValue() != null && !updateServiceCost.getText().isEmpty() && !updateServiceLocation.getText().isEmpty() && !updateAppointmentStatus.getText().isEmpty()) {
                ObservableList<Appointment> getAllAppointments = AppointmentAccess.getAppointments();

                int newAppointmentID = Integer.parseInt(String.valueOf((int) (Math.random() * 100)));
                int petID = Integer.parseInt(updatePetID.getText());
                int groomerID = Integer.parseInt(updateGroomerID.getText());
                int serviceID = Integer.parseInt(updateServiceTypeID.getText());
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

                LocalDate localDateStart = updateAppointmentStartDate.getValue();
                LocalDate localDateEnd = updateAppointmentEndDate.getValue();

                LocalTime localTimeStart = LocalTime.parse(updateAppointmentStartTime.getValue(), timeFormat);
                LocalDateTime dateTimeStart = LocalDateTime.of(localDateStart, localTimeStart);

                LocalTime LocalTimeEnd = LocalTime.parse(updateAppointmentEndTime.getValue(), timeFormat);
                LocalDateTime dateTimeEnd = LocalDateTime.of(localDateEnd, LocalTimeEnd);
                System.out.println(localTimeStart + " " + dateTimeStart);

                ZonedDateTime zoneStart = ZonedDateTime.of(dateTimeStart, ZoneId.systemDefault());

                LocalTime startAppointmentTimeToCheck = zoneStart.toLocalTime();
                DayOfWeek startAppointmentDayToCheck = zoneStart.toLocalDate().getDayOfWeek();


                ZonedDateTime zoneEnd = ZonedDateTime.of(dateTimeEnd, ZoneId.systemDefault());

                LocalTime endAppointmentTimeToCheck = zoneEnd.toLocalTime();
                DayOfWeek endAppointmentDayToCheck = zoneEnd.toLocalDate().getDayOfWeek();

                int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();
                int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();
                int workWeekStart = DayOfWeek.TUESDAY.getValue();
                int workWeekEnd = DayOfWeek.SUNDAY.getValue();

                LocalTime businessStart = LocalTime.of(8, 0, 0);
                LocalTime businessEnd = LocalTime.of(22, 0, 0);

                if (startAppointmentDayToCheckInt < workWeekStart || startAppointmentDayToCheckInt > workWeekEnd || endAppointmentDayToCheckInt < workWeekStart || endAppointmentDayToCheckInt > workWeekEnd) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Day is outside of business operations (Tuesday-Sunday)");
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }

                if (startAppointmentTimeToCheck.isBefore(businessStart) || startAppointmentTimeToCheck.isAfter(businessEnd) || endAppointmentTimeToCheck.isBefore(businessStart) || endAppointmentTimeToCheck.isAfter(businessEnd)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Time is outside of business hours (10am-4pm): " + startAppointmentTimeToCheck + " - " + endAppointmentTimeToCheck);
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }

                // start time after end time
                if (dateTimeStart.isAfter(dateTimeEnd)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment:  Appointment start date can not be after the end time.");
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }

                // same start and end time
                if (dateTimeStart.isEqual(dateTimeEnd)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }
                for (Appointment appointment : getAllAppointments) {
                    LocalDateTime aptStart = appointment.getStart();
                    LocalDateTime aptEnd = appointment.getEnd();

                    // Appointments overlap
                    if ((petID == appointment.getPetID()) && (newAppointmentID != appointment.getAppointmentID()) && (dateTimeStart.isBefore(aptStart)) && (dateTimeEnd.isAfter(aptEnd))) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // Appointment start time is the same time as another appointment
                    if ((petID == appointment.getPetID()) && (newAppointmentID != appointment.getAppointmentID()) && (dateTimeStart.isAfter(aptStart)) && (dateTimeStart.isBefore(aptEnd))) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // Appointment end time is the same time as another appointment
                    if (petID == appointment.getPetID() && (newAppointmentID != appointment.getAppointmentID()) && (dateTimeEnd.isAfter(aptStart)) && (dateTimeEnd.isBefore(aptEnd))) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // New Years is a Holiday
                    if (dateTimeStart.getDayOfMonth() == 1 && dateTimeStart.getDayOfYear() == 1 && dateTimeEnd.getDayOfMonth() == 1 && dateTimeEnd.getDayOfYear() == 1) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // Fourth of July is a Holiday
                    if (dateTimeStart.getDayOfMonth() == 4 && dateTimeStart.getMonthValue() == 7 && dateTimeEnd.getDayOfMonth() == 4 && dateTimeEnd.getMonthValue() == 7) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    LocalDate nextWeekFromStart = dateTimeStart.toLocalDate().plus(1, ChronoUnit.WEEKS);
                    LocalDate nextWeekFromEnd = dateTimeStart.toLocalDate().plus(1, ChronoUnit.WEEKS);

                    // Memorial Day is a Holiday - check if day is monday and then add 7 days if its june then its memorial day (isStartMonday.equals(1)) && (nextWeekFromStart.getMonthValue() != 5)
                    if (DayOfWeek.valueOf(nextWeekFromStart.getDayOfWeek().toString()).getValue() == 1 && DayOfWeek.valueOf(nextWeekFromEnd.getDayOfWeek().toString()).getValue() == 1) {
                        if (nextWeekFromStart.getMonthValue() == 6 && nextWeekFromEnd.getMonthValue() == 6) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                            Optional<ButtonType> confirmation = alert.showAndWait();
                            return;
                        }
                    }

                    LocalDate lastWeekFromStart = dateTimeStart.toLocalDate().minus(1, ChronoUnit.WEEKS);
                    LocalDate lastWeekFromEnd = dateTimeStart.toLocalDate().minus(1, ChronoUnit.WEEKS);

                    // Labor Day is a Holiday - check if day is monday and then minus 7 days if its august then its labor day
                    if (DayOfWeek.valueOf(lastWeekFromStart.getDayOfWeek().toString()).getValue() == 1 && DayOfWeek.valueOf(lastWeekFromEnd.getDayOfWeek().toString()).getValue() == 1) {
                        if (lastWeekFromStart.getMonthValue() == 8 && lastWeekFromEnd.getMonthValue() == 8) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                            Optional<ButtonType> confirmation = alert.showAndWait();
                            return;
                        }
                    }

                    if (dateTimeStart.isEqual(aptStart)) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Groomer is already scheduled for another appointment.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    if (dateTimeStart.isBefore(aptEnd) && dateTimeStart.isAfter(aptStart)) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Groomer is already scheduled for another appointment.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }
                }

                String sql = "INSERT INTO appointment (appointment_id, pet_id, groomer_id, servicetype_id, appointment_description, start_datetime, end_datetime, service_cost, service_location, appointment_status, created_at, created_by, updated_at, updated_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                JDBC.makePreparedStatement(sql, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, newAppointmentID);
                ps.setInt(2, petID);
                ps.setInt(3, groomerID);
                ps.setInt(4, serviceID);
                ps.setString(5, updateAppointmentDescription.getText());
                ps.setTimestamp(6, Timestamp.valueOf(dateTimeStart));
                ps.setTimestamp(7, Timestamp.valueOf(dateTimeEnd));
                ps.setDouble(8, Double.parseDouble(updateServiceCost.getText()));
                ps.setString(9, updateServiceLocation.getText());
                ps.setBoolean(10, Boolean.parseBoolean(updateAppointmentStatus.toString()));
                ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(12, "admin");
                ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(14, "admin");

                hideAppointment();
                //System.out.println(ps);
                ps.execute();
            }

            Parent root = FXMLLoader.load(getClass().getResource("../forms/homePage.fxml"));
            Scene scene = new Scene(root);
            Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
            MainScreenReturn.setScene(scene);
            MainScreenReturn.show();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Button that allows the user to submit the appointment data to the database.
     *
     * @param event
     * @throws IOException
     * @see IOException
     */
    @FXML
    void updateAppointment(ActionEvent event) throws IOException {
        try {
            Connection connection = JDBC.getConnection();
            if (!updateAppointmentID.getText().isEmpty() && !updatePetID.getText().isEmpty() && !updateGroomerID.getText().isEmpty() && !updateServiceTypeID.getText().isEmpty() && !updateAppointmentDescription.getText().isEmpty() && updateAppointmentStartDate.getValue() != null && updateAppointmentStartTime.getValue() != null && updateAppointmentEndDate.getValue() != null && updateAppointmentEndTime.getValue() != null && !updateServiceCost.getText().isEmpty() && !updateServiceLocation.getText().isEmpty() && !updateAppointmentStatus.getText().isEmpty()) {
                ObservableList<Appointment> getAllAppointments = AppointmentAccess.getAppointments();

                int appointmentID = Integer.parseInt(updateAppointmentID.getText());
                int petID = Integer.parseInt(updatePetID.getText());
                int groomerID = Integer.parseInt(updateGroomerID.getText());
                int serviceID = Integer.parseInt(updateServiceTypeID.getText());

                LocalDate localDateStart = updateAppointmentStartDate.getValue();
                LocalDate localDateEnd = updateAppointmentEndDate.getValue();

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

                String appointmentStartDate = localDateStart.format(dateFormat);
                String appointmentStartTime = LocalTime.parse(updateAppointmentStartTime.getValue(), timeFormat).toString();

                String appointmentEndDate = localDateEnd.format(dateFormat);
                String appointmentEndTime = LocalTime.parse(updateAppointmentEndTime.getValue(), timeFormat).toString();
                //System.out.println(appointmentStartDate + " " + appointmentStartTime);

                LocalTime localTimeStart = LocalTime.parse(updateAppointmentStartTime.getValue(), timeFormat);
                LocalDateTime dateTimeStart = LocalDateTime.of(localDateStart, localTimeStart);

                LocalTime LocalTimeEnd = LocalTime.parse(updateAppointmentEndTime.getValue(), timeFormat);
                LocalDateTime dateTimeEnd = LocalDateTime.of(localDateEnd, LocalTimeEnd);

                ZonedDateTime zoneStart = ZonedDateTime.of(dateTimeStart, ZoneId.systemDefault());
                LocalTime startAppointmentTimeToCheck = zoneStart.toLocalTime();
                DayOfWeek startAppointmentDayToCheck = zoneStart.toLocalDate().getDayOfWeek();

                ZonedDateTime zoneEnd = ZonedDateTime.of(dateTimeEnd, ZoneId.systemDefault());
                LocalTime endAppointmentTimeToCheck = zoneEnd.toLocalTime();
                DayOfWeek endAppointmentDayToCheck = zoneEnd.toLocalDate().getDayOfWeek();

                int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();
                int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();
                int workWeekStart = DayOfWeek.TUESDAY.getValue();
                int workWeekEnd = DayOfWeek.SUNDAY.getValue();

                LocalTime businessStart = LocalTime.of(8, 0, 0);
                LocalTime businessEnd = LocalTime.of(22, 0, 0);

                if (startAppointmentDayToCheckInt < workWeekStart || startAppointmentDayToCheckInt > workWeekEnd || endAppointmentDayToCheckInt < workWeekStart || endAppointmentDayToCheckInt > workWeekEnd) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Day is outside of business operations (Tuesday-Sunday)");
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }

                if (startAppointmentTimeToCheck.isBefore(businessStart) || startAppointmentTimeToCheck.isAfter(businessEnd) || endAppointmentTimeToCheck.isBefore(businessStart) || endAppointmentTimeToCheck.isAfter(businessEnd)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Time is outside of business hours (10am-4pm): " + startAppointmentTimeToCheck + " - " + endAppointmentTimeToCheck);
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }

                // start time after end time
                if (dateTimeStart.isAfter(dateTimeEnd)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }

                // same start and end time
                if (dateTimeStart.isEqual(dateTimeEnd)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    return;
                }
                for (Appointment appointment : getAllAppointments) {
                    LocalDateTime aptStart = appointment.getStart();
                    LocalDateTime aptEnd = appointment.getEnd();

                    // Appointments overlap
                    if ((petID == appointment.getPetID()) && (appointmentID != appointment.getAppointmentID()) && (dateTimeStart.isBefore(aptStart)) && (dateTimeEnd.isAfter(aptEnd))) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // Appointment start time is the same time as another appointment
                    if ((petID == appointment.getPetID()) && (appointmentID != appointment.getAppointmentID()) && (dateTimeStart.isAfter(aptStart)) && (dateTimeStart.isBefore(aptEnd))) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // Appointment end time is the same time as another appointment
                    if (petID == appointment.getPetID() && (appointmentID != appointment.getAppointmentID()) && (dateTimeEnd.isAfter(aptStart)) && (dateTimeEnd.isBefore(aptEnd))) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Error Scheduling Appointment: An Appointment is already scheduled at this time.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // New Years is a Holiday
                    if (dateTimeStart.getDayOfMonth() == 1 && dateTimeStart.getDayOfYear() == 1 && dateTimeEnd.getDayOfMonth() == 1 && dateTimeEnd.getDayOfYear() == 1) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    // Fourth of July is a Holiday
                    if (dateTimeStart.getDayOfMonth() == 4 && dateTimeStart.getMonthValue() == 7 && dateTimeEnd.getDayOfMonth() == 4 && dateTimeEnd.getMonthValue() == 7) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }

                    LocalDate nextWeekFromStart = dateTimeStart.toLocalDate().plus(1, ChronoUnit.WEEKS);
                    LocalDate nextWeekFromEnd = dateTimeStart.toLocalDate().plus(1, ChronoUnit.WEEKS);

                    // Memorial Day is a Holiday - check if day is monday and then add 7 days if its june then its memorial day (isStartMonday.equals(1)) && (nextWeekFromStart.getMonthValue() != 5)
                    if (DayOfWeek.valueOf(nextWeekFromStart.getDayOfWeek().toString()).getValue() == 1 && DayOfWeek.valueOf(nextWeekFromEnd.getDayOfWeek().toString()).getValue() == 1) {
                        if (nextWeekFromStart.getMonthValue() == 6 && nextWeekFromEnd.getMonthValue() == 6) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                            Optional<ButtonType> confirmation = alert.showAndWait();
                            return;
                        }
                    }

                    LocalDate lastWeekFromStart = dateTimeStart.toLocalDate().minus(1, ChronoUnit.WEEKS);
                    LocalDate lastWeekFromEnd = dateTimeStart.toLocalDate().minus(1, ChronoUnit.WEEKS);

                    // Labor Day is a Holiday - check if day is monday and then minus 7 days if its august then its labor day
                    if (DayOfWeek.valueOf(lastWeekFromStart.getDayOfWeek().toString()).getValue() == 1 && DayOfWeek.valueOf(lastWeekFromEnd.getDayOfWeek().toString()).getValue() == 1) {
                        if (lastWeekFromStart.getMonthValue() == 8 && lastWeekFromEnd.getMonthValue() == 8) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment date is on a holiday.");
                            Optional<ButtonType> confirmation = alert.showAndWait();
                            return;
                        }
                    }

                    if (dateTimeStart.isBefore(aptEnd) && dateTimeStart.isAfter(aptStart)) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Groomer is already scheduled for another appointment.");
                        Optional<ButtonType> confirmation = alert.showAndWait();
                        return;
                    }
                }

                String startDate = updateAppointmentStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String startTime = updateAppointmentStartTime.getValue();

                String endDate = updateAppointmentEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String endTime = updateAppointmentEndTime.getValue();

                String start = startDate + " " + startTime + ":00";
                String end = endDate + " " + endTime + ":00";

                String sql = "UPDATE appointment SET appointment_id = ?, pet_id = ?, groomer_id = ?, servicetype_id = ?, appointment_description = ?, start_datetime = ?, end_datetime = ?, service_cost = ?, service_location = ?, appointment_status = ?, updated_at = ?, updated_by = ? WHERE appointment_id = ?";
                JDBC.makePreparedStatement(sql, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, Integer.parseInt(updateAppointmentID.getText()));
                ps.setInt(2, Integer.parseInt(updatePetID.getText()));
                ps.setInt(3, Integer.parseInt(updateGroomerID.getText()));
                ps.setInt(4, Integer.parseInt(updateServiceTypeID.getText()));
                ps.setString(5, updateAppointmentDescription.getText());
                ps.setString(6, start);
                ps.setString(7, end);
                ps.setDouble(8, Double.parseDouble(updateServiceCost.getText()));
                ps.setString(9, updateServiceLocation.getText());
                ps.setBoolean(10, Boolean.parseBoolean(updateAppointmentStatus.getText()));
                ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(12, "admin");
                ps.setInt(13, Integer.parseInt(updateAppointmentID.getText()));

                hideAppointment();
                //System.out.println(ps);
                ps.execute();
            }

            Parent root = FXMLLoader.load(getClass().getResource("../forms/homePage.fxml"));
            Scene scene = new Scene(root);
            Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
            MainScreenReturn.setScene(scene);
            MainScreenReturn.show();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Button allowing the user to remove an appointment.
     *
     * @param event
     * @throws Exception
     * @see Exception
     */
    @FXML
    void removeAppointment(ActionEvent event) throws Exception {
        try {
            Connection connection = JDBC.getConnection();
            int removeAppointment = appointmentTable.getSelectionModel().getSelectedItem().getAppointmentID();
            int removeServiceType = appointmentTable.getSelectionModel().getSelectedItem().getServiceTypeID();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected appointment with appointment ID: " + removeAppointment + " and appointment service type ID: " + removeServiceType);
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                AppointmentAccess.DeleteAppointment(removeAppointment, connection);
                ObservableList<Appointment> appointmentsList = AppointmentAccess.getAppointments();
                appointmentTable.setItems(appointmentsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts data into each cell for the selected appointment.
     */
    @FXML
    void showAppointment() {
        try {
            JDBC.makeConnection();
            Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

            if (selectedAppointment != null) {
                updateAppointmentID.setText(String.valueOf(selectedAppointment.getAppointmentID()));
                updatePetID.setText(String.valueOf(selectedAppointment.getPetID()));
                updateGroomerID.setText(String.valueOf(selectedAppointment.getGroomerID()));
                updateServiceTypeID.setText(String.valueOf(selectedAppointment.getServiceTypeID()));
                updateAppointmentDescription.setText(selectedAppointment.getAppointmentDescription());
                updateAppointmentStartDate.setValue(selectedAppointment.getStart().toLocalDate());
                updateAppointmentEndDate.setValue(selectedAppointment.getEnd().toLocalDate());
                updateAppointmentStartTime.setValue(String.valueOf(selectedAppointment.getStart().toLocalTime()));
                updateAppointmentEndTime.setValue(String.valueOf(selectedAppointment.getEnd().toLocalTime()));
                updateServiceCost.setText(String.valueOf(selectedAppointment.getServiceCost()));
                updateServiceLocation.setText(String.valueOf(selectedAppointment.getServiceLocation()));
                updateAppointmentStatus.setText(String.valueOf(selectedAppointment.getAppointmentStatus()));

                ObservableList<String> appointmentTimesList = FXCollections.observableArrayList();

                LocalTime start = LocalTime.MIN.plusHours(10);
                LocalTime end = LocalTime.MAX.minusHours(8);

                if (!start.equals(0) || !end.equals(0)) {
                    while (start.isBefore(end)) {
                        appointmentTimesList.add(String.valueOf(start));
                        start = start.plusMinutes(30);
                    }
                }
                updateAppointmentStartTime.setItems(appointmentTimesList);
                updateAppointmentEndTime.setItems(appointmentTimesList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear data from each cell to clean up form UI.
     */
    @FXML
    void hideAppointment() {
        updateAppointmentID.clear();
        updatePetID.clear();
        updateGroomerID.clear();
        updateServiceTypeID.clear();
        updateAppointmentDescription.clear();
        updateAppointmentStartDate.setValue(null);
        updateAppointmentEndDate.setValue(null);
        updateAppointmentStartTime.setValue(null);
        updateAppointmentEndTime.setValue(null);
        updateServiceCost.clear();
        updateServiceLocation.clear();
        updateAppointmentStatus.clear();
    }

    /**
     * Month Dropdown box sorting appointments by month.
     */
    @FXML
    void monthCombo() {
        try {
            ObservableList<Appointment> allAppointmentsList = AppointmentAccess.getAppointments();
            ObservableList<Appointment> appointmentsMonth = FXCollections.observableArrayList();
            //monthCombo.getSelectionModel().selectFirst();

            String monthComboString = monthCombo.getValue();

            if (monthComboString.equals("January")) {
                // Get Appointments For Only January
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 1) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("February")) {
                // Get Appointments For Only February
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 2) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("March")) {
                // Get Appointments For Only March
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 3) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("April")) {
                // Get Appointments For Only April
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 4) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("May")) {
                // Get Appointments For Only May
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 5) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("June")) {
                // Get Appointments For Only June
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 6) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("July")) {
                // Get Appointments For Only July
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 7) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("August")) {
                // Get Appointments For Only August
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 8) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("September")) {
                // Get Appointments For Only September
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 9) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("October")) {
                // Get Appointments For Only October
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 10) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("November")) {
                // Get Appointments For Only November
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 11) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("December")) {
                // Get Appointments For Only December
                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getStart().getMonthValue() == 12) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentTable.setItems(appointmentsMonth);
                });
            } else if (monthComboString.equals("All Months")) {
                // Get All Appointments
                appointmentTable.setItems(allAppointmentsList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Button that allows the user to add a new pet.
     *
     * @param event
     */
    @FXML
    void newPet(ActionEvent event) {
        try {
            Connection connection = JDBC.getConnection();

            if (!updatePet_ID.getText().isEmpty() || !updatePetTypeID.getText().isEmpty() || !updatePetName.getText().isEmpty() || !updateOwnerName.getText().isEmpty() || !updatePetAddressID.getText().isEmpty() || !updatePetBirthdate.getText().isEmpty() || !updatePetStatus.getText().isEmpty()) {
                //create random ID for new Pet
                Integer newPetID = (int) (Math.random() * 100);
                String date = updatePetBirthdate.getText();

                String sql = "INSERT INTO pet (pet_id, pettype_id, pet_name, owner_name, address_id, pet_birthdate, pet_status, created_at, created_by, updated_at, updated_by) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                JDBC.makePreparedStatement(sql, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, newPetID);
                ps.setInt(2, Integer.parseInt(updatePetTypeID.getText()));
                ps.setString(3, updatePetName.getText());
                ps.setString(4, updateOwnerName.getText());
                ps.setInt(5, Integer.parseInt(updatePetAddressID.getText()));
                ps.setDate(6, java.sql.Date.valueOf(date));
                ps.setBoolean(7, Boolean.parseBoolean(updatePetStatus.getText()));
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(9, "admin");
                ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(11, "admin");
                ps.execute();

                hidePet();

                ObservableList<Pet> refreshPetList = PetAccess.getPets(connection);
                petRecordsTable.setItems(refreshPetList);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Button to update the pet table in database.
     *
     * @param event
     */
    @FXML
    void updatePet(ActionEvent event) {
        try {
            Connection connection = JDBC.getConnection();
            if (!updatePet_ID.getText().isEmpty() || !updatePetTypeID.getText().isEmpty() || !updatePetName.getText().isEmpty() || !updateOwnerName.getText().isEmpty() || !updatePetAddressID.getText().isEmpty() || !updatePetBirthdate.getText().isEmpty() || !updatePetStatus.getText().isEmpty()) {
                String date = updatePetBirthdate.getText();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                LocalDate birthdateDate = LocalDate.parse(date, dtf);

                String insertStatement = "UPDATE pet SET pet_id = ?, pettype_id = ?, pet_name = ?, owner_name = ?, address_id = ?, pet_birthdate = ?,  pet_status = ?, created_at = ?, created_by = ?, updated_at = ?, updated_by = ? WHERE pet_id = ?";
                JDBC.makePreparedStatement(insertStatement, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, Integer.parseInt(updatePet_ID.getText()));
                ps.setInt(2, Integer.parseInt(updatePetTypeID.getText()));
                ps.setString(3, updatePetName.getText());
                ps.setString(4, updateOwnerName.getText());
                ps.setInt(5, Integer.parseInt(updatePetAddressID.getText()));
                ps.setObject(6, birthdateDate);
                ps.setBoolean(7, Boolean.parseBoolean(updatePetStatus.getText()));
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(9, "admin");
                ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(11, "admin");
                ps.setInt(12, Integer.parseInt(updatePet_ID.getText()));
                ps.execute();

                hidePet();

                ObservableList<Pet> refreshPetList = PetAccess.getPets(connection);
                petRecordsTable.setItems(refreshPetList);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "There is not a record with this ID.");
            Optional<ButtonType> confirmation = alert.showAndWait();
            return;
        }
    }

    /**
     * Button that allows the user to remove pet records.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void removePetRecord(ActionEvent event) throws Exception {
        Connection connection = JDBC.getConnection();
        ObservableList<Appointment> getAppointmentsList = AppointmentAccess.getAppointments();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remove the selected pet record and all appointments? ");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            int deletePetID = petRecordsTable.getSelectionModel().getSelectedItem().getPetID();
            AppointmentAccess.DeleteAppointment(deletePetID, connection);

            String sqlDelete = "DELETE FROM pet WHERE pet_id = ?";
            JDBC.makePreparedStatement(sqlDelete, JDBC.getConnection());

            PreparedStatement psDelete = JDBC.getPreparedStatement();
            int petFromTable = petRecordsTable.getSelectionModel().getSelectedItem().getPetID();

            //Delete all pet appointments from database.
            for (Appointment appointment : getAppointmentsList) {
                int petFromAppointments = appointment.getPetID();
                if (petFromTable == petFromAppointments) {
                    String deleteStatementAppointments = "DELETE FROM appointment WHERE appointment_id = ?";
                    JDBC.makePreparedStatement(deleteStatementAppointments, JDBC.getConnection());
                }
            }
            psDelete.setInt(1, petFromTable);
            psDelete.execute();
            ObservableList<Pet> refreshPetsList = PetAccess.getPets(connection);
            petRecordsTable.setItems(refreshPetsList);
        }
    }

    /**
     * Inserts data into each cell for the selected pet.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void showPet(ActionEvent event) throws SQLException {
        try {
            JDBC.makeConnection();
            Pet selectedPet = (Pet) petRecordsTable.getSelectionModel().getSelectedItem();

            if (selectedPet != null) {
                updatePet_ID.setText(String.valueOf((selectedPet.getPetID())));
                updatePetTypeID.setText(String.valueOf((selectedPet.getPetTypeID())));
                updatePetName.setText(selectedPet.getPetName());
                updateOwnerName.setText(selectedPet.getOwnerName());
                updatePetAddressID.setText(String.valueOf(selectedPet.getPetAddressID()));
                updatePetBirthdate.setText(selectedPet.getPetBirthdate().toString());
                updatePetStatus.setText(String.valueOf(selectedPet.getPetStatus()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear data from each cell to clean up form UI.
     */
    @FXML
    void hidePet() {
        updatePet_ID.clear();
        updatePetTypeID.clear();
        updatePetName.clear();
        updateOwnerName.clear();
        updatePetAddressID.clear();
        updatePetBirthdate.clear();
        updatePetStatus.clear();
    }

    /**
     * Button that allows the user to add a new address.
     *
     * @param event
     */
    @FXML
    void newAddress(ActionEvent event) {
        try {
            Connection connection = JDBC.getConnection();

            if (!updateAddressID.getText().isEmpty() || !updateAddressLineOne.getText().isEmpty() || !updateAddressLineTwo.getText().isEmpty() || !updateCityID.getText().isEmpty() || !updatePostalCode.getText().isEmpty() || !updatePhone.getText().isEmpty()) {
                //create random ID for new Address
                Integer newAddressID = (int) (Math.random() * 100);

                String sql = "INSERT INTO address (address_id, addressline_1, addressline_2, city_id, postal_code, phone, created_at, created_by, updated_at, updated_by) VALUES (?,?,?,?,?,?,?,?,?,?)";
                JDBC.makePreparedStatement(sql, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, newAddressID);
                ps.setString(2, updateAddressLineOne.getText());
                ps.setString(3, updateAddressLineTwo.getText());
                ps.setInt(4, Integer.parseInt(updateCityID.getText()));
                ps.setString(5, updatePostalCode.getText());
                ps.setString(6, updatePhone.getText());
                ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(8, "admin");
                ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(10, "admin");
                ps.execute();

                hideAddress();

                ObservableList<AddressAccess> refreshAddressList = AddressAccess.getAllAddressID();
                petAddressTable.setItems(refreshAddressList);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Button to update the address table in database.
     *
     * @param event
     */
    @FXML
    void updateAddress(ActionEvent event) {
        try {
            Connection connection = JDBC.getConnection();
            if (!updateAddressID.getText().isEmpty() || !updateAddressLineOne.getText().isEmpty() || !updateAddressLineTwo.getText().isEmpty() || !updateCityID.getText().isEmpty() || !updatePostalCode.getText().isEmpty() || !updatePhone.getText().isEmpty()) {
                String insertStatement = "UPDATE address SET address_id = ?, addressline_1 = ?, addressline_2 = ?, city_id = ?, postal_code = ?, phone = ?, created_at = ?, created_by = ?, updated_at = ?, updated_by = ? WHERE address_id = ?";
                JDBC.makePreparedStatement(insertStatement, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, Integer.parseInt(updateAddressID.getText()));
                ps.setString(2, updateAddressLineOne.getText());
                ps.setString(3, updateAddressLineTwo.getText());
                ps.setInt(4, Integer.parseInt(updateCityID.getText()));
                ps.setString(5, updatePostalCode.getText());
                ps.setString(6, updatePhone.getText());
                ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(8, "admin");
                ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(10, "admin");
                ps.setInt(11, Integer.parseInt(updateAddressID.getText()));
                ps.execute();

                hideAddress();

                ObservableList<AddressAccess> refreshAddressList = AddressAccess.getAllAddressID();
                petAddressTable.setItems(refreshAddressList);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "There is not a record with this ID.");
            Optional<ButtonType> confirmation = alert.showAndWait();
            return;
        }
    }

    /**
     * Button that allows the user to remove address records.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void removePetAddress(ActionEvent event) throws Exception {
        Connection connection = JDBC.getConnection();
        ObservableList<AddressAccess> getAppointmentsList = AddressAccess.getAllAddressID();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remove the selected address? ");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            int deleteAddressID = petAddressTable.getSelectionModel().getSelectedItem().getAddressID();

            String sqlDelete = "DELETE FROM address WHERE address_id = ?";
            JDBC.makePreparedStatement(sqlDelete, JDBC.getConnection());

            PreparedStatement psDelete = JDBC.getPreparedStatement();
            int addressFromTable = petRecordsTable.getSelectionModel().getSelectedItem().getPetAddressID();


            psDelete.setInt(1, addressFromTable);
            psDelete.execute();
            ObservableList<AddressAccess> refreshAddressList = AddressAccess.getAllAddressID();
            petAddressTable.setItems(refreshAddressList);
        }
    }

    /**
     * Inserts data into each cell for the selected address.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void showAddress(ActionEvent event) throws SQLException {
        try {
            JDBC.makeConnection();
            Address selectedAddress = (Address) petAddressTable.getSelectionModel().getSelectedItem();

            if (selectedAddress != null) {
                updateAddressID.setText(String.valueOf((selectedAddress.getAddressID())));
                updateAddressLineOne.setText(String.valueOf((selectedAddress.getAddressLineOne())));
                updateAddressLineTwo.setText(selectedAddress.getAddressLineTwo());
                updateCityID.setText(String.valueOf((selectedAddress.getCityID())));
                updatePostalCode.setText(String.valueOf((selectedAddress.getPostalCode())));
                updatePhone.setText(selectedAddress.getPhone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear data from each cell to clean up form UI.
     */
    @FXML
    void hideAddress() {
        updateAddressID.clear();
        updateAddressLineOne.clear();
        updateAddressLineTwo.clear();
        updateCityID.clear();
        updatePostalCode.clear();
        updatePhone.clear();
    }

    /**
     * Report amount of appointments by service type for the current year.
     * Includes lambda function adding all serviceTypes to a list
     */
    @FXML
    public void appointmentsByServiceType() {
        try {
            ObservableList<ReportType> getServiceTypes = ReportAccess.getServiceTypeAmount();
            ObservableList<ReportType> servicesToAdd = FXCollections.observableArrayList();

            ObservableList<ServiceType> serviceList = ServiceTypeAccess.getServicesTypeID();
            ObservableList<String> servAdded = FXCollections.observableArrayList();

            // Lambda function to add all servicetypes to a list for the report.
            serviceList.forEach(ServiceType -> servAdded.add(ServiceType.getServiceTypeDescription()));

            getServiceTypes.forEach(servicesToAdd::add);
            totalServiceTypeAppointments.setItems(servicesToAdd);
            serviceTypeDescription.setCellValueFactory(new PropertyValueFactory<>("ServiceType"));
            serviceAmount.setCellValueFactory(new PropertyValueFactory<>("TotalAppointments"));
            LogReport("Total Appointments by Service Type");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Report the total number of groomer sales for the year.
     * Lambda function adding groomers to a list
     *
     * @throws SQLException
     * @see SQLException
     */
    public void groomerSales() throws SQLException {
        try {
            ObservableList<ReportSales> groomerSalesList = ReportAccess.getGroomerAppointmentAmount();
            ObservableList<ReportSales> groomersToAdd = FXCollections.observableArrayList();

            ObservableList<GroomerAccess> groomersList = GroomerAccess.getGroomers();
            ObservableList<String> groomersAdded = FXCollections.observableArrayList();

            // Lambda function to add all groomers to a list for the report.
            groomersList.forEach(Groomer -> groomersAdded.add(Groomer.getGroomerName()));

            groomerSalesList.forEach(groomersToAdd::add);
            totalGroomerSales.setItems(groomersToAdd);
            groomerName.setCellValueFactory(new PropertyValueFactory<>("GroomerName"));
            groomerTotalSales.setCellValueFactory(new PropertyValueFactory<>("SalesTotal"));
            LogReport("Total Groomer Sales");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Report appointment totals by city for the year.
     * Lambda function adding all cities to a list
     *
     * @throws SQLException
     * @see SQLException
     */
    public void appointmentTotalByCity() throws SQLException {
        try {
            ObservableList<ReportCities> citiesList = ReportAccess.getCities();
            ObservableList<ReportCities> citiesToAdd = FXCollections.observableArrayList();

            ObservableList<CityAccess> cityList = CityAccess.getCities();
            ObservableList<String> citiesAdded = FXCollections.observableArrayList();

            // Lambda function to add all cities to a list for the report.
            cityList.forEach(Groomer -> citiesAdded.add(Groomer.getCityName()));

            citiesList.forEach(citiesToAdd::add);
            totalAppointmentsByCity.setItems(citiesToAdd);
            cityName.setCellValueFactory(new PropertyValueFactory<>("CityName"));
            appointmentAmount.setCellValueFactory(new PropertyValueFactory<>("TotalAppointments"));
            LogReport("Total Pet Appointments By City");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Save Groomer ID, Report Name, and TimeStamp to report_tracking.txt
     *
     * @throws IOException
     * @see IOException
     */
    public void LogReport(String currentReport) throws SQLException, IOException {
        try {
            String username;
            username = GroomerAccess.getUser();

            FileWriter writer = new FileWriter("report_tracking.txt", true);
            PrintWriter output = new PrintWriter(writer);

            output.print("Groomer: " + username + " logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + " and was looking at the: " + currentReport + " Report." + "\n");
            output.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}