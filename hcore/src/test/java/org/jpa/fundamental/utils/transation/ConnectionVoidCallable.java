package org.jpa.fundamental.utils.transation;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vlad Mihalcea
 */
@FunctionalInterface
public interface ConnectionVoidCallable {
	void execute(Connection connection) throws SQLException;
}
