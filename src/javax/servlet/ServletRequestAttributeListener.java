package javax.servlet;

import java.util.EventListener;

public interface ServletRequestAttributeListener extends EventListener {
    void attributeAdded(ServletRequestAttributeEvent var1);

    void attributeRemoved(ServletRequestAttributeEvent var1);

    void attributeReplaced(ServletRequestAttributeEvent var1);
}