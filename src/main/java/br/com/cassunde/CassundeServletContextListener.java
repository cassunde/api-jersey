package br.com.cassunde;


import br.com.cassunde.cache.CacheSingleton;
import br.com.cassunde.persistence.PersistenceSingleton;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class CassundeServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        CacheSingleton.instance.destroy();
        PersistenceSingleton.instance.destroy();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }
}
