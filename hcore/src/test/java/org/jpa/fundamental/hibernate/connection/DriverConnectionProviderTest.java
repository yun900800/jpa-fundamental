package org.jpa.fundamental.hibernate.connection;

import org.jpa.fundamental.utils.AbstractTest;
import org.jpa.fundamental.utils.provider.DataSourceProvider;
import org.jpa.fundamental.utils.provider.entity.BlogEntityProvider;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class DriverConnectionProviderTest extends AbstractTest {

    private BlogEntityProvider entityProvider = new BlogEntityProvider();

    @Override
    protected Class<?>[] entities() {
        return entityProvider.entities();
    }

    protected DataSource newDataSource() {
        return null;
    }

    protected Properties properties() {
        Properties properties = super.properties();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        appendDriverProperties(properties);
        return properties;
    }

    protected void appendDriverProperties(Properties properties) {
        DataSourceProvider dataSourceProvider = dataSourceProvider();
        properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
        properties.put("hibernate.connection.url", dataSourceProvider.url());
        properties.put("hibernate.connection.username", dataSourceProvider.username());
        properties.put("hibernate.connection.password", dataSourceProvider.password());
    }

    @Test
    public void testConnection() {
        for (final AtomicLong i = new AtomicLong(); i.get() < 5; i.incrementAndGet()) {
            doInJPA(em -> {
                em.persist(new BlogEntityProvider.Post(i.get()));
            });
        }

        doInJPA(em -> {
            BlogEntityProvider.Post post = em.find(BlogEntityProvider.Post.class, 1L);
            BlogEntityProvider.PostComment comment = new BlogEntityProvider.PostComment("abc");
            comment.setId(1L);
            post.addComment(comment);
            em.persist(comment);
        });
        doInJPA(em -> {
            em.createQuery("select p from Post p join fetch p.comments", BlogEntityProvider.Post.class).getResultList();
        });
    }
}
