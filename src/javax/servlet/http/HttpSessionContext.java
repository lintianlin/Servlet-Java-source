package javax.servlet.http;

import java.util.Enumeration;

/** @deprecated */
public interface HttpSessionContext {
    /** @deprecated */
    HttpSession getSession(String var1);

    /** @deprecated */
    Enumeration<String> getIds();
}