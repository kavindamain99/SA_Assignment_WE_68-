package doc24patientsubscriber;

import doc24patientpublisher.IPatientService;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	ServiceReference serviceReference;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		System.out.println("Subscriber Started");
		
		serviceReference = bundleContext.getServiceReference(IPatientService.class.getName());
		IPatientService patientService = (IPatientService) bundleContext.getService(serviceReference);
		
		String patientName, field = "", doctorID;
		int selection;
		boolean terminate = false;
		char input;
		
		Scanner sc = new Scanner(System.in);
		
		while(!terminate) {
			System.out.println("Patient name : ");
			patientName = sc.nextLine();
			
			System.out.println();
			
			System.out.println("Please select a type from the list");
			System.out.println();
			
			System.out.println("1 - Cardiology");
			System.out.println("2 - Dermatology");
			System.out.println("3 - Radiology");
			System.out.println("4 - Neurology");
			System.out.println("5 - Psychiatry");
			
			System.out.println();
			
			System.out.println("Enter Selection : ");
			selection = sc.nextInt();
			
			System.out.println();
			
			switch(selection) {
			case 1:
				field = "Cardiology";
				break;
			case 2:
				field = "Dermatology";
				break;
			case 3:
				field = "Radiology";
				break;
			case 4:
				field = "Neurology";
				break;
			case 5:
				field = "Psychiatry";
				break;
			default:
				System.out.println("Selection is invalid");
			}
			
			patientService.displayDoctors(field);
			
			System.out.println();
			
			System.out.println("Enter Doctor ID : ");
			sc.nextLine();
			doctorID = sc.nextLine();
			
			System.out.println();
			
			patientService.createAppointment(patientName, doctorID);
			
			System.out.println();
			
			System.out.println("Terminate service? (Yes - y, No - n) :");
			input = sc.next().charAt(0);
			
			System.out.println();
			
			if(input == 'y' || input == 'Y') {
				terminate = true;
			}
			
			sc.nextLine();
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Subscriber stopped");
		Activator.context = null;
	}

}
