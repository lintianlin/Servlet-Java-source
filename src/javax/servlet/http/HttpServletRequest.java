package javax.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;


public interface HttpServletRequest extends ServletRequest {
    String BASIC_AUTH = "BASIC";
    String FORM_AUTH = "FORM";
    String CLIENT_CERT_AUTH = "CLIENT_CERT";
    String DIGEST_AUTH = "DIGEST";

    String getAuthType();

    /**
     * HttpServletRequest接口提供了getCookies方法来获得请求中的cookie的一个数组。这些cookie是从客户端
     * 发送到服务器端的客户端发出的每个请求上的数据。典型地，客户端发送回的作为cookie的一部分的唯一
     * 信息是cookie的名称和cookie值。当cookie发送到浏览器时可以设置其他cookie属性，诸如注释，这些信
     * 息不会返回到服务器。该规范还允许的cookies是HttpOnly cookie。HttpOnly cookie暗示客户端它们不会暴
     * 露给客户端脚本代码（它没有被过滤掉，除非客户端知道如何查找此属性）。使用HttpOnly cookie有助于减
     * 少某些类型的跨站点脚本攻击。
     * page-33
     *
     * @return
     */
    Cookie[] getCookies();

    long getDateHeader(String var1);

    /**
     * servlet可以通过HttpServletRequest接口的下面方法访问HTTP请求的头部信息：
     * ■ getHeader
     * ■ getHeaders
     * ■ getHeaderNames
     * getHeader方法返回给定头名称的头。多个头可以具有相同的名称，例如HTTP请求中的Cache-Control头。
     * 如果多个头的名称相同，getHeader方法返回请求中的第一个头。getHeaders方法允许访问所有与特定头名
     * 称相关的头值，返回一个String对象的枚举。
     * 头可包含由String形式的int或Date数据。HttpServletRequest接口提供如下方便的方法访问这些类型的头
     * 数据：
     * ■ getIntHeader
     * ■ getDateHeader
     * 如果getIntHeader方法不能转换为int的头值，则抛出NumberFormatException异常。如果getDateHeader方
     * 法不能把头转换成一个Date对象，则抛出IllegalArgumentException 异常。
     * page-31
     *
     * @param var1
     * @return
     */
    String getHeader(String var1);

    Enumeration<String> getHeaders(String var1);

    Enumeration<String> getHeaderNames();

    int getIntHeader(String var1);

    String getMethod();

    String getPathInfo();

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
     * @return
     */
    String getPathTranslated();

    /**
     * 引导servlet服务请求的请求路径由许多重要部分组成。以下元素从请求URI路径得到，并通过request对象
     * 公开：
     * ■ Context Path：与ServletContext相关联的路径前缀是这个servlet 的一部分。如果这个上下文是基于Web
     * 服务器的URL命名空间基础上的“默认”上下文，那么这个路径将是一个空字符串。否则，如果上下文不是
     * 基于服务器的命名空间，那么这个路径以/字符开始，但不以/字符结束。
     * ■ Servlet Path：路径部分直接与激活请求的映射对应。这个路径以“/”字符开头，如果请求与“/ *”或“”模式
     * 匹配，在这种情况下，它是一个空字符串。
     * ■ PathInfo：请求路径的一部分，不属于Context Path或Servlet Path。如果没有额外的路径，它要么是null，
     * 要么是以'/'开头的字符串。
     * 使用HttpServletRequest接口中的下面方法来访问这些信息：
     * ■ getContextPath
     * ■ getServletPath
     * ■ getPathInfo
     * 重要的是要注意，除了请求URI和路径部分的URL编码差异外，下面的等式永远为真：
     * requestURI = contextPath + servletPath + pathInfo
     * page-31
     *
     * @return
     */
    String getContextPath();

    String getQueryString();

    String getRemoteUser();

    boolean isUserInRole(String var1);

    Principal getUserPrincipal();

    String getRequestedSessionId();

    String getRequestURI();

    StringBuffer getRequestURL();

    String getServletPath();

    HttpSession getSession(boolean var1);

    HttpSession getSession();

    String changeSessionId();

    boolean isRequestedSessionIdValid();

    boolean isRequestedSessionIdFromCookie();

    boolean isRequestedSessionIdFromURL();

    /**
     * @deprecated
     */
    boolean isRequestedSessionIdFromUrl();

    boolean authenticate(HttpServletResponse var1) throws IOException, ServletException;

    void login(String var1, String var2) throws ServletException;

    void logout() throws ServletException;

    Collection<Part> getParts() throws IOException, ServletException;

    /**
     * 如何使request中multipart/form-data类型的数据可用，取决于servlet容器是否提供multipart/form-data格式
     * 数据的处理：
     * ■ 如果 servlet容器提供multipart/form-data格式数据的处理，可通过HttpServletRequest中的以下方法得到：
     * ■ public Collection<Part> getParts()
     * ■ public Part getPart(String name)
     * 译者注：Part类代表从multipart/form-data格式的POST请求中接收到的一个部分或表单项。每个part都可
     * 通过Part.getInputStream方法访问头部，相关的内容类型和内容。
     * 对于表单数据的Content-Disposition，即使没有文件名，也可使用part 的名称通过HttpServletRequest 的
     * getParameter和getParameterValues方法得到part的字符串值。
     * ■ 如果 servlet 的容器不提供multi-part/form-data 格式数据的处理，这些数据将可通过
     * HttpServletReuqest.getInputStream得到。
     * page-30
     *
     * @param var1
     * @return
     * @throws IOException
     * @throws ServletException
     */
    Part getPart(String var1) throws IOException, ServletException;

    <T extends HttpUpgradeHandler> T upgrade(Class<T> var1) throws IOException, ServletException;
}
