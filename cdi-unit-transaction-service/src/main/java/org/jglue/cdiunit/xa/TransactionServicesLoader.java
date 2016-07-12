package org.jglue.cdiunit.xa;

import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jglue.cdiunit.services.WeldServicesLoader;

/**
 * Created by ungerts on 12.07.16.
 */
public class TransactionServicesLoader implements WeldServicesLoader {

    @Override
    public void load(ServiceRegistry serviceRegistry) {
        serviceRegistry.add(TransactionServicesImpl.class, new TransactionServicesImpl());
    }
}
