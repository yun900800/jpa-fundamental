package org.jpa.fundamental.utils.transation;

import jakarta.persistence.EntityManager;

import java.util.function.Function;

/**
 * @author Vlad Mihalcea
 */
@FunctionalInterface
public interface JPATransactionFunction<T> extends Function<EntityManager, T> {
	default void beforeTransactionCompletion() {

	}

	default void afterTransactionCompletion() {

	}
}
