package javax.servlet;

import java.io.IOException;
import java.io.InputStream;

public abstract class ServletInputStream extends InputStream {
    protected ServletInputStream() {
    }

    public int readLine(byte[] b, int off, int len) throws IOException {
        if (len <= 0) {
            return 0;
        } else {
            int count = 0;

            int c;
            while ((c = this.read()) != -1) {
                b[off++] = (byte) c;
                ++count;
                if (c == 10 || count == len) {
                    break;
                }
            }

            return count > 0 ? count : -1;
        }
    }

    /**
     * 与ServletReader/ServletInputStream 相关的请求的所有数据已经读取完时isFinished
     * 方法返回true。否则返回false。
     * page-33
     *
     * @return
     */
    public abstract boolean isFinished();

    /**
     * 如果可以无阻塞地读取数据isReady方法返回true。如果没有数据可以无阻塞地读取该
     * 方法返回false。如果isReady方法返回false，调用read方法是非法的，且必须抛出IllegalStateException。
     * page-33
     *
     * @return
     */
    public abstract boolean isReady();

    /**
     * 设置上述定义的ReadListener，调用它以非阻塞的方式读取数
     * 据。一旦把监听器与给定的ServletInputStream关联起来，当数据可以读取，所有的数据都读取完或如果处
     * 理请求时发生错误，容器调用ReadListener 的方法。注册一个ReadListener 将启动非阻塞IO。在那时切换
     * 到传统的阻塞IO 是非法的，且必须抛出IllegalStateException。在当前请求范围内，随后调用setReadListener
     * 是非法的且必须抛出IllegalStateException。
     * page-33
     *
     * @param var1
     */
    public abstract void setReadListener(ReadListener var1);
}
