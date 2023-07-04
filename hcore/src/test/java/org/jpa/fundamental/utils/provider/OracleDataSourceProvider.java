package org.jpa.fundamental.utils.provider;

import oracle.jdbc.pool.OracleDataSource;
import org.jpa.fundamental.utils.provider.query.OracleQueries;
import org.jpa.fundamental.utils.provider.query.Queries;
import org.testcontainers.containers.JdbcDatabaseContainer;

import javax.sql.DataSource;
import java.util.Properties;

public class OracleDataSourceProvider extends AbstractContainerDataSourceProvider {

    @Override
    public String hibernateDialect() {
        return FastOracleDialect.class.getName();
    }

    @Override
    public String defaultJdbcUrl() {
        return "jdbc:oracle:thin:@localhost:1521/xe";
    }

    @Override
    public DataSource newDataSource() {
        try {
            OracleDataSource dataSource = new OracleDataSource();
            JdbcDatabaseContainer container = database().getContainer();
            if(container == null) {
                dataSource.setDatabaseName("high_performance_java_persistence");
            } else {
                dataSource.setDatabaseName(container.getDatabaseName());
            }
            dataSource.setURL(url());
            dataSource.setUser(username());
            dataSource.setPassword(password());
            return dataSource;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Class<? extends DataSource> dataSourceClassName() {
        return OracleDataSource.class;
    }

    @Override
    public Properties dataSourceProperties() {
        Properties properties = new Properties();
        properties.setProperty("databaseName", "high_performance_java_persistence");
        properties.setProperty("URL", url());
        properties.setProperty("user", username());
        properties.setProperty("password", password());
        return properties;
    }

    @Override
    public String username() {
        return "oracle";
    }

    @Override
    public String password() {
        return "admin";
    }

    @Override
    public Database database() {
        return Database.ORACLE;
    }

    @Override
    public Queries queries() {
        return OracleQueries.INSTANCE;
    }

}
