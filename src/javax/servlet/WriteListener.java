package javax.servlet;

import java.io.IOException;
import java.util.EventListener;

public interface WriteListener extends EventListener {
    /**
     * 当一个WriteListener注册到ServletOutputStream时，当可以写数据时该方法
     * 将被容器首次调用。当且仅当下边描述的ServletOutputStream 的isReady 方法返回false，容器随后将
     * 调用该方法。
     * page-42
     *
     * @throws IOException
     */
    void onWritePossible() throws IOException;

    /**
     * 当处理响应过程中出现错误时回调。
     * page-42
     *
     * @param var1
     */
    void onError(Throwable var1);
}