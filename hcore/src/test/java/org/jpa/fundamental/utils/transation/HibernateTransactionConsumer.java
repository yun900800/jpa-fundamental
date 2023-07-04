package org.jpa.fundamental.utils.transation;

import org.hibernate.Session;

import java.util.function.Consumer;

/**
 * @author Vlad Mihalcea
 */
@FunctionalInterface
public interface HibernateTransactionConsumer extends Consumer<Session> {
	default void beforeTransactionCompletion() {

	}

	default void afterTransactionCompletion() {

	}
}
