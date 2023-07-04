package org.jpa.fundamental.utils;

import org.jpa.fundamental.utils.provider.Database;

public class AbstractSQLServerIntegrationTest extends AbstractTest{

    @Override
    protected Database database() {
        return Database.SQLSERVER;
    }
}
