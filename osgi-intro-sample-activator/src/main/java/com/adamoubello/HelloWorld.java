package com.adamoubello;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HelloWorld implements BundleActivator {

    public void start(BundleContext ctx) {
        System.out.println("Hello Bello.");
    }

    public void stop(BundleContext bundleContext) {
        System.out.println("Goodbye Bello.");
    }

}