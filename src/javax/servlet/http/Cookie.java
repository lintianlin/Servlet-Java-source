package javax.servlet.http;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 通过HTTP cookie的会话跟踪是最常用的会话跟踪机制，且所有servlet容器都应该支持。
 * 容器向客户端发送一个cookie，客户端后续到服务器的请求都将返回该cookie，明确地将请求与会话关联。
 * 会话跟踪cookie 的标准名字必须是JSESSIONID，所有3.0 兼容的容器必须支持。容器也允许通过容器指
 * 定的配置自定义会话跟踪cookie的名字。
 * 所有servlet容器必须提供能够配置容器是否标记会话跟踪cookie 为HttpOnly。已建立的配置必须应用到所
 * 有上下文中还没有建立特定的配置(见SessionCookieConfig javadoc获取更多细节)。
 * 如果web应用为其会话跟踪cookie 配置了一个自定义的名字，则如果会话id编码到URL中那么相同的自
 * 定义名字也将用于URI参数的名字（假如URL重写已开启）。
 * page-51
 */
public class Cookie implements Cloneable, Serializable {
    private static final long serialVersionUID = -6454587001725327448L;
    private static final String TSPECIALS;
    private static final String LSTRING_FILE = "javax.com.sinfeeloo.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle("javax.com.sinfeeloo.servlet.http.LocalStrings");
    private String name;
    private String value;
    private String comment;
    private String domain;
    private int maxAge = -1;
    private String path;
    private boolean secure;
    private int version = 0;
    private boolean isHttpOnly = false;

    public Cookie(String name, String value) {
        if (name != null && name.length() != 0) {
            if (this.isToken(name) && !name.equalsIgnoreCase("Comment") && !name.equalsIgnoreCase("Discard") && !name.equalsIgnoreCase("Domain") && !name.equalsIgnoreCase("Expires") && !name.equalsIgnoreCase("Max-Age") && !name.equalsIgnoreCase("Path") && !name.equalsIgnoreCase("Secure") && !name.equalsIgnoreCase("Version") && !name.startsWith("$")) {
                this.name = name;
                this.value = value;
            } else {
                String errMsg = lStrings.getString("err.cookie_name_is_token");
                Object[] errArgs = new Object[]{name};
                errMsg = MessageFormat.format(errMsg, errArgs);
                throw new IllegalArgumentException(errMsg);
            }
        } else {
            throw new IllegalArgumentException(lStrings.getString("err.cookie_name_blank"));
        }
    }

    public void setComment(String purpose) {
        this.comment = purpose;
    }

    public String getComment() {
        return this.comment;
    }

    public void setDomain(String domain) {
        this.domain = domain.toLowerCase(Locale.ENGLISH);
    }

    public String getDomain() {
        return this.domain;
    }

    public void setMaxAge(int expiry) {
        this.maxAge = expiry;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public void setPath(String uri) {
        this.path = uri;
    }

    public String getPath() {
        return this.path;
    }

    public void setSecure(boolean flag) {
        this.secure = flag;
    }

    public boolean getSecure() {
        return this.secure;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

    public String getValue() {
        return this.value;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int v) {
        this.version = v;
    }

    private boolean isToken(String value) {
        int len = value.length();

        for (int i = 0; i < len; ++i) {
            char c = value.charAt(i);
            if (c < ' ' || c >= 127 || TSPECIALS.indexOf(c) != -1) {
                return false;
            }
        }

        return true;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException var2) {
            throw new RuntimeException(var2.getMessage());
        }
    }

    public void setHttpOnly(boolean isHttpOnly) {
        this.isHttpOnly = isHttpOnly;
    }

    public boolean isHttpOnly() {
        return this.isHttpOnly;
    }

    static {
        if (Boolean.valueOf(System.getProperty("org.glassfish.web.rfc2109_cookie_names_enforced", "true")).booleanValue()) {
            TSPECIALS = "/()<>@,;:\\\"[]?={} \t";
        } else {
            TSPECIALS = ",; ";
        }

    }
}
