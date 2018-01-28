package javax.servlet.http;

import javax.servlet.ServletContext;

import java.util.Enumeration;

/**
 * 超文本传输协议（HTTP）被设计为一种无状态协议。为构建有效的Web 应用，必须与来自一个特定的客
 * 户端的请求彼此是相互关联。随时间的推移，演变了许多会话跟踪机制，这些机制直接使用对程序员而言
 * 是困难或麻烦的。
 * 该规范定义了一个简单的HttpSession接口，允许servlet 容器使用几种方法来跟踪用户会话，而不会使应用
 * 开发人员陷入到这些方法的细节中。
 * page-51
 */
public interface HttpSession {
    long getCreationTime();

    /**
     * Servlet开发人员必须设计他的应用以便处理客户端没有，不能，或不会加入会话的情况。
     * 与每个会话相关联是一个包含唯一标识符的字符串，也被称为会话ID。会话ID 的值能通过调用
     * javax.servlet.http.HttpSession.getId() 获取， 且能在创建后通过调用
     * javax.servlet.http.HttpServletRequest.changeSessionId()改变。
     * page-52
     *
     * @return
     */
    String getId();

    /**
     * HttpSession接口的getLastAccessedTime方法允许servlet 确定在当前请求之前的会话的最后访问时间。当会
     * 话中的请求是servlet容器第一个处理的时该会话被认为是访问了。
     * page-53
     *
     * @return
     */
    long getLastAccessedTime();

    ServletContext getServletContext();

    /**
     * 在 HTTP 协议中，当客户端不再处于活动状态时没有显示的终止信号。这意味着当客户端不再处于活跃状
     * 态时可以使用的唯一机制是超时时间。
     * Servlet容器定义了默认的会话超时时间，且可以通过HttpSession接口的getMaxInactiveInterval 方法获取。
     * 开发人员可以使用HttpSession 接口的setMaxInactiveInterval 方法改变超时时间。这些方法的超时时间以秒
     * 为单位。根据定义，如果超时时间设置为0 或更小的值，会话将永不过期。会话不会生效，直到所有servlet
     * 使用的会话已经退出其service方法。一旦会话已失效,新的请求必须不能看到该会话。
     * page-53
     *
     * @param var1
     */
    void setMaxInactiveInterval(int var1);

    int getMaxInactiveInterval();

    /**
     * @deprecated
     */
    HttpSessionContext getSessionContext();

    Object getAttribute(String var1);

    /**
     * @deprecated
     */
    Object getValue(String var1);

    Enumeration<String> getAttributeNames();

    /**
     * @deprecated
     */
    String[] getValueNames();

    /**
     * 在一个标识为分布式的应用程序中，会话中的所有请求在同一时间必须仅被一个JVM处理。容器必须能够
     * 使用适当的setAttribute 或putValue 方法把所有对象放入到HttpSession类实例。
     * page-53
     *
     * @param var1
     * @param var2
     */
    void setAttribute(String var1, Object var2);

    /**
     * @deprecated
     */
    void putValue(String var1, Object var2);

    void removeAttribute(String var1);

    /**
     * @deprecated
     */
    void removeValue(String var1);

    void invalidate();

    boolean isNew();
}
