package br.com.cassunde.pessistence.impl;

import javax.persistence.EntityManager;

@FunctionalInterface
interface JPAOperation {
    Object execute(EntityManager entityManager);
}
