package javax.servlet;

import java.util.EventListener;

public interface ServletRequestListener extends EventListener {
    void requestDestroyed(ServletRequestEvent var1);

    void requestInitialized(ServletRequestEvent var1);
}
