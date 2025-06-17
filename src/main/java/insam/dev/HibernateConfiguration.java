package insam.dev;

import org.hibernate.SessionFactory;

public class HibernateConfiguration {
    public SessionFactory sessionFactory(){
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration
                .addPackage("insam.dev")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hibernate")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "password")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop");
        return configuration.buildSessionFactory();
    }
}
