package org.jpa.fundamental.utils.transation;

import org.hibernate.Session;

import java.util.function.Function;

/**
 * @author Vlad Mihalcea
 */
@FunctionalInterface
public interface HibernateTransactionFunction<T> extends Function<Session, T> {
	default void beforeTransactionCompletion() {

	}

	default void afterTransactionCompletion() {

	}
}
