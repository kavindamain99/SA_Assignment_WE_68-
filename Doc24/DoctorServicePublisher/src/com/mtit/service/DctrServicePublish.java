package com.mtit.service;

import java.util.List;

public interface DctrServicePublish {
	
	public void displayPatientsList(String dctId);
	
	public void createPrescription(String appointmentId, String doctorId, List<String> prescription);

	public void printPrescriptions(String doctorID);
}
