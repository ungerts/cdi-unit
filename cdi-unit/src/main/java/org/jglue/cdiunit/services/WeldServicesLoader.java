package org.jglue.cdiunit.services;

import org.jboss.weld.bootstrap.api.ServiceRegistry;

/**
 * Created by ungerts on 12.07.16.
 */
public interface WeldServicesLoader {

    public void load(ServiceRegistry serviceRegistry);
}

