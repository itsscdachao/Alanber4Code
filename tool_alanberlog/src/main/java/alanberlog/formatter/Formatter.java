package alanberlog.formatter;

public interface Formatter {
    String format(int logLevel, String tag, String msg);
}
