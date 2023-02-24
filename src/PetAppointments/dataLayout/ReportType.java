package PetAppointments.dataLayout;

/**
 * Report Service Type Class
 */
public class ReportType { // Total Service Type Appointments

    public String serviceType;
    public int serviceCount;

    /**
     * Report Type Constructor
     * @param serviceType
     * @param serviceCount
     */
    public ReportType(String serviceType, int serviceCount) {
        this.serviceType = serviceType;
        this.serviceCount = serviceCount;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getTotalAppointments() {
        return serviceCount;
    }
}