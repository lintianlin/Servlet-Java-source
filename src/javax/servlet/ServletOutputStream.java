package javax.servlet;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 容器必须线程安全的访问WriteListener中的方法。
 * page-43
 */
public abstract class ServletOutputStream extends OutputStream {
    private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.LocalStrings");

    protected ServletOutputStream() {
    }

    public void print(String s) throws IOException {
        if (s == null) {
            s = "null";
        }

        int len = s.length();

        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if ((c & '\uff00') != 0) {
                String errMsg = lStrings.getString("err.not_iso8859_1");
                Object[] errArgs = new Object[]{c};
                errMsg = MessageFormat.format(errMsg, errArgs);
                throw new CharConversionException(errMsg);
            }

            this.write(c);
        }

    }

    public void print(boolean b) throws IOException {
        String msg;
        if (b) {
            msg = lStrings.getString("value.true");
        } else {
            msg = lStrings.getString("value.false");
        }

        this.print(msg);
    }

    public void print(char c) throws IOException {
        this.print(String.valueOf(c));
    }

    public void print(int i) throws IOException {
        this.print(String.valueOf(i));
    }

    public void print(long l) throws IOException {
        this.print(String.valueOf(l));
    }

    public void print(float f) throws IOException {
        this.print(String.valueOf(f));
    }

    public void print(double d) throws IOException {
        this.print(String.valueOf(d));
    }

    public void println() throws IOException {
        this.print("\r\n");
    }

    public void println(String s) throws IOException {
        this.print(s);
        this.println();
    }

    public void println(boolean b) throws IOException {
        this.print(b);
        this.println();
    }

    public void println(char c) throws IOException {
        this.print(c);
        this.println();
    }

    public void println(int i) throws IOException {
        this.print(i);
        this.println();
    }

    public void println(long l) throws IOException {
        this.print(l);
        this.println();
    }

    public void println(float f) throws IOException {
        this.print(f);
        this.println();
    }

    public void println(double d) throws IOException {
        this.print(d);
        this.println();
    }

    /**
     * 如果往ServletOutputStream 写会成功，则该方法返回true，其他情况会返回false。
     * 如果该方法返回true，可以在ServletOutputStream 上执行写操作。如果没有后续的数据能写到
     * ServletOutputStream，那么直到底层的数据被刷出之前该方法将一直返回false。且在此时容器将调用
     * WriteListener的onWritePossible方法。随后调用该方法将返回true。
     * page-43
     *
     * @return
     */
    public abstract boolean isReady();

    /**
     * 关联WriteListener 和当且的ServletOutputStream，当
     * ServletOutputStream 可以写入数据时容器会调用WriteListener 的回调方法。注册了WriteListener 将开
     * 始非阻塞IO。此时再切换到传统的阻塞IO 是非法的。
     *
     * @param var1
     */
    public abstract void setWriteListener(WriteListener var1);
}
