package javax.servlet.http;

import java.util.EventListener;

public interface HttpSessionListener extends EventListener {
    void sessionCreated(HttpSessionEvent var1);

    void sessionDestroyed(HttpSessionEvent var1);
}
