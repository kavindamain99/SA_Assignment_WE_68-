package osgi_servicesubscriber;

import com.mtit.service.DctrServicePublish;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class DctrServiceActivator implements BundleActivator {
	
    ServiceReference serviceReference;
   
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Start subscriber Service");
		serviceReference = context.getServiceReference(DctrServicePublish.class.getName());
		DctrServicePublish servicePublish = (DctrServicePublish)context.getService(serviceReference);
		
		String dctrId;
		boolean end = false;
		boolean end2 = false;
		Scanner s1 = new Scanner(System.in);
		char input;
		String appointmentId;
		List<String> prescription = new ArrayList<>();
		String line;
		//String prescription;
		
		while(!end) {
			System.out.println("Enter Your Doctor ID : ");
			dctrId = s1.nextLine();
			
			System.out.println("\n");
			servicePublish.displayPatientsList(dctrId);
			
			while(!end2) {
				System.out.println("Enter Appointment ID : ");
				appointmentId = s1.nextLine();
				
				//prescription[0] = s1.nextLine();
				
				System.out.println("Enter Prescription : ");
				
				while(s1.hasNextLine()) {
					line = s1.nextLine();
					if(line.isEmpty()) {
						break;
					}
					prescription.add(line);
				}
				
				//prescription = s1.nextLine();
				
				//System.out.println("THJFHT");
				
				//s1.close();
				
				servicePublish.createPrescription(appointmentId, dctrId, prescription);
				
				System.out.println("\n\nDo you want to resubmit?  ");
				input = s1.next().charAt(0);
				if(input == 'n' || input == 'N') {
					end2 = true;
				}
				
			}

			System.out.println("\n\nDo you want to resubmit?  ");
			input = s1.next().charAt(0);
			if(input == 'n' || input == 'N') {
				end = true;
			}
			
		}
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye");
		context.ungetService(serviceReference);
	}

}
