package org.jglue.cdiunit;

import javax.naming.InitialContext;

/**
 * Created by ungerts on 18.05.16.
 */
public interface ContextFactoryLoader {

    public InitialContext loadContextFactory() throws Exception;

    public void registerObject(String jndiName, Object object) throws Exception;

}
