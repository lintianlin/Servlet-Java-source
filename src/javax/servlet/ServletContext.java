package javax.servlet;

import javax.servlet.descriptor.JspConfigDescriptor;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;
import java.util.logging.Filter;

/**
 * ServletContext（Servlet上下文）接口定义了servlet运行在的Web应用的视图。容器供应商负责提供Servlet
 * 容器的ServletContext接口的实现。Servlet可以使用ServletContext 对象记录事件，获取URL引用的资源，
 * 存取当前上下文的其他Servlet可以访问的属性。
 * <p>
 * ServletContext 是Web 服务器中已知路径的根。例如，Servlet 上下文可以从http://www.mycorp.com/catalog
 * 找出，/catalog 请求路径称为上下文路径，所有以它开头的请求都会被路由到与ServletContext 相关联的
 * Web 应用。
 * page-35
 */
public interface ServletContext {
    String TEMPDIR = "javax.servlet.context.tempdir";
    String ORDERED_LIBS = "javax.servlet.context.orderedLibs";

    String getContextPath();

    ServletContext getContext(String var1);

    int getMajorVersion();

    int getMinorVersion();

    int getEffectiveMajorVersion();

    int getEffectiveMinorVersion();

    String getMimeType(String var1);

    Set<String> getResourcePaths(String var1);

    /**
     * ServletContext 接口提供了直接访问Web应用中静态内容层次结构的文件的方法，包括HTML，GIF和JPEG
     * 文件：
     * ■ getResource
     * ■ getResourceAsStream
     * getResource 和getResourceAsStream 方法需要一个以“/”开头的String 字符串作为参数，给定的资源路径
     * 是相对于上下文的根，或者相对于web 应用的WEB-INF/lib 目录下的JAR 文件中的META-INF/resources
     * 目录。这两个方法首先根据请求的资源查找web应用上下文的根，然后查找所有WEB-INF/lib目录下的JAR
     * 文件。查找WEB-INF/lib目录中JAR文件的顺序是不确定的。这种层次结构的文件可以存在于服务器的文
     * 件系统，Web 应用的归档文件，远程服务器，或在其他位置。
     * 这两个方法不能用于获取动态内容。例如，在支持JavaServer Pages™规范（JavaServer Pages™ 规范可以在
     * http://java.sun.com/products/jsp 找到）的容器中，如getResource("/index.jsp")形式的方法调用将返回JSP 源
     * 码而不是处理后的输出。请看第9 章，“分派请求”获取更多关于动态内容的信息。
     * 可以使用getResourcePaths(String path)方法访问Web 应用中的资源的完整列表。该方法的语义的全部细节
     * 可以从本规范的API文档中找到。
     * page-40
     *
     * @param var1
     * @return
     * @throws MalformedURLException
     */
    URL getResource(String var1) throws MalformedURLException;

    InputStream getResourceAsStream(String var1);

    /**
     * getRequestDispatcher方法需要一个String类型的参数描述在ServletContext作用域内的路径。这个路径必须
     * 是相对于ServletContext的根路径，或以’/’开头，或者为空。该方法根据这个路径使用servlet 路径匹配规则
     * （见第12章，请求映射到servlet）来查找servlet，把它包装成RequestDispatcher 对象并返回。如果基于给
     * 定的路径没有找到相应的servlet，那么返回这个路径内容提供的RequestDispatcher。
     * page-75
     *
     * @param var1
     * @return
     */
    RequestDispatcher getRequestDispatcher(String var1);

    /**
     * getNamedDispatcher 方法使用一个ServletContext知道的servlet名称作为参数。如果找到一个servlet，则把
     * 它包装成RequestDispatcher 对象，并返回该对象。如果没有与给定名字相关的servlet，该方法必须返回null。
     * page-75
     *
     * @param var1
     * @return
     */
    RequestDispatcher getNamedDispatcher(String var1);

    /**
     * @deprecated
     */
    Servlet getServlet(String var1) throws ServletException;

    /**
     * @deprecated
     */
    Enumeration<Servlet> getServlets();

    /**
     * @deprecated
     */
    Enumeration<String> getServletNames();

    void log(String var1);

    /**
     * @deprecated
     */
    void log(Exception var1, String var2);

    void log(String var1, Throwable var2);

    /**
     * 在API中有两个方便的方法，允许开发者获得与某个特定的路径等价的文件系统路径。这些方法是：
     * ■ ServletContext.getRealPath
     * ■ HttpServletRequest.getPathTranslated
     * getRealPath 方法需要一个字符串参数，并返回一个字符串形式的路径，这个路径对应一个在本地文件系统
     * 上的文件。getPathTranslated 方法推断出请求的pathInfo的实际路径（译者注：把URL中servlet 名称之后，
     * 查询字符串之前的路径信息转化成实际的路径）。
     * 这些方法在servlet 容器无法确定一个有效的文件路径的情况下，如Web 应用程序从归档中，在不能访问
     * 本地的远程文件系统上，或在一个数据库中执行时，这些方法必须返回null。JAR文件中META-INF/resources
     * 目录下的资源，只有当调用getRealPath()方法时才认为容器已经从包含它的JAR 文件中解压，在这种情况
     * 下，必须返回解压缩后位置。
     * page-32
     *
     * @param var1
     * @return
     */
    String getRealPath(String var1);

    String getServerInfo();

    /**
     * 如下ServletContext 接口方法允许Servlet 访问由应用开发人员在Web 应用中的部署描述符中指定的上下文
     * 初始化参数：
     * ■ getInitParameter
     * ■ getInitParameterNames
     * 应用开发人员使用初始化参数来表达配置信息。代表性的例子是一个Webmaster 的e-mail 地址，或保存关
     * 键数据的系统名称。
     * page-35
     *
     * @param var1
     * @return
     */
    String getInitParameter(String var1);

    Enumeration<String> getInitParameterNames();

    boolean setInitParameter(String var1, String var2);

    /**
     * Servlet 可以使用指定的名字将对象属性绑定到上下文。同一个Web 应用内的其他任何Servlet 都可以使用
     * 绑定到上下文的任意属性。以下Servlet接口中的方法允许访问此功能：
     * ■ setAttribute
     * ■ getAttribute
     * ■ getAttributeNames
     * ■ removeAttribute
     * page-39
     *
     * @param var1
     * @return
     */
    Object getAttribute(String var1);

    Enumeration<String> getAttributeNames();

    void setAttribute(String var1, Object var2);

    void removeAttribute(String var1);

    String getServletContextName();

    /**
     * 该方法允许应用以编程方式声明一个Servlet。它添加以给定的名称和class名称的Servlet到servlet上下文。
     * page-36
     *
     * @param var1
     * @param var2
     * @return
     */
    ServletRegistration.Dynamic addServlet(String var1, String var2);

    /**
     * 该方法允许应用以编程方式声明一个Servlet。它添加以给定的名称和Servlet 实例的Servlet 到servlet 上下
     * 文。
     * page-36
     *
     * @param var1
     * @param var2
     * @return
     */
    ServletRegistration.Dynamic addServlet(String var1, Servlet var2);

    /**
     * 该方法允许应用以编程方式声明一个Servlet。它添加以给定的名称和Servlet 类的一个实例的Servlet 到
     * servlet上下文。
     * page-36
     *
     * @param var1
     * @param var2
     * @return
     */
    ServletRegistration.Dynamic addServlet(String var1, Class<? extends Servlet> var2);

    /**
     * 该方法实例化一个给定的Servlet class，该方法必须支持适用于Servlet 的除了@WebServlet的所有注解。
     * 返回的Servlet 实例通过调用上边定义的addServlet(String, Servlet)注册到ServletContext 之前，可以进行进
     * 一步的定制。
     * page-36
     *
     * @param var1
     * @param <T>
     * @return
     * @throws ServletException
     */
    <T extends Servlet> T createServlet(Class<T> var1) throws ServletException;

    /**
     * 该方法返回与指定名字的Servlet相关的ServletRegistration，或者如果没有该名字的ServletRegistration 则返
     * 回null。如果ServletContext传到了ServletContextListener的contextInitialized方法，但该ServletContextListener
     * 即没有在web.xml 或web-fragment.xml中声明也没有使用javax.servlet.annotation.WebListener 注解，则必须
     * 抛出UnsupportedOperationException。
     * page-36
     *
     * @param var1
     * @return
     */
    ServletRegistration getServletRegistration(String var1);

    /**
     * 该方法返回ServletRegistration 对象的map，由名称作为键并对应着注册到ServletContext 的所有Servlet。
     * 如果没有Servlet 注册到ServletContext 则返回一个空的map。返回的Map 包括所有声明和注解的Servlet
     * 对应的ServletRegistration对象，也包括那些使用addServlet方法添加的所有Servlet对于的ServletRegistration
     * 对象。返回的Map 的任何改变不影响ServletContext。如果ServletContext 传到了ServletContextListener 的
     * contextInitialized 方法，但该ServletContextListener 即没有在web.xml 或web-fragment.xml 中声明也没有使
     * 用javax.servlet.annotation.WebListener 注解，则必须抛出UnsupportedOperationException。
     * page-36
     *
     * @return
     */
    Map<String, ? extends ServletRegistration> getServletRegistrations();

    /**
     * 该方法允许应用以编程方式声明一个Filter。它添加以给定的名称和class 名称的Filter到web 应用。
     * page-36
     *
     * @param var1
     * @param var2
     * @return
     */
    FilterRegistration.Dynamic addFilter(String var1, String var2);

    /**
     * 该方法允许应用以编程方式声明一个Filter。它添加以给定的名称和filter实例的Filter到web 应用。
     * page-37
     *
     * @param var1
     * @param var2
     * @return
     */
    FilterRegistration.Dynamic addFilter(String var1, Filter var2);

    /**
     * 该方法允许应用以编程方式声明一个Filter。它添加以给定的名称和filter 类的一个实例的Filter 到web 应
     * 用。
     * page-37
     *
     * @param var1
     * @param var2
     * @return
     */
    FilterRegistration.Dynamic addFilter(String var1, Class<? extends Filter> var2);

    /**
     * 该方法实例化一个给定的Filter class，该方法必须支持适用于Filter的所有注解。
     * 返回的Filter实例通过调用上边定义的addServlet(String, Filter)注册到ServletContext之前，可以进行进一步
     * 的定制。给定的Filter类必须定义一个用于实例化的空参构造器。
     * page-37
     *
     * @param var1
     * @param <T>
     * @return
     * @throws ServletException
     */
    <T extends Filter> T createFilter(Class<T> var1) throws ServletException;

    /**
     * 该方法返回与指定名字的Filter 相关的FilterRegistration，或者如果没有该名字的FilterRegistration 则返回
     * null。如果ServletContext传到了ServletContextListener的contextInitialized方法，但该ServletContextListener
     * 即没有在web.xml 或web-fragment.xml中声明也没有使用javax.servlet.annotation.WebListener 注解，则必须
     * 抛出UnsupportedOperationException。
     * page-37
     *
     * @param var1
     * @return
     */
    FilterRegistration getFilterRegistration(String var1);

    /**
     * 该方法返回FilterRegistration对象的map，由名称作为键并对应着注册到ServletContext的所有Filter。如果
     * 没有Filter 注册到ServletContext 则返回一个空的Map。返回的Map 包括所有声明和注解的Filter 对应的
     * FilterRegistration 对象，也包括那些使用addFilter 方法添加的所有Servlet 对于的ServletRegistration 对象。
     * 返回的Map 的任何改变不影响ServletContext。如果ServletContext 传到了ServletContextListener 的
     * contextInitialized 方法，但该ServletContextListener 即没有在web.xml 或web-fragment.xml 中声明也没有使
     * 用javax.servlet.annotation.WebListener 注解，则必须抛出UnsupportedOperationException。
     * page-37
     *
     * @return
     */
    Map<String, ? extends FilterRegistration> getFilterRegistrations();

    SessionCookieConfig getSessionCookieConfig();

    void setSessionTrackingModes(Set<SessionTrackingMode> var1);

    Set<SessionTrackingMode> getDefaultSessionTrackingModes();

    Set<SessionTrackingMode> getEffectiveSessionTrackingModes();

    /**
     * 往ServletContext添加指定class 名称的监听器。ServletContext 将使用由与应用关联的classloader 装载加载
     * 该给定名称的class，且它们必须实现一个或多个以下接口：
     * ■ javax.servlet.ServletContextAttributeListener
     * ■ javax.servlet.ServletRequestListener
     * ■ javax.servlet.ServletRequestAttributeListener
     * ■ javax.servlet.http.HttpSessionListener
     * ■ javax.servlet.http.HttpSessionAttributeListener
     * ■ javax.servlet.http.HttpSessionIdListener
     * 如果 ServletContext 传到了ServletContainerInitializer 的onStartup方法，则给定名字的类可以实现除上面列
     * 出的接口之外的javax.servlet.ServletContextListener。作为该方法调用的一部分，容器必须装载指定类名的
     * class，以确保其实现了所需的接口之一。如果给定名字的类实现了一个监听器接口，则其调用顺序和声明
     * 顺 序 是一样的， 换句话说， 如果它实现了javax.servlet.ServletRequestListener 或
     * javax.servlet.http.HttpSessionListener，那么新的监听器将被添加到该接口的有序监听器列表的末尾。
     * page-37
     *
     * @param var1
     */
    void addListener(String var1);

    /**
     * 往ServletContext添加一个给定的监听器。给定的监听器实例必须实现是一个或多个如下接口：
     * ■ javax.servlet.ServletContextAttributeListener
     * ■ javax.servlet.ServletRequestListener
     * ■ javax.servlet.ServletRequestAttributeListener
     * ■ javax.servlet.http.HttpSessionListener
     * ■ javax.servlet.http.HttpSessionAttributeListener
     * ■ javax.servlet.http.HttpSessionIdListener
     * 如果ServletContext 传到了ServletContainerInitializer 的onStartup方法，则给定的监听器实例可以实现除上
     * 面列出的接口之外的javax.servlet.ServletContextListener。如果给定的监听器实例实现了一个监听器接口，
     * 则其调用顺序和声明顺序是一样的，换句话说，如果它实现了javax.servlet.ServletRequestListener 或
     * javax.servlet.http.HttpSessionListener，那么新的监听器将被添加到该接口的有序监听器列表的末尾。
     * page-38
     *
     * @param var1
     * @param <T>
     */
    <T extends EventListener> void addListener(T var1);

    /**
     * 往ServletContext添加指定class 类型的监听器。给定的监听器类必须实现是一个或多个如下接口：
     * ■ javax.servlet.ServletContextAttributeListener
     * ■ javax.servlet.ServletRequestListener
     * ■ javax.servlet.ServletRequestAttributeListener
     * ■ javax.servlet.http.HttpSessionListener
     * ■ javax.servlet.http.HttpSessionAttributeListener
     * ■ javax.servlet.http.HttpSessionIdListener
     * 如果ServletContext 传到了ServletContainerInitializer 的onStartup方法，则给定的监听器类可以实现除上面
     * 列出的接口之外的javax.servlet.ServletContextListener。如果给定的监听器类实现了一个监听器接口，则其
     * 调用顺序和声明顺序是一样的，换句话说，如果它实现了javax.servlet.ServletRequestListener 或
     * javax.servlet.http.HttpSessionListener，那么新的监听器将被添加到该接口的有序监听器列表的末尾。
     * page-38
     *
     * @param var1
     */
    void addListener(Class<? extends EventListener> var1);

    /**
     * 该方法实例化给定的EventListener类。指定的EventListener类必须实现至少一个如下接口：
     * ■ javax.servlet.ServletContextAttributeListener
     * ■ javax.servlet.ServletRequestListener
     * ■ javax.servlet.ServletRequestAttributeListener
     * ■ javax.servlet.http.HttpSessionListener
     * ■ javax.servlet.http.HttpSessionAttributeListener
     * ■ javax.servlet.http.HttpSessionIdListener
     * 该方法必须支持该规范定义的适用于如上接口的所有注解。返回的EventListener 实例可以在通过调用
     * addListener(T t)注册到ServletContext之前进行进一步的定制。给定的EventListener必须定义一个用于实例
     * 化的空参构造器。
     * page-39
     *
     * @param var1
     * @param <T>
     * @return
     * @throws ServletException
     */
    <T extends EventListener> T createListener(Class<T> var1) throws ServletException;

    JspConfigDescriptor getJspConfigDescriptor();

    ClassLoader getClassLoader();

    void declareRoles(String... var1);

    /**
     * Web 服务器可以支持多个逻辑主机共享一个服务器IP 地址。有时，这种能力被称为“虚拟主机”。这种情
     * 况下，每一个逻辑主机必须有它自己的上下文或一组上下文。Servlet上下文不会在虚拟主机之间共享。
     * ServletContext 接口的getVirtualServerName方法允许访问ServletContext部署在的逻辑主机的配置名字。该
     * 方法必须对所有部署在逻辑主机上的所有servlet context返回同一个名字。且该方法返回的名字必须是明确
     * 的、每个逻辑主机稳定的、和适合用于关联服务器配置信息和逻辑主机。
     * page-40
     *
     * @return
     */
    String getVirtualServerName();
}
