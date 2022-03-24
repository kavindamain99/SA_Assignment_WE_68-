package doc24paymentconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;



import doc24paymentproducer.PaymentManager;

public class Activator implements BundleActivator {
	ServiceReference serviceReference;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("consumer started");

		serviceReference = bundleContext.getServiceReference(PaymentManager.class.getName());
		PaymentManager paymentManager = (PaymentManager) bundleContext.getService(serviceReference);
		
		Scanner sc = new Scanner(System.in);
		
		String payId = null;
		String dId;
		String pId;
		String desc = null;
		double amount;
		int number;
		
		System.out.println(" ----------------------------------------------------------------------------   ");
		System.out.println("Payment Menu\n");
		System.out.println("New payment  01 ");
		System.out.println("View payment 02 ");
		System.out.println("Print a bill 03 \n");
		
		System.out.println("Enter menu number: ");
		number = sc.nextInt();
		sc.nextLine();
		if(number == 01) {
			System.out.println("Enter payment ID : ");
			payId = sc.nextLine();
			System.out.println("Enter doctor ID : ");
			dId = sc.nextLine();
			System.out.println("Enter patient ID : ");
			pId = sc.nextLine();
			System.out.println("Enter Description : ");
			desc = sc.nextLine();
			System.out.println("Enter consultants fees : ");
			amount = sc.nextDouble();
			
			paymentManager.newPayment(payId, dId, pId,desc, amount);
			
		}else if(number == 02) {
			paymentManager.viewPayment();
			
		}else if(number == 03) {
			
			String pId1;
    		
    		System.out.println("Enter the pateint id: ");
    		sc.nextLine();
    		pId1 =  sc.nextLine();

    		paymentManager.billPayment(pId1);
			
		}else {
			System.out.println("Invalid number");
			
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("consumer stopped");
	}

}
