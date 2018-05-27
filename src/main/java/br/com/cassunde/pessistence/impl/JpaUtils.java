package br.com.cassunde.pessistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import br.com.cassunde.persistence.PersistenceSingleton;

class JpaUtils {

    static Object transactional(final JPAOperation operation, String errorMessage) {
        EntityManager em = PersistenceSingleton.instance.getEntityManagerFactory().createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            final Object returnedData = operation.execute(em);
            tx.commit();
            return returnedData;
        } catch (RollbackException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new IllegalStateException(errorMessage, e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
