package javax.servlet;

import java.util.EventListener;

public interface ServletContextAttributeListener extends EventListener {
    void attributeAdded(ServletContextAttributeEvent var1);

    void attributeRemoved(ServletContextAttributeEvent var1);

    void attributeReplaced(ServletContextAttributeEvent var1);
}