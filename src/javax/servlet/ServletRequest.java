package javax.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public interface ServletRequest {
    /**
     * 属性是与请求相关联的对象。属性可以由容器设置来表达信息，否则无法通过API表示，或者由servlet设
     * 置将信息传达给另一个servlet（通过RequestDispatcher）。属性通过ServletRequest 接口中下面的方法来访
     * 问：
     * ■ getAttribute
     * ■ getAttributeNames
     * ■ setAttribute
     * 只有一个属性值可与一个属性名称相关联。以前缀java.和javax.开头的属性名称是本规范的保留定义。
     * 同样地，以前缀sun.和com.sun.，oracle 和com.oracle 开头的属性名是Oracle Corporation 的保留定
     * 义。建议属性集中所有属性的命名与Java 编程语言的规范1为包命名建议的反向域名约定一致。
     * page-30
     *
     * @param var1
     * @return
     */
    Object getAttribute(String var1);

    Enumeration<String> getAttributeNames();

    String getCharacterEncoding();

    /**
     * 目前，许多浏览器不随着Content-Type头一起发送字符编码限定符，而是根据读取HTTP请求确定字符编
     * 码。如果客户端请求没有指定请求默认的字符编码，容器用来创建请求读取器和解析POST 数据的编码必
     * 须是“ISO-8859-1”。然而，为了向开发人员说明客户端没有指定请求默认的字符编码，在这种情况下，客
     * 户端发送字符编码失败，容器从getCharacterEncoding方法返回null。
     * 如果客户端没有设置字符编码，并使用不同的编码来编码请求数据，而不是使用上面描述的默认的字符编
     * 码，那么可能会发生破坏。为了弥补这种情况，ServletRequest 接口添加了一个新的方法
     * setCharacterEncoding(String enc)。开发人员可以通过调用此方法来覆盖由容器提供的字符编码。必须在解析
     * 任何post数据或从请求读取任何输入之前调用此方法。此方法一旦调用，将不会影响已经读取的数据的编
     * 码。
     *
     * @param var1
     * @throws UnsupportedEncodingException
     */
    void setCharacterEncoding(String var1) throws UnsupportedEncodingException;

    int getContentLength();

    long getContentLengthLong();

    String getContentType();

    ServletInputStream getInputStream() throws IOException;

    String getParameter(String var1);

    Enumeration<String> getParameterNames();

    /**
     * getParameterValues 方法返回一个String 对象的数组，包含了与参数名称相关的所有参数值。getParameter
     * 方法的返回值必须是getParameterValues 方法返回的String 对象数组中的第一个值。getParameterMap 方法
     * 返回请求参数的一个java.util.Map 对象，其中以参数名称作为map键，参数值作为map值。
     * page-29
     *
     * @param var1
     * @return
     */
    String[] getParameterValues(String var1);

    Map<String, String[]> getParameterMap();

    String getProtocol();

    String getScheme();

    String getServerName();

    int getServerPort();

    BufferedReader getReader() throws IOException;

    String getRemoteAddr();

    String getRemoteHost();

    void setAttribute(String var1, Object var2);

    void removeAttribute(String var1);

    /**
     * 客户可以选择希望Web服务器用什么语言来响应。该信息可以和使用Accept-Language头与HTTP/1.1规范
     * 中描述的其他机制的客户端通信。ServletRequest接口提供下面的方法来确定发送者的首选语言环境：
     * ■ getLocale
     * ■ getLocales
     * 的getLocale方法将返回客户端要接受内容的首选语言环境。要了解更多关于Accept-Language头必须被解
     * 释为确定客户端首选语言的信息，请参阅RFC 2616（HTTP/1.1）14.4节。
     * getLocales 方法将返回一个Locale 对象的枚举，从首选语言环境开始顺序递减，这些语言环境是可被客户
     * 接受的语言环境。
     * 如果客户端没有指定首选语言环境，getLocale方法返回的语言环境必须是servlet容器默认的语言环境，而
     * getLocales方法必须返回只包含一个默认语言环境的Local元素的枚举。
     * page-34
     *
     * @return
     */
    Locale getLocale();

    Enumeration<Locale> getLocales();

    /**
     * 如果请求已经通过一个安全协议发送过，如HTTPS，必须通过ServletRequest接口的isSecure方法公开该信
     * 息。
     * page-33
     *
     * @return
     */
    boolean isSecure();

    RequestDispatcher getRequestDispatcher(String var1);

    /**
     * @deprecated
     */
    String getRealPath(String var1);

    int getRemotePort();

    String getLocalName();

    String getLocalAddr();

    int getLocalPort();

    ServletContext getServletContext();

    /**
     * public AsyncContext startAsync() 是一个简便方法，使用原始请求和响应对象用于异步处理。请注意，
     * 如果它们在你想调用此方法之前被包装了，这个方法的使用者应该刷出（flush）响应，确保数据写到被包装的响应中没有丢失
     * page-19
     *
     * @return
     * @throws IllegalStateException
     */
    AsyncContext startAsync() throws IllegalStateException;

    /**
     * 这个方法的作用是将请求转换
     * 为异步模式，并使用给定的请求及响应对象和getAsyncTimeout 返回的超时时间初始化它的
     * AsyncContext
     * page-19
     *
     * @param var1
     * @param var2
     * @return
     * @throws IllegalStateException
     */
    AsyncContext startAsync(ServletRequest var1, ServletResponse var2) throws IllegalStateException;

    /**
     * 如果请求的异步处理已经开始将返回true，否则返回false。如果这
     * 个请求自从被设置为异步模式后已经使用任意一个AsyncContext.dispatch 方法分派，或者成功调用了
     * AsynContext.complete 方法，这个方法将返回false。
     * page-20
     *
     * @return
     */
    boolean isAsyncStarted();

    /**
     * 如果请求支持异常处理则返回true，否则返回false。一旦请求传
     * 给了过滤器或servlet 不支持异步处理（通过指定的注解或声明），异步支持将被禁用。
     * page-20
     *
     * @return
     */
    boolean isAsyncSupported();

    /**
     * 返回由startAsync 调用创建的或初始化的AsyncContext。如
     * 果请求已经被设置为异步模式，调用getAsyncContext 是非法的。
     * page-20
     *
     * @return
     */
    AsyncContext getAsyncContext();

    /**
     * 返回请求的分派器（dispatcher）类型。容器使用请求的
     * 分派器类型来选择需要应用到请求的过滤器。只有匹配分派器类型和url 模式（url pattern）的过滤器
     * 才会被应用。允许一个过滤器配置多个分派器类型，过滤器可以根据请求的不同分派器类型处理请求。
     * 请求的初始分派器类型定义为DispatcherType.REQUEST 。使用
     * RequestDispatcher.forward(ServletRequest, ServletResponse) 或RequestDispatcher.include(ServletRequest,
     * ServletResponse) 分派时， 它们的请求的分派器类型分别是DispatcherType.FORWARD 或
     * DispatcherType.INCLUDE ，当一个异步请求使用任意一个AsyncContext.dispatch 方法分派时该请求的
     * 分派器类型是DispatcherType.ASYNC。最后，由容器的错误处理机制分派到错误页面的分派器类型是
     * DispatcherType.ERROR 。
     * page-20
     *
     * @return
     */
    DispatcherType getDispatcherType();
}