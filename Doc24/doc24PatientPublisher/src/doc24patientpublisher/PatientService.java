package doc24patientpublisher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientService implements IPatientService{
	

	private static String doctors = "C:\\Users\\MSI\\Documents\\DataFiles\\Doctors.txt";
	private static String appointments = "C:\\Users\\MSI\\Documents\\DataFiles\\Appointments.txt";
	private static String appointmentCount = "C:\\Users\\MSI\\Documents\\DataFiles\\AppointmentCount.txt";
	
	public PatientService() {
		System.out.println("Patient service started");
		
//		try {
//			File file = new File(doctors);
//			if(!file.exists()) {
//				file.createNewFile();
//				System.out.println("New Doctors file created");
//			}
//		}
//		catch(IOException e) {
//			System.out.println("Error occurred");
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void displayDoctors(String field) {
		
		try {
			String line;
			String[] lineSplit;
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(doctors));
			
			System.out.println(field + " Doctors");
			
			System.out.println();
			
			System.out.println("---------------------------------------------------");
			System.out.println("ID        Name                     Hospital");
			System.out.println("---------------------------------------------------");
			
			while((line = bufferedReader.readLine()) != null) {
				lineSplit = line.split(",");
				if(lineSplit[2].equals(field)) {
					String row = String.format("%-10s%-25s%s", lineSplit[0], lineSplit[1], lineSplit[3]);
					System.out.println(row);
				}
			}
			
			bufferedReader.close();
			
			System.out.println("---------------------------------------------------");
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void createAppointment(String patientName, String doctorID) {
		String appointmentID = "D241000", appointmentTime = "21:30 - 22:00", doctorName = "";
		int appointmentNumber = 0;
		boolean found = false;
		
		try {
			String line;
			String[] lineSplit;
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(appointmentCount));
			
			while((line = bufferedReader.readLine()) != null) {
				lineSplit = line.split(",");
				if(lineSplit[0].equals(doctorID)) {
					found = true;
					appointmentNumber = Integer.parseInt(lineSplit[1]);
				}
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		if(found) {
			if(appointmentNumber + 1 > 6) {
				System.out.println("All the appointments are taken");
			}
			else {
				appointmentTime = appointmentTimeGenerator(appointmentNumber + 1);
				appointmentID = appointmentIDGenerator();
				doctorName = getDoctorName(doctorID);
				
				// creating new appointment and saving it in the appointments file
				try {
					File file = new File(appointments);
					if(!file.exists()) {
						file.createNewFile();
						System.out.println("New Appointments file created");
					}
				}
				catch(IOException e) {
					System.out.println("Error occurred");
					e.printStackTrace();
				}
				
				try {
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(appointments,true));
					bufferedWriter.write(appointmentID + "," + doctorName + "," +patientName + "," + appointmentTime);
					bufferedWriter.flush();
					bufferedWriter.newLine();
					bufferedWriter.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				updateAppointmentCount(doctorID);
				
				printAppointmentDetails(appointmentID);
			}
		}
		else {
			System.out.println("Invalid Doctor ID");
		}
	}
	
	@Override
	public void printAppointmentDetails(String appointmentID) {
		String line;
		String[] lineSplit;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(appointments));
			while((line = bufferedReader.readLine()) != null) {
				lineSplit = line.split(",");
				if(appointmentID.equals(lineSplit[0])) {
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime current = LocalDateTime.now();
					
					System.out.println("---------------------------------------------");
					System.out.println("APPOINTMENT DETAILS");
					System.out.println("---------------------------------------------");
					System.out.println("Name : " + lineSplit[2]);
					System.out.println("Dr.  : " + lineSplit[1]);
					System.out.println("Appointment ID   : " + appointmentID);
					System.out.println("Appointment time : " + lineSplit[3]);
					System.out.println("Appointment placed on : " + dateTimeFormatter.format(current));
					System.out.println("\n-------------------------------------------");
				}
			}
			
			bufferedReader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private String appointmentTimeGenerator(int appointmentNumber) {
		String time = "";
		int prefix;
		if(appointmentNumber % 2 != 0) {
			prefix = (appointmentNumber / 2) + 1;
			time += (prefix + 7) + ":00 - " + (prefix + 7) + ":30";
		}
		else {
			prefix = appointmentNumber / 2;
			time += (prefix + 7) + ":30 - " + (prefix + 8) + ":00";
		}
		return time;
	}
	
	private String appointmentIDGenerator() {
		String appointmentID = "D24";
		String line;
		String lastLine = "D240000";
		String[] lineSplit;
		int lastAppointmentIDNo = 1;
		try {
			File appointmentsFile = new File(appointments);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(appointmentsFile));
			if(appointmentsFile.length() == 0) {
				appointmentID += String.format("%04d", lastAppointmentIDNo);
			}
			else {
				while((line = bufferedReader.readLine()) != null) {
					lastLine = line;
				}
				lineSplit = lastLine.split(",");
				lastAppointmentIDNo = Integer.parseInt(lineSplit[0].substring(3));
				appointmentID += String.format("%04d", (lastAppointmentIDNo + 1));
			}
			
			bufferedReader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return appointmentID;
	}
	
	private String getDoctorName(String doctorID) {
		String line, doctorName = "";
		String[] lineSplit;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(doctors));
			
			while((line = bufferedReader.readLine()) != null) {
				lineSplit = line.split(",");
				if(lineSplit[0].equals(doctorID)) {
					doctorName = lineSplit[1];
				}
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return doctorName;
	}
	
	private void updateAppointmentCount(String doctorID) {
		File tempFile = new File("C:\\Users\\user\\Documents\\Eclipse-Workspace\\appointment_temp.txt");
		File appointmentCountFile = new File("C:\\Users\\user\\Documents\\Eclipse-Workspace\\AppointmentCount.txt");
		
		String line;
		String[] lineSplit;
		int previousCount = 0;
		boolean found = false;
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			BufferedReader bufferedReader = new BufferedReader(new FileReader(appointmentCountFile));
			
			while((line = bufferedReader.readLine()) != null) {
				lineSplit = line.split(",");
				if(lineSplit[0].equals(doctorID)) {
					found = true;
					previousCount = Integer.parseInt(lineSplit[1]);
					bufferedWriter.write(doctorID + "," + (previousCount + 1));
					bufferedWriter.flush();
					bufferedWriter.newLine();
					continue;
				}
				bufferedWriter.write(line + "\n");
			}
			
			if(!found) {
				bufferedWriter.write(doctorID + "," + (previousCount + 1));
			}
			
			bufferedReader.close();
			bufferedWriter.close();
			
			appointmentCountFile.delete();
			
			tempFile.renameTo(appointmentCountFile);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
