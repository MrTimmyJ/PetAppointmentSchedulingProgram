package PetAppointments.dataLayout;

import java.time.LocalDateTime;

/**
 * Appointment Class
 */
public class Appointment {
    private int appointmentID;
    private int petID;
    private int groomerID;
    private int serviceTypeID;
    private String appointmentDescription;
    public LocalDateTime appointmentStart;
    public LocalDateTime appointmentEnd;
    public double serviceCost;
    public String serviceLocation;
    public boolean appointmentStatus;

    /**
     * Constructor for Pet Appointments.
     *
     * @param appointmentID          unique appointment ID.
     * @param petID                  unique pet ID.
     * @param groomerID              unique groomer ID.
     * @param serviceTypeID          specified service type ID.
     * @param appointmentDescription more details defining the service.
     * @param appointmentStart       start time of the appointment.
     * @param appointmentEnd         end time of the appointment.
     * @param serviceCost            the amount of money charged for the appointment.
     * @param serviceLocation        the location of the appointment.
     * @param appointmentStatus      the appointment status determining if active.
     */
    public Appointment(int appointmentID, int petID, int groomerID, int serviceTypeID, String appointmentDescription, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, double serviceCost, String serviceLocation, Boolean appointmentStatus) {
        this.appointmentID = appointmentID;
        this.petID = petID;
        this.groomerID = groomerID;
        this.serviceTypeID = serviceTypeID;
        this.appointmentDescription = appointmentDescription;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.serviceCost = serviceCost;
        this.serviceLocation = serviceLocation;
        this.appointmentStatus = appointmentStatus;
    }

    /**
     * Constructor for a copied appointment.
     *
     * @param copy an appointment
     */
    public Appointment(Appointment copy) {
        this.appointmentID = copy.appointmentID;
        this.petID = copy.petID;
        this.groomerID = copy.groomerID;
        this.serviceTypeID = copy.serviceTypeID;
        this.appointmentDescription = copy.appointmentDescription;
        this.appointmentStart = copy.appointmentStart;
        this.appointmentEnd = copy.appointmentEnd;
        this.serviceCost = copy.serviceCost;
        this.serviceLocation = copy.serviceLocation;
        this.appointmentStatus = copy.appointmentStatus;
    }

    /**
     * Null Appointment.
     */
    public Appointment() {
    }

    /**
     * Get Appointment ID.
     *
     * @return appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Get Pet ID.
     *
     * @return petID
     */
    public int getPetID() {
        return petID;
    }

    /**
     * Get Groomer ID.
     *
     * @return groomerID
     */
    public int getGroomerID() {
        return groomerID;
    }

    /**
     * Get Service Type ID.
     *
     * @return serviceTypeID
     */
    public int getServiceTypeID() {
        return serviceTypeID;
    }

    /**
     * Get Appointment Description.
     *
     * @return appointmentDescription
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * Get local start date and time.
     *
     * @return start
     */
    public LocalDateTime getStart() {
        return appointmentStart;
    }

    /**
     * Get local end date and time.
     *
     * @return end
     */
    public LocalDateTime getEnd() {
        return appointmentEnd;
    }

    /**
     * Get Service Cost.
     *
     * @return serviceCost
     */
    public Double getServiceCost() {
        return serviceCost;
    }

    /**
     * Get Service Location.
     *
     * @return serviceLocation
     */
    public String getServiceLocation() {
        return serviceLocation;
    }

    /**
     * Get Appointment Status.
     *
     * @return appointmentStatus
     */
    public boolean getAppointmentStatus() {
        return appointmentStatus;
    }

    /**
     * Set Appointment ID.
     *
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Set Pet ID.
     *
     * @param petID
     */
    public void setPetID(int petID) {
        this.petID = petID;
    }

    /**
     * Set Groomer ID.
     *
     * @param groomerID
     */
    public void setGroomerID(int groomerID) {
        this.groomerID = groomerID;
    }

    /**
     * Set Service Type ID.
     *
     * @param serviceTypeID
     */
    public void setServiceTypeID(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    /**
     * Set Service Type Description.
     *
     * @param appointmentDescription
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Set Appointment Start Date Time.
     *
     * @param appointmentEnd
     */
    public void setAppointmentStart(LocalDateTime appointmentEnd) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * Set Appointment End Date Time.
     *
     * @param appointmentEnd
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * Set Service Cost.
     *
     * @param serviceCost
     */
    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    /**
     * Set Service Location.
     *
     * @param serviceLocation
     */
    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    /**
     * Set Appointment Status.
     *
     * @param appointmentStatus
     */
    public void setAppointmentStatus(boolean appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

//    @Override
//    public String toString() {
//        return String.format("%s { ID: %d, title: \"%s\", description: \"%s\", location: \"%s\", type: \"%s\", " +
//                        "start: %s, end: %s, createdBy: %s, lastUpdatedBy: %s, customerID: %d, userID: %d, contactID: %d}",
//                super.toString(),
//                this.appointmentID,
//                this.title,
//                this.description,
//                this.location,
//                this.type,
//                this.start,
//                this.end,
//                this.createdBy,
//                this.lastUpdatedBy,
//                this.customerID,
//                this.userID,
//                this.contactID);
//    }
}