package PetAppointments.dataLayout;

/**
 * Groomer Class
 */
public class Groomer {
    private int groomerID;
    private String groomerName;
    private String groomerPassword;
    private boolean groomerStatus;

    /**
     * Constructor for Groomer Class.
     *
     * @param groomerID       unique ID to specify groomer.
     * @param groomerName     groomers user name.
     * @param groomerPassword groomers login password.
     * @param groomerStatus   if groomer is active.
     */
    public Groomer(int groomerID, String groomerName, String groomerPassword, boolean groomerStatus) {
        this.groomerID = groomerID;
        this.groomerName = groomerName;
        this.groomerPassword = groomerPassword;
        this.groomerStatus = groomerStatus;
    }

    /**
     * Null Groomer
     */
    public Groomer() {
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
     * Get Groomer Name.
     *
     * @return groomerName
     */
    public String getGroomerName() {
        return groomerName;
    }

    /**
     * Get Groomer Password.
     *
     * @return groomerPassword
     */
    public String getGroomerPassword() {
        return groomerPassword;
    }

    /**
     * Get Groomer Status.
     *
     * @return groomerStatus
     */
    public boolean getGroomerStatus() {
        return groomerStatus;
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
     * Set Groomer Name.
     *
     * @param name Groomer's Username
     */
    public void setCustomerName(String name) {
        this.groomerName = name;
    }

    /**
     * Set Groomer Password.
     *
     * @param password Groomer's password
     */
    public void setGroomerPassword(String password) {
        this.groomerPassword = password;
    }

    /**
     * Set Groomer Status.
     *
     * @param groomerStatus
     */
    public void setGroomerStatus(boolean groomerStatus) {
        this.groomerStatus = groomerStatus;
    }
}