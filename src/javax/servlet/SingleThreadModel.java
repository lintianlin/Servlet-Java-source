package javax.servlet;

/**
 * @deprecated
 */

/**
 * SingleThreadModel 接口的作用是保证一个特定servlet实例的service方法在一个时刻仅能被一个线程执行，
 * 一定要注意，此保证仅适用于每一个servlet实例，因此容器可以选择池化这些对象。有些对象可以在同一
 * 时刻被多个servlet 实例访问，如HttpSession 实例，可以在一个特定的时间对多个Servlet 可用，包括那些
 * 实现了SingleThreadModel接口的Servlet。
 */
public interface SingleThreadModel {
}
