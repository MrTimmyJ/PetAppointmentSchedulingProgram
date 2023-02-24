package PetAppointments.dataLayout;

import java.sql.SQLException;

/**
 * PetType Class
 */
public class PetType {
    private int petTypeID;
    private String petTypeDescription;

    /**
     * Constructor for Pet Type.
     *
     * @param petTypeID          specific ID to determine pet type.
     * @param petTypeDescription description of the pets type.
     */
    public PetType(int petTypeID, String petTypeDescription) {
        this.petTypeID = petTypeID;
        this.petTypeDescription = petTypeDescription;
    }

    /**
     * Null PetType
     */
    public PetType() {
    }

    /**
     * Constructor using petTypeID
     *
     * @param petTypeID ID for specified pet type
     */
    public PetType(int petTypeID) {
        this.petTypeID = petTypeID;
    }

    /**
     * Get Pet Type ID.
     *
     * @return petTypeID
     */
    public int getPetTypeID() throws SQLException {
        return petTypeID;
    }

    /**
     * Get Pet Type Description.
     *
     * @return petTypeDescription
     */
    public String getPetTypeDescription() {
        return petTypeDescription;
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
     * Set Pet Type Description.
     *
     * @param petTypeDescription
     */
    public void setPetTypeDescription(String petTypeDescription) {
        this.petTypeDescription = petTypeDescription;
    }
}