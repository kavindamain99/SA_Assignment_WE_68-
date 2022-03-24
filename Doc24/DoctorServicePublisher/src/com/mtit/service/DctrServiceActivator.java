package com.mtit.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class DctrServiceActivator implements BundleActivator {
    
    	 
	
	ServiceRegistration publishServiceRegistration;

	public void start(BundleContext context) throws Exception {
		
		System.out.println("Publisher start");
		DctrServicePublish publisherService = new DctrServicePublishImpl();
		publishServiceRegistration = context.registerService(DctrServicePublish.class.getName(), publisherService, null );
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Publisher Stop");
		publishServiceRegistration.unregister();
	}
	

}
