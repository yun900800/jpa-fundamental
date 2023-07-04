package org.jpa.fundamental.utils.provider;

import com.zaxxer.hikari.util.DriverDataSource;
import org.hibernate.dialect.CockroachDialect;
import org.jpa.fundamental.utils.provider.query.PostgreSQLQueries;
import org.jpa.fundamental.utils.provider.query.Queries;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.JdbcDatabaseContainer;

import javax.sql.DataSource;
import java.util.Properties;

public class CockroachDBDataSourceProvider extends AbstractContainerDataSourceProvider {

    @Override
    public String hibernateDialect() {
        return CockroachDialect.class.getName();
    }

    @Override
    protected String defaultJdbcUrl() {
        return String.format(
                "jdbc:postgresql://%s:%d/high_performance_java_persistence",
                host(),
                port()
        );
    }

    protected DataSource newDataSource() {
        JdbcDatabaseContainer container = database().getContainer();
        if (container != null) {
            Properties properties = new Properties();
            return new DriverDataSource(
                    container.getJdbcUrl(),
                    container.getDriverClassName(),
                    properties,
                    container.getUsername(),
                    container.getPassword()
            );
        }
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url());
        dataSource.setUser(username());
        dataSource.setPassword(password());
        dataSource.setSsl(false);
        return dataSource;
    }

    @Override
    public Class<? extends DataSource> dataSourceClassName() {
        return PGSimpleDataSource.class;
    }

    @Override
    public Properties dataSourceProperties() {
        Properties properties = new Properties();
        properties.setProperty("databaseName", "high_performance_java_persistence");
        properties.setProperty("serverName", host());
        properties.setProperty("portNumber", String.valueOf(port()));
        properties.setProperty("user", username());
        properties.setProperty("password", password());
        properties.setProperty("sslmode", "disabled");
        return properties;
    }

    public String host() {
        return "127.0.0.1";
    }

    public int port() {
        return 26257;
    }

    @Override
    public String username() {
        return "cockroach";
    }

    @Override
    public String password() {
        return "admin";
    }

    @Override
    public Database database() {
        return Database.COCKROACHDB;
    }

    @Override
    public Queries queries() {
        return PostgreSQLQueries.INSTANCE;
    }
}
