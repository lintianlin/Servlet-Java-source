package javax.servlet.http;

import java.util.EventListener;

public interface HttpSessionAttributeListener extends EventListener {
    void attributeAdded(HttpSessionBindingEvent var1);

    void attributeRemoved(HttpSessionBindingEvent var1);

    void attributeReplaced(HttpSessionBindingEvent var1);
}