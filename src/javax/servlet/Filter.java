package javax.servlet;

import java.io.IOException;

/**
 * 过滤器（Filter）是Java 组件，允许运行过程中改变进入资源的请求和资源返回的响应中的有效负载和header
 * 信息。
 * 本章描述了Java Servlet v3.0 API类和方法，它们提供了一种轻量级的框架用于过滤动态和静态内容。还描
 * 述了如何在Web应用配置Filter，它们实现的约定和语义。
 * 网上提供了Servlet 过滤器的API文档。过滤器的配置语法在第14章的“部署描述符”中的部署描述符模
 * 式部分给出。当阅读本章时，读者应该是一这些资源作为参考。
 * <p>
 * 什么是过滤器
 * 过滤器是一种代码重用的技术，它可以改变HTTP 请求的内容，响应，及header 信息。过滤器通常不产生
 * 响应或像servlet 那样对请求作出响应，而是修改或调整到资源的请求，修改或调整来自资源的响应。
 * 过滤器可以作用于动态或静态内容。这章说的动态和静态内容指的是Web资源。
 * 供开发人员使用的过滤器功能有如下几种类型：
 * ■ 在执行请求之前访问资源。
 * ■ 在执行请求之前处理资源的请求。
 * ■ 用请求对象的自定义版本包装请求对请求的header 和数据进行修改。
 * ■ 用响应对象的自定义版本包装响应对响应的header 和数据进行修改。
 * ■ 拦截资源调用之后的调用。
 * ■ 作用在Servlet，一组Servlet，或静态内容上的零个，一个或多个拦截器按指定的顺序执行。
 * <p>
 * 过滤器组件示例
 * ■ 验证过滤器
 * ■ 日志记录和审计过滤器
 * ■ 图像转换过滤器
 * ■ 数据压缩过滤器
 * ■ 加密过滤器
 * ■ 词法（Tokenizing）过滤器
 * ■ 触发资源访问事件过滤器
 * ■ 转换XML内容的XSL/T 过滤器
 * ■ MIME-类型链过滤器
 * ■ 缓存过滤器
 * <p>
 * 主要概念
 * 本章描述了过滤器模型的主要概念。
 * 应用开发人员通过实现javax.servlet.Filter 接口并提供一个公共的空参构造器来创建过滤器。该类及构建
 * Web 应用的静态资源和Servlet打包在Web应用归档文件中。Filter在部署描述符中通过<filter>元素声明。
 * 一个过滤器或一组过滤器可以通过在部署描述符中定义<filter-mapping>来为调用配置。可以使用servlet 的
 * 逻辑视图名把过滤器映射到一个特定的servlet，或者使用URL 模式把一组Servlet 和静态内容资源映射到
 * 过滤器。
 * <p>
 * 过滤器生命周期
 * 在Web应用部署之后，在请求导致容器访问Web资源之前，容器必须找到过滤器列表并按照如上所述的应
 * 用到Web 资源。容器必须确保它为过滤器列表中的每一个都实例化了一个适当类的过滤器,并调用其
 * init(FilterConfig config)方法。过滤器可能会抛出一个异常,以表明它不能正常运转。如果异常的类型是
 * UnavailableException，容器可以检查异常的isPermanent 属性并可以选择稍候重试过滤器。
 * 在部署描述符中声明的每个<filter>在每个JVM的容器中仅实例化一个实例。容器提供了声明在过滤器的部
 * 署描述符的过滤器config（译者注：FilterConfig），对Web 应用的ServletContext 的引用，和一组初始化参
 * 数。
 * 当容器接收到传入的请求时，它将获取列表中的第一个过滤器并调用doFilter方法，传入ServletRequest 和
 * ServletResponse，和一个它将使用的FilterChain 对象的引用。
 * 过滤器的doFilter方法通常会被实现为如下或如下形式的子集：
 * 1. 该方法检查请求的头。
 * 2. 该方法可以用自定义的ServletRequest或HttpServletRequest实现包装请求对象为了修改请求的头或数据。
 * 3. 该方法可以用自定义的ServletResponse 或HttpServletResponse实现包装传入doFilter方法的响应对象用
 * 于修改响应的头或数据。
 * 4. 该过滤器可以调用过滤器链中的下一个实体。下一个实体可能是另一个过滤器，或者如果当前调用的过
 * 滤器是该过滤器链配置在部署描述符中的最后一个过滤器，下一个实体是目标Web 资源。调用FilterChain
 * 对象的doFilter 方法将影响下一个实体的调用，且传入的它被调用时请求和响应，或传入它可能已经创建
 * 的包装版本。
 * 由容器提供的过滤器链的doFilter方法的实现，必须找出过滤器链中的下一个实体并调用它的doFilter方法，
 * 传入适当的请求和响应对象。另外，过滤器链可以通过不调用下一个实体来阻止请求，离开过滤器负责填
 * 充响应对象。
 * service方法必须和应用到servlet 的所有过滤器运行在同一个线程中。
 * 5. 过滤器链中的下一个过滤器调用之后，过滤器可能检查响应的头。
 * 6. 另外，过滤器可能抛出一个异常以表示处理过程中出错了。如果过滤器在doFilter 处理过程中抛出
 * UnavailableException，容器必须停止处理剩下的过滤器链。如果异常没有标识为永久的，它或许选择稍候
 * 重试整个链。
 * 7. 当链中的最后的过滤器被调用，下一个实体访问的是链最后的目标servlet或资源。
 * http://jinnianshilongnian.iteye.com/
 * http://jinnianshilongnian.iteye.com/
 * 47
 * 8. 在容器能把服务中的过滤器实例移除之前，容器必须先调用过滤器的destroy 方法以便过滤器释放资源
 * 并执行其他的清理工作。
 * 6.2.2 包装请求和响应
 * 过滤器的核心概念是包装请求或响应，以便它可以覆盖行为执行过滤任务。在这个模型中，开发人员不仅
 * 可以覆盖请求和响应对象上已有的方法，也能提供新的API以适用于对过滤器链中剩下的过滤器或目标web
 * 资源做特殊的过滤任务。例如，开发人员可能希望用更高级别的输出对象如output stream 或writer来扩展
 * 响应对象，如API，允许DOM对象写回客户端。
 * 为了支持这种风格的过滤器，容器必须支持如下要求。当过滤器调用容器的过滤器链实现的doFilter 方法
 * 时，容器必须确保请求和响应对象传到过滤器链中的下一个实体，或如果过滤器是链中最后一个，将传入
 * 目标web资源，且与调用过滤器传入doFilter方法的对象是一样的。
 * 当调用者包装请求或响应对象时，对包装对象的要求同样适用于从servlet 或过滤器到
 * RequestDispatcher.forward 或RequestDispatcher.include 的调用。在这种情况下，调用servlet 看到的请求和
 * 响应对象与调用servlet或过滤器传入的包装对象必须是一样的。
 * 6.2.3 过滤器环境
 * 可以使用部署描述符中的<init-params>元素把一组初始化参数关联到过滤器。这些参数的名字和值在过滤
 * 器运行期间可以使用过滤器的FilterConfig对象的getInitParameter和getInitParameterNames方法得到。另外，
 * FilterConfig提供访问Web应用的ServletContext用于加载资源，记录日志，在ServletContext 的属性列表存
 * 储状态。链中最后的过滤器和目标servlet或资源必须执行在同一个调用线程。
 * page-45
 */
public interface Filter {
    void init(FilterConfig var1) throws ServletException;

    void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException;

    void destroy();
}
