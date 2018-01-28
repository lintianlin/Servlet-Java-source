package javax.servlet.http;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;

import java.io.IOException;


public interface WebConnection extends AutoCloseable {
    ServletInputStream getInputStream() throws IOException;

    ServletOutputStream getOutputStream() throws IOException;
}
