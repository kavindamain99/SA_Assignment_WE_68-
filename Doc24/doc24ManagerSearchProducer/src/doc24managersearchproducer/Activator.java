package doc24managersearchproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
public class Activator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration serviceRegistration;
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	
		System.out.println("Doc24 Search Service started");
		ManagerSearchServices managerServices = new ManagerSearchServiceImpl();
		serviceRegistration =bundleContext.registerService(ManagerSearchServices.class.getName(), managerServices, null);}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Doc24 Search Service stopped");
	}

}
