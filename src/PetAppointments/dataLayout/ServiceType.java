package PetAppointments.dataLayout;

/**
 * ServiceType Class
 */
public class ServiceType {
    private int serviceTypeID;
    private String serviceTypeDescription;

    /**
     * Constructor for ServiceType.
     *
     * @param serviceTypeID          unique service type ID.
     * @param serviceTypeDescription service type description.
     */
    public ServiceType(int serviceTypeID, String serviceTypeDescription) {
        this.serviceTypeID = serviceTypeID;
        this.serviceTypeDescription = serviceTypeDescription;
    }

    /**
     * Null ServiceType
     */
    public ServiceType() { }

    /**
     * Constructor using serviceTypeID
     *
     * @param serviceTypeID ID for specified service
     */
    public ServiceType(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
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
     * Get Service Type Description.
     *
     * @return serviceTypeDescription
     */
    public String getServiceTypeDescription() {
        return serviceTypeDescription;
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
     * @param serviceTypeDescription
     */
    public void setServiceTypeDescription(String serviceTypeDescription) {
        this.serviceTypeDescription = serviceTypeDescription;
    }
}