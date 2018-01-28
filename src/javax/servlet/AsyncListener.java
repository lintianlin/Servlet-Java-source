package javax.servlet;

import java.io.IOException;
import java.util.EventListener;

public interface AsyncListener extends EventListener {
    /**
     * 用于通知监听器在Servlet上启动的异步操作完成了。
     * page-24
     *
     * @param var1
     * @throws IOException
     */
    void onComplete(AsyncEvent var1) throws IOException;

    void onTimeout(AsyncEvent var1) throws IOException;

    /**
     * 用于通知监听器异步操作未能完成。
     * page-24
     *
     * @param var1
     * @throws IOException
     */
    void onError(AsyncEvent var1) throws IOException;

    /**
     * 用 于 通知监听器正在通过调用一个
     * ServletRequest.startAsync方法启动一个新的异步周期。正在被重新启动的异步操作对应的AsyncContext
     * 可以通过调用给定的event 上调用AsyncEvent.getAsyncContext获取。
     * page-24
     *
     * @param var1
     * @throws IOException
     */
    void onStartAsync(AsyncEvent var1) throws IOException;
}