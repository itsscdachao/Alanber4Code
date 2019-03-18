package com.itssc.tool_network.config;

/**
 * 网络请求错误码
 */
public class NetWorkErrorCode {
    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 1000;
    /**
     * 解析数据失败
     */
    public static final int PARSE_DATA_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int BAD_NETWORK_ERROR = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_FAILED_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT_ERROR = 1004;
}
