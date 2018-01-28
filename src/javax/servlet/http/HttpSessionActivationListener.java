package javax.servlet.http;

import java.util.EventListener;

public interface HttpSessionActivationListener extends EventListener {
    void sessionWillPassivate(HttpSessionEvent var1);

    void sessionDidActivate(HttpSessionEvent var1);
}