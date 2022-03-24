package doc24patientpublisher;

public interface IPatientService {
	public void displayDoctors(String field);
	public void createAppointment(String patientName, String doctorID);
	public void printAppointmentDetails(String receiptID);
}
