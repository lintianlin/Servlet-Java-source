package javax.servlet;

import java.io.IOException;

/**
 *
 */
public interface Servlet {

    /**
     * 容器通过调用Servlet 实例的init 方法完成初始化，init方法定义在Servlet 接口中，
     * 并且提供一个唯一的ServletConfig接口实现的对象作为参数，该对象每个Servlet实例一个
     *
     * @param var1
     * @throws ServletException
     */
    void init(ServletConfig var1) throws ServletException;

    ServletConfig getServletConfig();

    void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    String getServletInfo();

    void destroy();
}
