package javax.servlet.http;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletResponse;

public interface HttpServletResponse extends ServletResponse {
    int SC_CONTINUE = 100;
    int SC_SWITCHING_PROTOCOLS = 101;
    int SC_OK = 200;
    int SC_CREATED = 201;
    int SC_ACCEPTED = 202;
    int SC_NON_AUTHORITATIVE_INFORMATION = 203;
    int SC_NO_CONTENT = 204;
    int SC_RESET_CONTENT = 205;
    int SC_PARTIAL_CONTENT = 206;
    int SC_MULTIPLE_CHOICES = 300;
    int SC_MOVED_PERMANENTLY = 301;
    int SC_MOVED_TEMPORARILY = 302;
    int SC_FOUND = 302;
    int SC_SEE_OTHER = 303;
    int SC_NOT_MODIFIED = 304;
    int SC_USE_PROXY = 305;
    int SC_TEMPORARY_REDIRECT = 307;
    int SC_BAD_REQUEST = 400;
    int SC_UNAUTHORIZED = 401;
    int SC_PAYMENT_REQUIRED = 402;
    int SC_FORBIDDEN = 403;
    int SC_NOT_FOUND = 404;
    int SC_METHOD_NOT_ALLOWED = 405;
    int SC_NOT_ACCEPTABLE = 406;
    int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
    int SC_REQUEST_TIMEOUT = 408;
    int SC_CONFLICT = 409;
    int SC_GONE = 410;
    int SC_LENGTH_REQUIRED = 411;
    int SC_PRECONDITION_FAILED = 412;
    int SC_REQUEST_ENTITY_TOO_LARGE = 413;
    int SC_REQUEST_URI_TOO_LONG = 414;
    int SC_UNSUPPORTED_MEDIA_TYPE = 415;
    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    int SC_EXPECTATION_FAILED = 417;
    int SC_INTERNAL_SERVER_ERROR = 500;
    int SC_NOT_IMPLEMENTED = 501;
    int SC_BAD_GATEWAY = 502;
    int SC_SERVICE_UNAVAILABLE = 503;
    int SC_GATEWAY_TIMEOUT = 504;
    int SC_HTTP_VERSION_NOT_SUPPORTED = 505;

    void addCookie(Cookie var1);

    boolean containsHeader(String var1);

    String encodeURL(String var1);

    String encodeRedirectURL(String var1);

    /**
     * @deprecated
     */
    String encodeUrl(String var1);

    /**
     * @deprecated
     */
    String encodeRedirectUrl(String var1);

    /**
     * sendError 方法将设置适当的header 和内容体用于返回给客户端返回错误消息。可以sendError 方法提供一
     * 个可选的String参数用于指定错误的内容体。
     * 如果响应已经提交并终止，这两个方法将对提交的响应产生负作用。这两个方法调用后servlet将不会产生
     * 到客户端的后续的输出。这两个方法调用后如果有数据继续写到响应，这些数据被忽略。如果数据已经写
     * 到响应的缓冲区，但没有返回到客户端（例如，响应没有提交），则响应缓冲区中的数据必须被清空并使用
     * 这两个方法设置的数据替换。如果想要已提交，这两个方法必须抛出IllegalStateException。
     * page-43
     *
     * @param var1
     * @param var2
     * @throws IOException
     */
    void sendError(int var1, String var2) throws IOException;

    void sendError(int var1) throws IOException;

    /**
     * sendRedirect 方法将设置适当的header和内容体将客户端重定向到另一个地址。使用相对URL路径调用该
     * 方法是合法的，但是底层的容器必须将传回到客户端的相对地址转换为全路径URL。无论出于什么原因，
     * 如果给定的URL 是不完整的，且不能转换为一个有效的URL， 那么该方法必须抛出
     * IllegalArgumentException。
     * page-43
     *
     * @param var1
     * @throws IOException
     */
    void sendRedirect(String var1) throws IOException;

    void setDateHeader(String var1, long var2);

    void addDateHeader(String var1, long var2);

    /**
     * Servlet可以使用如下HttpServletResponse 接口中的方法设置HTTP 响应头：
     * ■ setHeader
     * ■ addHeader
     * setHeader方法设置一个给定名字和值的header。之前的header将被新的header替换。如果已经存在同名的
     * header值的set，set中的值会被清空并用新的值替换。
     * page-42
     *
     * @param var1
     * @param var2
     */
    void setHeader(String var1, String var2);

    /**
     * addHeader方法使用给定的名字添加一个header值到set。如果没有header与给定的名字关联，则创建一个
     * 新的set。
     * page-42
     *
     * @param var1
     * @param var2
     */
    void addHeader(String var1, String var2);

    /**
     * Header可能包含表示int或Date对象的数据。以下HttpServletResponse接口提供的便利方法允许Servlet 对
     * 适当的数据类型用正确的格式设置一个header：
     * ■ setIntHeader
     * ■ setDateHeader
     * ■ addIntHeader
     * ■ addDateHeader
     * 为了成功的传回给客户端，header必须在响应提交前设置。响应提交后的Header 设置将被servlet 容器忽略。
     * page-42
     *
     * @param var1
     * @param var2
     */
    void setIntHeader(String var1, int var2);

    void addIntHeader(String var1, int var2);

    void setStatus(int var1);

    /**
     * @deprecated
     */
    void setStatus(int var1, String var2);

    int getStatus();

    String getHeader(String var1);

    Collection<String> getHeaders(String var1);

    Collection<String> getHeaderNames();
}
