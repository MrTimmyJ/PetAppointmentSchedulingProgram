package PetAppointments.dataLayout;

import java.time.LocalDate;

/**
 * Pet Class
 */
public class Pet {
    private int petID;
    private int petTypeID;
    private String petName;
    private String ownerName;
    private int petAddressID;
    private LocalDate petBirthdate;
    private boolean petStatus;

    /**
     * Constructor for Pets attending their appointments.
     *
     * @param petID        unique pet ID.
     * @param petTypeID    ID to determine the pet type.
     * @param petName      name of the pet.
     * @param ownerName    name of the pets owner.
     * @param petAddressID address ID of the pet.
     * @param petBirthdate birthday of the pet.
     * @param petStatus    if the pet is active.
     */
    public Pet(int petID, int petTypeID, String petName, String ownerName, int petAddressID, LocalDate petBirthdate, boolean petStatus) {
        this.petID = petID;
        this.petTypeID = petTypeID;
        this.petName = petName;
        this.ownerName = ownerName;
        this.petAddressID = petAddressID;
        this.petBirthdate = petBirthdate;
        this.petStatus = petStatus;
    }

    /**
     * Null Pet.
     */
    public Pet() {
    }

    /**
     * Constructor using PetID.
     *
     * @param petID the ID of the specified pet.
     */
    public Pet(int petID) {
        this.petID = petID;
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
     * Get Pet Type ID.
     *
     * @return petTypeID
     */
    public int getPetTypeID() {
        return petTypeID;
    }

    /**
     * Get Pet Name.
     *
     * @return petName
     */
    public String getPetName() {
        return petName;
    }

    /**
     * Get Pets Owner Name.
     *
     * @return ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Get Pet's Address ID.
     *
     * @return addressID
     */
    public int getPetAddressID() {
        return petAddressID;
    }

    /**
     * Get Pets Birth date.
     *
     * @return petBirthday
     */
    public LocalDate getPetBirthdate() {
        return petBirthdate;
    }

    /**
     * Get Pet Status.
     *
     * @return petStatus
     */
    public boolean getPetStatus() {
        return petStatus;
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
     * Set Pet Type ID.
     *
     * @param petTypeID
     */
    public void setPetTypeID(int petTypeID) {
        this.petTypeID = petTypeID;
    }

    /**
     * Set Pet Name.
     *
     * @param petName
     */
    public void setPetName(String petName) {
        this.petName = petName;
    }

    /**
     * Set Pet Owner Name.
     *
     * @param ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Set Pet Address ID.
     *
     * @param petAddressID
     */
    public void setPetAddressID(int petAddressID) {
        this.petAddressID = petAddressID;
    }

    /**
     * Set Pet Birthdate.
     *
     * @param petBirthdate
     */
    public void setPetBirthdate(LocalDate petBirthdate) {
        this.petBirthdate = petBirthdate;
    }

    /**
     * Set Pet Status.
     *
     * @param petStatus
     */
    public void setPetStatus(boolean petStatus) {
        this.petStatus = petStatus;
    }
}
