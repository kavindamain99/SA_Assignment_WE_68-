package com.mtit.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DctrServicePublishImpl implements DctrServicePublish{
	
	private static String filepath = "C:\\Users\\Madhawa\\OneDrive\\Desktop\\java code\\DoctorServicePublisher\\Appointments.txt";
    private static String doctors = "C:\\Users\\Madhawa\\OneDrive\\Desktop\\java code\\DoctorServicePublisher\\Doctors.txt";
    private static String prescriptions = "C:\\Users\\Madhawa\\OneDrive\\Desktop\\java code\\DoctorServicePublisher\\prescription.txt";
    
    
	public void displayPatientsList(String dctrId) {
		
		try {
			String line;
			String[] seperated_row;
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
			
			System.out.println(dctrId + " Patients List");
			System.out.println("\n");
			System.out.println("Appointment ID          Doctor Name              Patient Name        Appointment Time");
			
			while((line = bufferedReader.readLine()) != null){
				seperated_row = line.split(",");
				if(seperated_row[0].equals(dctrId)) {
					String row = String.format("%-22s%-25s%-20s%s", seperated_row[1], seperated_row[2], seperated_row[3], seperated_row[4]);
					System.out.println(row);
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
	}


	@Override
	public void createPrescription(String appointmentId, String doctorID, List<String> prescription) {
		
		try {
			File file = new File(prescriptions);
			if(!file.exists()) {
				file.createNewFile();
				System.out.println("Prescription File Created!");
			}
		}
		catch(IOException e) {
			System.out.println("Error occurred");
			e.printStackTrace();
		}
		
			// Creates a FileWriter
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(prescriptions, true));
			
			String p = "";
			if(prescription.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for(String str : prescription) {
					sb.append(str).append(",");
				}
				p = sb.deleteCharAt(sb.length() - 1).toString();
			}
			
			bw.write(doctorID + "," + appointmentId + "," + p);
			bw.flush();
			bw.newLine();
			bw.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		printPrescriptions(doctorID);
		removeAppointment(appointmentId);

	}


	@Override
	public void printPrescriptions(String doctorID) {
		String prescription;
		String[] prescriptionSplit;
		try {
			BufferedReader br = new BufferedReader(new FileReader(prescriptions));
			
			while((prescription = br.readLine()) != null) {
				prescriptionSplit = prescription.split(",");
				if(prescriptionSplit[0].equals(doctorID)) {
					System.out.println("Appointment ID : " + prescriptionSplit[0]);
					System.out.println("Prescription : " + prescriptionSplit[2]);
					for(int i = 3; i < prescriptionSplit.length; i++) {
						String str = String.format("%-15s%s", "", prescriptionSplit[i]);
						System.out.println(str);
					}
					System.out.println();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private void removeAppointment(String appointmentID) {
    	String appointment;
    	String[] appointmentSplit;
    	
    	File tempFile = new File("C:\\Users\\Madhawa\\OneDrive\\Desktop\\java code\\DoctorServicePublisher\\temp_app.txt");
    	File appointments = new File("C:\\Users\\Madhawa\\OneDrive\\Desktop\\java code\\DoctorServicePublisher\\Appointments.txt");
    	
    	try {
    		BufferedReader br = new BufferedReader(new FileReader(appointments));
    		BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    		
    		while((appointment = br.readLine()) != null) {
    			appointmentSplit = appointment.split(",");
    			if(appointmentSplit[1].equals(appointmentID)) {
    				continue;
    			}
    			else {
    				bw.write(appointmentSplit[0] + "," + appointmentSplit[1] + "," + appointmentSplit[2] + "," + appointmentSplit[3] + "," + appointmentSplit[4]);
    				bw.flush();
    				bw.newLine();
    			}
    		}
    		
    		br.close();
    		bw.close();
    		
    		appointments.delete();
    		
    		tempFile.renameTo(appointments);
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
    }
}
