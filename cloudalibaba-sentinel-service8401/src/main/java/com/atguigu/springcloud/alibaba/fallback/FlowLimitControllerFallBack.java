package com.atguigu.springcloud.alibaba.fallback;

/**
 * @author wangyaochong
 * @date 2020/3/18 22:52
 */
public class FlowLimitControllerFallBack {
    public static String fallback_testHotKey(String p1,
                                             String p2) {
        return "---testHotKey---FlowLimitControllerFallBack";
    }
}
