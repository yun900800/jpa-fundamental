package org.jpa.fundamental.utils.provider.query;

/**
 * @author Vlad Mihalcea
 */
public class SQLServerQueries implements Queries {

    public static final Queries INSTANCE = new SQLServerQueries();

    @Override
    public String transactionId() {
        return "SELECT CONVERT(VARCHAR, CURRENT_TRANSACTION_ID())";
    }
}
