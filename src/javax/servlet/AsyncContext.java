package javax.servlet;

/**
 * 该类表示在ServletRequest 启动的异步操作执行上下文，AsyncContext 由之前描述的
 * ServletRequest.startAsync 创建并初始化
 * page-20
 */
public interface AsyncContext {
    String ASYNC_REQUEST_URI = "javax.servlet.async.request_uri";
    String ASYNC_CONTEXT_PATH = "javax.servlet.async.context_path";
    String ASYNC_PATH_INFO = "javax.servlet.async.path_info";
    String ASYNC_SERVLET_PATH = "javax.servlet.async.servlet_path";
    String ASYNC_QUERY_STRING = "javax.servlet.async.query_string";

    /**
     * 返回调用startAsync 用于初始化AsyncContext 的请求对象。当在
     * 异步周期之前调用了complete 或任意一个dispatch 方法，调用getRequest 将抛出IllegalStateException
     * page-20
     *
     * @return
     */
    ServletRequest getRequest();

    /**
     * 返回调用startAsync用于初始化AsyncContext 的响应对象。当
     * 在异步周期之前调用了complete 或任意一个dispatch 方法，调用getResponse 将抛出
     * IllegalStateException。
     * page-20
     *
     * @return
     */
    ServletResponse getResponse();

    /**
     * 该方法检查AsyncContext 是否以原始的请求和响
     * 应对象调用ServletRequest.startAsync() 完成初始化的， 或者是否通过调用
     * ServletRequest.startAsync(ServletRequest, ServletResponse)完成初始化的，且传入的ServletRequest 和
     * ServletResponse 参数都不是应用提供的包装器，这样的话将返回true。如果AsyncContext 使用包装的
     * 请求及（或）响应对象调用ServletRequest.startAsync(ServletRequest, ServletResponse)完成初始化，那
     * 么将返回false。在请求处于异步模式后，该信息可以被出站方向调用的过滤器使用，用于决定是否在
     * 入站调用时添加的请求及（或）响应包装器需要在异步操作期间被维持或者被释放。
     * page-23
     *
     * @return
     */
    boolean hasOriginalRequestAndResponse();

    /**
     * 一个简便方法，使用初始化AsyncContext 时的请求和响应进行分派，如下所
     * 示。如果使用startAsync(ServletRequest, ServletResponse)初始化AsyncContext，且传入的请求是
     * HttpServletRequest的一个实例，则使用HttpServletRequest.getRequestURI()返回的URI进行分派。否则
     * 分派的是容器最后分派的请求URI
     * page-21
     */
    void dispatch();

    /**
     * 将用于初始化AsyncContext的请求和响应分派到指定的路径的资
     * 源。该路径以相对于初始化AsyncContext 的ServletContext 进行解析。与请求查询方法相关的所有路
     * 径，必须反映出分派的目标，同时原始请求的URI，上下文，路径信息和查询字符串都可以从请求属
     * 性中获取，请求属性定义在9-98 页的9.7.2 章节，“分派的请求参数”。这些属性必须反映最原始的路
     * 径元素，即使在多次分派之后
     * page-21
     *
     * @param var1
     */
    void dispatch(String var1);

    /**
     * 将用于初始化AsyncContext的请求和响应分
     * 派到指定ServletContext 的指定路径的资源。
     * page-22
     *
     * @param var1
     * @param var2
     */
    void dispatch(ServletContext var1, String var2);

    /**
     * 如果调用了request.startAsync，则必须调用该方法以完成异步处理并提交和
     * 关闭响应。如果请求分派到一个不支持异步操作的Servlet，或者由AsyncContext.
     * dispatch 调用的目标servlet 之后没有调用startAsync，则complete 方法会由容器调用。这种情况下，容器负责当servlet 的
     * service 方法一退出就调用complete()。如果 startAsync 没有被调用则必须抛出IllegalStateException。
     * 在调用ServletRequest.startAsync() 或ServletRequest.startAsync(ServletRequest, ServletResponse) 之后且
     * 在调用任意dispatch方法之前的任意时刻调用complete()是合法的。在调用了startAsync方法的容器启
     * 动的分派没有返回到容器之前该方法的调用将没有任何作用。AsyncListener.onComplete(AsyncEvent)
     * 的调用将被延迟到容器启动的分派返回到容器之后。
     * page-23
     */
    void complete();

    /**
     * 该方法导致容器分派一个线程，该线程可能来自托管的线程池，用于
     * 运行指定的Runnable对象。容器可能传播相应的上下文信息到该Runnable 对象。
     * page-23
     *
     * @param var1
     */
    void start(Runnable var1);

    /**
     * 注册给定的监听器用于接收onTimeout, onError, onComplete
     * 或onStartAsync通知。前三个是与最近通过调用任意ServletRequest.startAsync方法启动的异步周期相
     * 关联的。onStartAsync 是与通过任意ServletRequest.startAsync 启动的一个新的异步周期相关联的。异
     * 步监听器将以它们添加到请求时的顺序得到通知。当AsyncListener 接收到通知，如果在请求时调用
     * startAsync(req, res) 或startAsync()，从AsyncEvent 会得到同样的请求和响应对象。请求和响应对象可
     * 以是或者不是被包装的。异步监听器将以它们添加到请求时的顺序得到通知。容器启动的分派在异步
     * 周期启动后返回到容器后，或者在一个新的异步周期启动之前，调用该方法是非法的，将抛出
     * IllegalStateException。
     * page-21
     *
     * @param var1
     */
    void addListener(AsyncListener var1);

    /**
     * 注册一个用于
     * 接收的onTimeout, onError, onComplete 或onStartAsync 通知的监听器。前三个是与最近通过调用任意
     * ServletRequest.startAsync 方法启动的异步周期相关联的。onStartAsync 是与通过任意
     * ServletRequest.startAsync 启动的一个新的异步周期相关联的。异步监听器将以它们添加到请求时的顺
     * 序得到通知。当AsyncListener 得到通知， 传入到该方法的请求响应对象与
     * AsyncEvent.getSuppliedRequest()和AsyncEvent.getSuppliedResponse()是完全相同的。不应该对这些对象
     * 进行读取或写入，因为自从注册了AsyncListener后可能发生了额外的包装，不过可以被用来按顺序释
     * 放与它们关联的资源。容器启动的分派在异步周期启动后返回到容器后，或者在一个新的异步周期启
     * 动之前，调用该方法是非法的，将抛出IllegalStateException。
     * page-21
     *
     * @param var1
     * @param var2
     * @param var3
     */
    void addListener(AsyncListener var1, ServletRequest var2, ServletResponse var3);

    /**
     * 实例化指定的AsyncListener类。返
     * 回的AsyncListener 实例在使用下文描述的addListener 方法注册到AsyncContext 之前可能需要进一步
     * 的自定义。给定的AsyncListener 类必须定义一个用于实例化的空参构造器，该方法支持适用于
     * AsyncListener 的所有注解。
     * page-21
     *
     * @param var1
     * @param <T>
     * @return
     * @throws ServletException
     */
    <T extends AsyncListener> T createListener(Class<T> var1) throws ServletException;

    /**
     * 设置异步处理的超时时间，以毫秒为单位。该方法
     * 调用将覆盖容器设置的超时时间。如果没有调用setTimeout 设置超时时间，将使用容器默认的超时时
     * 间。一个小于等于0的数表示异步操作将永不超时。当调用任意一个ServletRequest.startAsync方法时，
     * 一旦容器启动的分派返回到容器，超时时间将应用到AsyncContext。当在异步周期开始时容器启动的分派
     * 已经返回到容器后，再设置超时时间是非法的，这将抛出一个IllegalStateException异常。
     * page-20
     *
     * @param var1
     */
    void setTimeout(long var1);

    /**
     * 获取AsyncContext关联的超时时间的毫秒值。该方法返回容器默认的超时
     * 时间，或最近一次调用setTimeout设置超时时间。
     * page-21
     *
     * @return
     */
    long getTimeout();
}
