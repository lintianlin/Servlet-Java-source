package javax.servlet;

import java.util.Set;

/**
 * ServletContainerInitializer 类通过jar services API 查找。对于每一个应用，应用启动时，由容器创建一个
 * ServletContainerInitializer 实例。框架提供的ServletContainerInitializer 实现必须绑定在jar 包的
 * META-INF/services 目录中的一个叫做javax.servlet.ServletContainerInitializer的文件，根据jar services API，
 * 指定ServletContainerInitializer 的实现。
 * page-73
 */
public interface ServletContainerInitializer {
    void onStartup(Set<Class<?>> var1, ServletContext var2) throws ServletException;
}