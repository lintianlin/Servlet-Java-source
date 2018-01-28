package javax.servlet.http;

import java.util.EventListener;

public interface HttpSessionBindingListener extends EventListener {
    /**
     * servlet可以按名称绑定对象属性到HttpSession实现，任何绑定到会话的对象可用于任意其他的Servlet，其
     * 属于同一个ServletContext且处理属于相同会话中的请求。
     * 一 些对象可能需要在它们被放进会话或从会话中移除时得到通知。这些信息可以从
     * HttpSessionBindingListener 接口实现的对象中获取。这个接口定义了以下方法，用于标识一个对象被绑定到
     * 会话或从会话解除绑定时。
     * ■ valueBound
     * ■ valueUnbound
     * 在对象对HttpSession接口的getAttribute 方法可用之前valueBound 方法必须被调用。在对象对HttpSession
     * 接口的getAttribute 方法不可用之后valueUnbound 方法必须被调用。
     * page-53
     *
     * @param var1
     */
    void valueBound(HttpSessionBindingEvent var1);

    void valueUnbound(HttpSessionBindingEvent var1);
}