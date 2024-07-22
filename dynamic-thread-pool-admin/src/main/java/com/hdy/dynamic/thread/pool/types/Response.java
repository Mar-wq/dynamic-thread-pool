package com.hdy.dynamic.thread.pool.types;

import lombok.*;

import java.io.Serializable;

/**
 * @author Hdy
 * @description
 * @date 2024/7/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -2474596551402989285L;

    private String code;
    private String info;
    private T data;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum Code {
        SUCCESS("0000", "调用成功"),
        UN_ERROR("0001", "调用成功"),
        ILLEGAL_PARAMETER("0002","非法参数"),
        ;


        private String code;
        private String info;
    }
}
