/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hw10.hibernate;


import org.junit.Test;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import org.hibernate.SessionFactory;
import ru.otus.core.model.User;


public class SessionManagerHibernateTest {
    @Test public void crateSessionTest() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
    }
}
