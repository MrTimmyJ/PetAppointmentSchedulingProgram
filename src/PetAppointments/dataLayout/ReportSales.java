package PetAppointments.dataLayout;

/**
 * Report Amount Class
 */
public class ReportSales { // Total Groomer Sales per groomer this year


    public String groomerName;
    public int totalAppointments;

    /**
     * Report Sales Constructor
     * @param groomerName
     * @param totalAppointments
     */
    public ReportSales(String groomerName, int totalAppointments) {
        this.groomerName = groomerName;
        this.totalAppointments = totalAppointments;
    }

    public String getGroomerName() {
        return groomerName;
    }

    public int getSalesTotal() {
        return totalAppointments;
    }
}
