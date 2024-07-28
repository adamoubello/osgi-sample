package com.adamoubello.definition;

import org.osgi.framework.BundleContext;

public interface Greeter {
    String sayHiTo(String name);

    void start(BundleContext context) throws Exception;

    void stop(BundleContext context) throws Exception;
}
