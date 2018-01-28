package javax.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Response 的生命周期
 * 每个响应对象是只有当在servlet的service方法的范围内或在filter的doFilter 方法范围内是有效的，除非该
 * 组件关联的请求对象已经开启异步处理。如果相关的请求已经启动异步处理，那么直到AsyncContext 的
 * complete 方法被调用，请求对象一直有效。为了避免响应对象创建的性能开销，容器通常回收响应对象。
 * 在相关的请求的startAsync 还没有调用时，开发人员必须意识到保持到响应对象引用，超出之上描述的范
 * 围可能导致不确定的行为。
 * page-44
 */
public interface ServletResponse {
    String getCharacterEncoding();

    String getContentType();

    ServletOutputStream getOutputStream() throws IOException;

    /**
     * 在 ServletResponse接口的getWriter方法被调用或响应被提交之前，如果servlet没有指定字符编码，默认使
     * 用ISO-8859-1。
     * 如果使用的协议提供了一种这样做的方式，容器必须传递servlet 响应的writer使用的locale和字符编码到
     * 客户端。使用HTTP 的情况下，locale可以使用Content-Language header 传递，字符编码可以作为用于指定
     * 文本媒体类型的Content-Type header 的一部分传递。注意，如果没有指定content type，字符编码不能通过
     * HTTP header 传递；但是仍使用它来编码通过servlet 响应的writer写的文本。
     * page-44
     *
     * @return
     * @throws IOException
     */
    PrintWriter getWriter() throws IOException;

    void setCharacterEncoding(String var1);

    /**
     * 当响应被关闭时，容器必须立即刷出响应缓冲区中的所有剩余的内容到客户端。
     * 以下事件表明servlet满足了请求且响应对象即将关闭：
     * ■ servlet的service方法终止。
     * ■ 响应的setContentLength或setContentLengthLong方法指定了大于零的内容量，且已经写入到响应。
     * ■ sendError 方法已调用。
     * ■ sendRedirect 方法已调用。
     * ■ AsyncContext 的complete 方法已调用。
     * page-44
     *
     * @param var1
     */
    void setContentLength(int var1);

    void setContentLengthLong(long var1);

    void setContentType(String var1);

    /**
     * Servlet可以请求setBufferSize方法设置一个最佳的缓冲大小。不一定分配servlet 请求大小的缓冲区，但至
     * 少与请求的大小一样大。这允许容器重用一组固定大小的缓冲区，如果合适，可以提供一个比请求时更大
     * 的缓冲区。该方法必须在使用ServletOutputStream 或Writer写任何内容之前调用。如果已经写了内容或响
     * 应对象已经提交，则该方法必须抛出IllegalStateException。
     * page-41
     *
     * @param var1
     */
    void setBufferSize(int var1);

    /**
     * Servlet容器允许但不必为了提高效率而缓冲到客户端的输出。典型的服务器默认都是缓冲的，但允许servlet
     * 指定缓冲参数。
     * ServletResponse 接口的如下方法允许servlet访问和设置缓冲信息：
     * ■ getBufferSize
     * ■ setBufferSize
     * ■ isCommitted
     * ■ reset
     * ■ resetBuffer
     * ■ flushBuffer
     * 不管Servlet 使用的是一个ServletOutputStream还是一个Writer，ServletResponse 接口提供的这些方法允许
     * 执行缓冲操作。
     * getBufferSize方法返回使用的底层缓冲区大小。如果没有使用缓冲，该方法必须返回一个int值0。
     * page-41
     *
     * @return
     */
    int getBufferSize();

    void flushBuffer() throws IOException;

    void resetBuffer();

    /**
     * isCommitted 方法返回一个表示是否有任何响应字节已经返回到客户端的boolean 值。flushBuffer 方法强制
     * 刷出缓冲区的内容到客户端。
     * page-41
     *
     * @return
     */
    boolean isCommitted();

    /**
     * 当响应没有提交时，reset方法清空缓冲区的数据。头信息，状态码和在调用reset之前servlet 调用getWriter
     * 或getOutputStream 设置的状态也必须被清空。如果响应没有被提交，resetBuffer 方法将清空缓冲区中的内
     * 容，但不清空请求头和状态码。
     * 如果响应已经提交并且reset或resetBuffer 方法已被调用，则必须抛出IllegalStateException，响应及它关联
     * 的缓冲区将保持不变。
     * 当使用缓冲区时，容器必须立即刷出填满的缓冲区内容到客户端。如果这是最早发送到客户端的数据，且
     * 认为响应被提交了。
     * page-41
     */
    void reset();

    /**
     * Servlet 应设置response 的locale 和字符集。使用ServletResponse.setLocale 方法设置locale。该方法可以重
     * 复的调用；但响应被提交后调用该方法不会产生任何作用。如果在页面被提交之前Servlet没有设置locale，
     * 容器的默认locale将用来确定响应的locale，但是没有制定与客户端通信的规范，例如使用HTTP 情况下的
     * Content-Language header。
     * <locale-encoding-mapping-list>
     * <locale-encoding-mapping>
     * <locale>ja</locale>
     * <encoding>Shift_JIS</encoding>
     * </locale-encoding-mapping>
     * </locale-encoding-mapping-list>
     * <p>
     * 如果该元素不存在或没有提供mapping，setLocale 使用容器依赖的mapping。setCharacterEncoding，
     * setContentType和setLocale方法可以被重复的调用来改变字符编码。如果在servlet 响应的getWriter方法已
     * 经调用之后或响应被提交之后，调用相关方法设置字符编码将没有任何作用。只有当给定的content type字
     * 符串提供了一个charset 属性值，调用setContentType 可以设置字符编码。只有当既没有调用
     * setCharacterEncoding也没有调用setContentType去设置字符编码之前调用setLocale可以设置字符编码。
     * page-43
     *
     * @param var1
     */
    void setLocale(Locale var1);

    Locale getLocale();
}
