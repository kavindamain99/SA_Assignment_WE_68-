package doc24patientpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	
	ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Service started");
		IPatientService patientService = new PatientService();
		serviceRegistration = bundleContext.registerService(IPatientService.class.getName(), patientService, null);
		
		//
		//patientService.displayDoctors("Cardiology");
		//patientService.createAppointment("Mulitha", "D01");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Service Terminated");
	}

}
