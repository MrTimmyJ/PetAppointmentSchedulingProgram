package PetAppointments.dataLayout;

/**
 * Country Class
 */
public class Country {
    private int countryID;
    private String countryName;

    /**
     * Constructor for Country Class
     *
     * @param countryID   ID to determine country.
     * @param countryName specified country name.
     */
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * Null Country
     */
    public Country() {
    }

    /**
     * Get Country ID.
     *
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Get Country Name.
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Set Country ID.
     *
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Set Country Name.
     *
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
