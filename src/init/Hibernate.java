package init;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import models.Character;
import models.Server;
import models.User;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by Adriel on 10/8/2015.
 */



public class Hibernate {

    private static SessionFactory SESSION_FACTORY = null;
    private static ServiceRegistry SERVICE_REGISTRY = null;

    private static Hibernate instance;
    private static Session mSession;

    private Hibernate() {
        try {
            Configuration configuration = getConfiguration();

            SERVICE_REGISTRY = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).buildServiceRegistry();
            SESSION_FACTORY = configuration.buildSessionFactory(SERVICE_REGISTRY);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Hibernate getInstance() {
        if (instance == null) {
            synchronized (Hibernate.class) {
                if (instance == null) {
                    instance = new Hibernate();
                }
            }
        }
        return instance;
    }

    public Session openSession() {
        mSession = SESSION_FACTORY.openSession();
        mSession.setFlushMode(FlushMode.COMMIT);
        ManagedSessionContext.bind(mSession);

        return mSession;
    }

    public void transactionManagement(Session session) {

        ManagedSessionContext.unbind(Hibernate.SESSION_FACTORY);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    private Configuration getConfiguration() {
        Configuration cfg = new Configuration();

        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Character.class);
        cfg.addAnnotatedClass(Server.class);

        cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        cfg.setProperty("hibernate.connection.url", Credential.DATABASE_CONNECTION);
        cfg.setProperty("hibernate.connection.username", Credential.DATABASE_USER);
        cfg.setProperty("hibernate.connection.password", Credential.DATABASE_PASSWORD);
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");
        cfg.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        cfg.setProperty("hibernate.current_session_context_class", "thread");

        return cfg;
    }
}