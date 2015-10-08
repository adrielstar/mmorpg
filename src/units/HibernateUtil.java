package units;

/**
 * Created by Adriel on 10/8/2015.
 */

import models.Character;
import models.Server;
import models.User;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;
    private static final ServiceRegistry SERVICE_REGISTRY;

    private static Session mSession;

    static {
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

    public static Session openSession() throws HibernateException {
        mSession = SESSION_FACTORY.openSession();
        mSession.setFlushMode(FlushMode.MANUAL);
        ManagedSessionContext.bind(mSession);

        System.out.printf("Session is %s \n", mSession.isOpen());

        return mSession;
    }

    public static void commitTransaction(Session session) {
        ManagedSessionContext.unbind(HibernateUtil.SESSION_FACTORY);
        session.flush();
        session.getTransaction().commit();
        session.close();

        System.out.printf("Session is %s \n", session.isConnected() ? "connected" : "not connected");
    }

    public static Configuration getConfiguration() {
        Configuration cfg = new Configuration();

        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Character.class);
        cfg.addAnnotatedClass(Server.class);
        cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/battlefield");
        cfg.setProperty("hibernate.connection.username", "postgres");
        cfg.setProperty("hibernate.connection.password", "daniel1986");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");
        cfg.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        cfg.setProperty("hibernate.current_session_context_class", "thread");

        return cfg;
    }
}