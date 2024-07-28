package com.adamoubello;

import com.adamoubello.definition.Greeter;
import org.osgi.framework.*;

public class Client implements BundleActivator, ServiceListener {

    private BundleContext ctx;
    private ServiceReference serviceReference;

    public void start(BundleContext ctx) {
        System.out.println("Starting Client...");
        this.ctx = ctx;
        try {
            ctx.addServiceListener(this
                    , "(objectclass=" + Greeter.class.getName() + ")");
        } catch (InvalidSyntaxException ise) {
            ise.printStackTrace();
        }
    }

    public void stop(BundleContext bundleContext) {
        System.out.println("Stopping Client...");
        if (serviceReference != null) {
            ctx.ungetService(serviceReference);
        }
        this.ctx = null;
    }

    public void serviceChanged(ServiceEvent serviceEvent) {
        int type = serviceEvent.getType();
        Greeter service = (Greeter) (ctx.getService(serviceReference));
        switch (type) {
        case (ServiceEvent.REGISTERED):
            System.out.println("Notification of service registered.");
            serviceReference = serviceEvent.getServiceReference();
            //Greeter service = (Greeter) (ctx.getService(serviceReference));
            try {
                service.start(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(service.sayHiTo("John"));
            break;
        case (ServiceEvent.UNREGISTERING):
            System.out.println("Notification of service unregistered.");
            ctx.ungetService(serviceEvent.getServiceReference());
            try {
                service.stop(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            break;
        default:
            break;
        }
    }
}
