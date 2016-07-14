package org.jglue.cdiunit.xa;

import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.transaction.spi.TransactionServices;
import org.jglue.cdiunit.services.WeldServicesLoader;

/**
 * Created by ungerts on 12.07.16.
 */
public class TransactionServicesLoader implements WeldServicesLoader {

    @Override
    public void load(ServiceRegistry serviceRegistry) {
        serviceRegistry.add(TransactionServices.class, new TransactionServicesImpl());
    }
}
