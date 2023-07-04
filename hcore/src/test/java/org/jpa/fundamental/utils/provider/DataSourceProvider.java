package org.jpa.fundamental.utils.provider;

import org.hibernate.dialect.Dialect;
import org.jpa.fundamental.utils.ReflectionUtils;
import org.jpa.fundamental.utils.provider.query.Queries;

import javax.sql.DataSource;
import java.util.Properties;

public interface DataSourceProvider {

    enum IdentifierStrategy {
        IDENTITY,
        SEQUENCE
    }

    String hibernateDialect();

    DataSource dataSource();

    Class<? extends DataSource> dataSourceClassName();

    Properties dataSourceProperties();

    String url();

    String username();

    String password();

    Database database();

    Queries queries();

    default Class<? extends Dialect> hibernateDialectClass() {
        return ReflectionUtils.getClass(hibernateDialect());
    }
}
