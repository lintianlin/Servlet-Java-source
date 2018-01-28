package javax.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 除 ServletContainerInitializer外，我们还有一个注解—HandlesTypes。在ServletContainerInitializer 实现上的
 * HandlesTypes 注解用于表示感兴趣的一些类，它们可能指定了HandlesTypes 的value 中的注解（类型、方
 * 法或自动级别的注解），或者是其类型的超类继承/实现了这些类之一。无论是否设置了metadata-complete，
 * HandlesTypes注解将应用。
 * 当检测一个应用的类看是否它们匹配ServletContainerInitializer的HandlesTypes指定的条件时，如果应用的
 * 一个或多个可选的JAR 包缺失，容器可能遇到类装载问题。由于容器不能决定是否这些类型的类装载失败
 * 将阻止应用正常工作，它必须忽略它们，同时也提供一个将记录它们的配置选项。
 * 如果ServletContainerInitializer 实现没有@HandlesTypes注解，或如果没有匹配任何指定的HandlesType，那
 * 么它会为每个应用使用null 值的集合调用一次。这将允许initializer基于应用中可用的资源决定是否需要初
 * 始化Servlet/Filter。
 * 在任何Servlet Listener 的事件被触发之前，当应用正在启动时，ServletContainerInitializer的onStartup方法
 * 将被调用。
 * ServletContainerInitializer’s 的onStartup得到一个类的Set，其或者继承/实现initializer表示感兴趣的类，或
 * 者它是使用指定在@HandlesTypes 注解中的任意类注解的。
 * page-73
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlesTypes {
    Class<?>[] value();
}
