package org.jpa.fundamental.hibernate.connection;

import org.jpa.fundamental.utils.provider.DataSourceProvider;

import java.util.Properties;

public class JPADriverConnectionProviderTest extends DriverConnectionProviderTest {

    protected void appendDriverProperties(Properties properties) {
        DataSourceProvider dataSourceProvider = dataSourceProvider();
        properties.put("jakarta.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
        properties.put("jakarta.persistence.jdbc.url", dataSourceProvider.url());
        properties.put("jakarta.persistence.jdbc.user", dataSourceProvider.username());
        properties.put("jakarta.persistence.jdbc.password", dataSourceProvider.password());
    }
}
