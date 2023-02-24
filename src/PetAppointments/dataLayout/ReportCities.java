package PetAppointments.dataLayout;

/**
 * Reports Class
 */
public class ReportCities { // Total appointments by city

    private String cityName;
    private int totalAppointments;

    /**
     * Reports Constructor
     * @param cityName
     * @param totalAppointments
     */
    public ReportCities(String cityName, int totalAppointments) {
        this.cityName = cityName;
        this.totalAppointments = totalAppointments;
    }

    /**
     * Get City Names.
     * @return serviceType
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Get Service Amount.
     * @return serviceAmount
     */
    public int getTotalAppointments() {
        return totalAppointments;
    }
}