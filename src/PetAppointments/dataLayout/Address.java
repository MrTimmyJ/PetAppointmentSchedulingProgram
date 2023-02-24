package PetAppointments.dataLayout;

/**
 * Address Class
 */
public class Address {
    private int addressID;
    private String addressLineOne;
    private String addressLineTwo;
    private int cityID;
    private String postalCode;
    private String phone;

    /**
     * Constructor for Pet Address's.
     *
     * @param addressID      unique address ID.
     * @param addressLineOne first line of the address.
     * @param addressLineTwo second line of the address.
     * @param cityID         specified city ID determining the city.
     * @param postalCode     specified postal code determining delivery location.
     * @param phone          phone number of pet owner.
     */
    public Address(int addressID, String addressLineOne, String addressLineTwo, int cityID, String postalCode, String phone) {
        this.addressID = addressID;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    /**
     * Null Address
     */
    public Address() {
    }

    /**
     * Get Address ID.
     *
     * @return addressID
     */
    public int getAddressID() {
        return addressID;
    }

    /**
     * Get Address Line One.
     *
     * @return addressLineOne
     */
    public String getAddressLineOne() {
        return addressLineOne;
    }

    /**
     * Get Address Line Two.
     *
     * @return addressLineTwo
     */
    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    /**
     * Get City ID.
     *
     * @return cityID
     */
    public int getCityID() {
        return cityID;
    }

    /**
     * Get Postal Code.
     *
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Get Phone Number.
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set Address ID.
     *
     * @param addressID
     */
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    /**
     * Set Address.
     *
     * @param addressLineOne
     * @param addressLineTwo
     */
    public void setAddressID(String addressLineOne, String addressLineTwo) {
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
    }

    /**
     * Set City ID.
     *
     * @param cityID
     */
    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    /**
     * Set Postal Code.
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Set Phone.
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}