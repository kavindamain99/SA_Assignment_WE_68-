package doc24managerconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import doc24managerproducer.ManagerServices;
import doc24managersearchproducer.ManagerSearchServices;
import doc24menuproducer.Menu;

public class Activator implements BundleActivator {

	private static BundleContext context;
	//Producer References
	ServiceReference serviceReference,serviceReferenceSearch,serviceReferenceMenu;
	
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Manager consumer started");

		//attach plugins
		serviceReference = bundleContext.getServiceReference(ManagerServices.class.getName());
		ManagerServices managerService = (ManagerServices) bundleContext.getService(serviceReference);
		
		serviceReferenceSearch = bundleContext.getServiceReference(ManagerSearchServices.class.getName());
		ManagerSearchServices managerSearchService = (ManagerSearchServices) bundleContext.getService(serviceReferenceSearch);
		
		serviceReferenceMenu = bundleContext.getServiceReference(Menu.class.getName());
		Menu menu = (Menu) bundleContext.getService(serviceReferenceMenu);


		//Subscriber input Section
		Scanner s = new Scanner(System.in);

		String dId;
		String dName;
		String dSpecialties;
		boolean isContinue = true;
		Character newField;
		int listNum = 0;
		boolean isList = true;
		Character back;
		String dHospital;

		while (isList = true) {
			//call menu method
			menu.printMainMenu();
			listNum = s.nextInt();

			System.out.println("========================================================");

			//call producer services
			switch (listNum) {
			case 1:

				s.nextLine();
				while (isContinue == true) {
					System.out.println("Please Enter Doctor Id : ");
					dId = s.nextLine();
					System.out.println("Please Enter Doctor Name : ");
					dName = s.nextLine();
					System.out.println("Please Enter Doctor specialties : ");
					dSpecialties = s.nextLine();
					System.out.println("Please Enter Doctor Hospital : ");
					dHospital = s.nextLine();
					System.out.println("Insert New Doctor 'Y' Close 'N' : ");
					newField = s.next().charAt(0);
					s.nextLine();

					if (newField == 'N') {
						isContinue = false;
						managerService.addDocter(dId, dName, dSpecialties, dHospital);
						break;
					} else if (newField == 'Y') {
						managerService.addDocter(dId, dName, dSpecialties, dHospital);
						isContinue = true;
						System.out.println("=======================================");
					} else {
						menu.printInvalid();
						break;
					}

				}
				break;
			case 2:
				managerService.doctorList();
				break;
			case 3:
				String dID;
				System.out.println("\t\t Delete Doctor Record\n");

				System.out.println("Enter the Doctor ID: ");
				s.nextLine();
				dID = s.nextLine();
				managerService.deleteDoctor(dID);

				break;
			case 4:
				System.out.println("Enter the Doctor Name or ID: ");
				s.nextLine();
				dID = s.nextLine();
				managerSearchService.searchDoctor(dID);
				
				
				break;
			default:
				menu.printInvalid();

			}

			System.out.println("\nBack to Admin Board (Back -> 'Y' Terminate -> any key) ");
			back = s.next().charAt(0);

			if (back == 'Y' || back == 'y') {
				isList = true;
			} else {
				isList = false;
				
				System.out.println("\t\tADMINISTRATIVE PROCEEDING TERMINATED\n\n\n");
				break;
			}

		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		//stop the consumer
		bundleContext.ungetService(serviceReference);
		System.out.println("Manager consumer Stopped");
		
	}

}
