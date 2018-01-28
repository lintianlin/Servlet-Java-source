package javax.servlet;

import java.io.IOException;
import java.util.EventListener;

/**
 * 容器必须线程安全的访问ReadListener中的方法。
 * page-33
 */
public interface ReadListener extends EventListener {
    /**
     * onDataAvailable().当可以从传入的请求流中读取数据时ReadListener的onDataAvailable方法被调用。当数
     * 据可读时容器初次调用该方法。当且仅当下面描述的ServletInputStream的isReady方法返回false，容器随
     * 后将调用onDataAvailable 方法。
     * page-32
     *
     * @throws IOException
     */
    void onDataAvailable() throws IOException;


    /**
     * 当读取完注册了此监听器的ServletRequest的所有数据时调用onAllDataRead方法。
     * page-32
     *
     * @throws IOException
     */
    void onAllDataRead() throws IOException;

    /**
     * 处理请求时如果有任何错误或异常发生时调用onError方法。
     *
     * @param var1
     */
    void onError(Throwable var1);
}
