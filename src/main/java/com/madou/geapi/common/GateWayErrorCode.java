package com.madou.geapi.common;

/**
 * 自定义错误码
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public enum GateWayErrorCode {
    /**
     * 接口调用次数用尽网关返回状态码
     */
    FORBIDDEN("Error request, response status: 403");

    /**
     * 状态码
     */
    private final String code;

    GateWayErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
