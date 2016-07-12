package org.jglue.cdiunit.xa;

import org.jboss.weld.transaction.spi.TransactionServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.*;

public class TransactionServicesImpl implements TransactionServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServicesImpl.class);

    private static UserTransaction getUserTransactionFromContext() {
        try {
            InitialContext ctx = new InitialContext();
            UserTransaction userTransaction = (UserTransaction) ctx.lookup("java:comp/UserTransaction");
            return userTransaction;
        } catch (NamingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private static TransactionManager getTransactionManagerFromContext() {
        try {
            InitialContext ctx = new InitialContext();
            TransactionManager transactionManager = (TransactionManager) ctx.lookup("java:/TransactionManager");
            return transactionManager;
        } catch (NamingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public void registerSynchronization(Synchronization synchronizedObserver) {
        TransactionManager transactionManager = getTransactionManagerFromContext();
        if (transactionManager != null) {
            try {
                transactionManager.getTransaction().registerSynchronization(synchronizedObserver);
            } catch (RollbackException e) {
                LOGGER.error("Cannot register synchronization", e);
            } catch (SystemException e) {
                LOGGER.error("Cannot register synchronization", e);
            }
        } else {
            LOGGER.error("Cannot register synchronization");
        }

    }

    @Override
    public boolean isTransactionActive() {
        UserTransaction userTransaction = getUserTransactionFromContext();
        if (userTransaction != null) {
            try {
                return (userTransaction.getStatus() == Status.STATUS_ACTIVE);
            } catch (SystemException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    public UserTransaction getUserTransaction() {
        return getUserTransactionFromContext();
    }

    @Override
    public void cleanup() {

    }
}
