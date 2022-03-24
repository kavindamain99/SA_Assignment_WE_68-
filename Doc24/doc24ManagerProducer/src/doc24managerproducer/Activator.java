package doc24managerproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	
	//register the producer service
	ServiceRegistration serviceRegistration;
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Doc24 Manager Service started");
		ManagerServices managerServices = new ManagerServiceImpl();
		serviceRegistration =bundleContext.registerService(ManagerServices.class.getName(), managerServices, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		serviceRegistration.unregister();
		System.out.println("Doc24 Manager Service stopped");
	}

}
