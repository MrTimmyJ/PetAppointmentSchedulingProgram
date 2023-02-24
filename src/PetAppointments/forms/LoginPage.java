package PetAppointments.forms;

import PetAppointments.databaseAccess.GroomerAccess;
import PetAppointments.databaseAccess.AppointmentAccess;
import PetAppointments.dataLayout.Appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * LoginPage displays a form that the user can use to login to the database with.
 */
public class LoginPage implements Initializable {

    // FXML Objects
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginScreenUsername;
    @FXML
    private PasswordField loginScreenPassword;

    // Text for alerts
    private String messageName;
    private String messageDescription;
    private String messageBody;
    private String messageError;

    // Create Stage Object To Get Window
    Stage stage;

    /**
     * Button that allows the user to verify their username and password and gain entry into the Home Page.
     *
     * @param event
     * @throws SQLException
     * @throws Exception
     * @see SQLException
     * @see Exception
     */
    @FXML
    private void clickToLogin(ActionEvent event) throws SQLException, Exception {
        // Load language resources and current appointments
        ResourceBundle rb = ResourceBundle.getBundle("PetAppointments/lang/login", Locale.getDefault());
        ObservableList<Appointment> getAppointments = AppointmentAccess.getAppointments();

        ObservableList<Appointment> apptList = AppointmentAccess.getAppointments();
        ObservableList<String> apptAdded = FXCollections.observableArrayList();

        //Lambda #1 Fill list with each appointments start time.
        apptList.forEach(Appointment -> apptAdded.add(Appointment.getStart().toString()));

        int appointmentIDNum = 0;
        int groomerID;
        boolean appointmentsUnderHour = false;

        LocalDateTime nowMinus60Min = LocalDateTime.now().minusMinutes(60);
        LocalDateTime nowPlus60Min = LocalDateTime.now().plusMinutes(60);
        LocalDateTime now;
        LocalDateTime appointmentTime = null;

        // Validate user login
        String usernameInput = loginScreenUsername.getText();
        String passwordInput = loginScreenPassword.getText();
        groomerID = GroomerAccess.validateUser(usernameInput, passwordInput);

        //Lambda #2 Fill the groomer list with location information based on their login location.
        ObservableList<GroomerAccess> groomerList = GroomerAccess.getGroomers();
        ObservableList<String> allGroomerLocations = FXCollections.observableArrayList();

        groomerList.forEach(Groomer -> allGroomerLocations.add(Groomer.getGroomerName()));

        //lambda #3 Add all appointment data to use in for loop.
        apptList.forEach(getAppointments::add);

        // Switch form to main menu screen if user is the groomer (or admin)
        if (groomerID > 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PetAppointments/forms/homePage.fxml/"));
            Parent root = loader.load();
            stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Get appointments
            for (Appointment appointment : apptList) {
                // Set datetime to the appointments starting time.
                now = appointment.getStart();

                // Check for appointments within the hour
                if ((now.isAfter(nowMinus60Min) || now.isEqual(nowMinus60Min)) && (now.isBefore(nowPlus60Min) || (now.isEqual(nowPlus60Min)))) {
                    //Get appointmentID
                    appointmentIDNum = appointment.getAppointmentID();
                    appointmentTime = now;
                    appointmentsUnderHour = true;
                }
            }

            // Alert user they have an appointment within the hour
            if (appointmentsUnderHour != false) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Welcome " + usernameInput + "! Appointments within 60 minutes: Appointment #" + appointmentIDNum + " with a start time of: " + appointmentTime);
                Optional<ButtonType> confirmation = alert.showAndWait();
            } else {
                // Alert user in the United States that they do not have an appointment within the next hour
                if (Locale.getDefault() == Locale.US) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText(rb.getString("NoAppt"));
                    Optional<ButtonType> confirmation = alert.showAndWait();
                } else { // Alert users in Mexico that they do not have an appointment within the next hour
                    //System.out.println("MX");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText(rb.getString("NoAppt"));
                    Optional<ButtonType> confirmation = alert.showAndWait();
                }
            }
            // Error alert if users password is incorrect
        } else if (groomerID < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle(rb.getString("Error"));
            alert.setContentText(rb.getString("messageError"));
            alert.show();
        }
    }

    /**
     * Button allows the user to exit the application.
     *
     * @param event
     */
    @FXML
    private void closeApp(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Start App by checking users Default Locale. Set confirmation box to users' language. US-en OR MX-es
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);

            rb = ResourceBundle.getBundle("PetAppointments/lang/login", locale);

            messageName = rb.getString("messageName");
            messageDescription = rb.getString("messageDescription");
            messageBody = rb.getString("messageBody");
            messageError = rb.getString("messageError");

        } catch (MissingResourceException e) {
            System.out.println("Resource file cannot be found: " + e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}