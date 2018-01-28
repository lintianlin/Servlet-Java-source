package javax.servlet;

import java.io.IOException;

/**
 * 构建Web应用时，把请求转发给另一个servlet处理、或在response中包含另一个servlet的输出通常是很有
 * 用的。RequestDispatcher接口提供了一种机制来实现这种功能。
 * 当请求启用异步处理时，AsyncContext允许用户将这个请求转发到servlet 容器。
 * page-75
 */
public interface RequestDispatcher {
    String FORWARD_REQUEST_URI = "javax.servlet.forward.request_uri";
    String FORWARD_CONTEXT_PATH = "javax.servlet.forward.context_path";
    String FORWARD_PATH_INFO = "javax.servlet.forward.path_info";
    String FORWARD_SERVLET_PATH = "javax.servlet.forward.servlet_path";
    String FORWARD_QUERY_STRING = "javax.servlet.forward.query_string";
    String INCLUDE_REQUEST_URI = "javax.servlet.include.request_uri";
    String INCLUDE_CONTEXT_PATH = "javax.servlet.include.context_path";
    String INCLUDE_PATH_INFO = "javax.servlet.include.path_info";
    String INCLUDE_SERVLET_PATH = "javax.servlet.include.servlet_path";
    String INCLUDE_QUERY_STRING = "javax.servlet.include.query_string";
    String ERROR_EXCEPTION = "javax.servlet.error.exception";
    String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    String ERROR_MESSAGE = "javax.servlet.error.message";
    String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    String ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    /**
     * RequestDispatcher接口的forward 方法，只有在没有输出提交到向客户端时，通过正在被调用的servlet调用。
     * 如果response缓冲区中存在尚未提交的输出数据，这些数据内容必须在目标servlet的service 方法调用前清
     * 除。如果response已经提交，必须抛出一个IllegalStateException异常。
     * http://jinnianshilongnian.iteye.com/
     * http://jinnianshilongnian.iteye.com/
     * 77
     * request对象暴露给目标servlet 的路径元素（path elements）必须反映获得RequestDispatcher使用的路径。
     * 唯一例外的是，如果RequestDispatcher 是通过getNamedDispatcher 方法获得。这种情况下，request 对象的
     * 路径元素必须反映这些原始请求。
     * 在 RequestDispatcher 接口的forward 方法无异常返回之前，响应的内容必须被发送和提交，且由Servlet 容
     * 器关闭，除非请求处于异步模式。如果RequestDispatcher.forward()的目标发生错误，异常信息会传回所有
     * 调用它经过的过滤器和servlet，且最终传回给容器。
     * page-77
     *
     * @param var1
     * @param var2
     * @throws ServletException
     * @throws IOException
     */
    void forward(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    /**
     * RequestDispatcher 接口的include 方法可能随时被调用。Include 方法的目标servlet 能够访问request 对象的
     * 各个方法（all aspects），但是使用response 对象的方法会受到更多限制。
     * 它只能把信息写到response 对象的ServletOutputStream或Writer 中，或提交在最后写保留在response 缓冲
     * 区中的内容，或通过显式地调用ServletResponse接口的flushBuffer方法。它不能设置响应头部信息或调用
     * 任何影响响应头部信息的方法，HttpServletRequest.getSession()和HttpServletRequest.getSession(boolean)方法
     * 除外。任何试图设置头部信息必须被忽略， 任何调用HttpServletRequest.getSession() 和
     * HttpServletRequest.getSession(boolean)方法将需要添加一个Cookie响应头部信息，如果响应已经提交，必须
     * 抛出一个IllegalStateException 异常。
     * 如果默认的servlet 是RequestDispatch.include()的目标servlet，而且请求的资源不存在，那么默认的servlet
     * 必须抛出FileNotFoundException 异常。如果这个异常没有被捕获和处理，以及响应还未提交，则响应状态
     * 码必须被设置为500。
     * page-76
     *
     * @param var1
     * @param var2
     * @throws ServletException
     * @throws IOException
     */
    void include(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
}
