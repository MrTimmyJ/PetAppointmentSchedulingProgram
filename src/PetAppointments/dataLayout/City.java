package PetAppointments.dataLayout;

/**
 * City Class
 */
public class City {
    private int cityID;
    private String cityName;
    private int country_ID;

    /**
     * Constructor for City Class.
     *
     * @param cityID     ID to determine city.
     * @param cityName   specified city name.
     * @param country_ID ID to determine country.
     */
    public City(int cityID, String cityName, int country_ID) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.country_ID = country_ID;
    }

    /**
     * Null City
     */
    public City() {
    }

    /**
     * Get city ID.
     *
     * @return cityID
     */
    public int getCityID() {
        return cityID;
    }

    /**
     * Get City Name.
     *
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Get Country ID.
     *
     * @return country_ID
     */
    public int getCountry_ID() {
        return country_ID;
    }

    /**
     * Set city ID.
     */
    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    /**
     * Set city Name.
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Set Country ID.
     */
    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }
}