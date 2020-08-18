package cn.zunyi001.flowable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/14 11:35 上午
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    OK(200, "success"),
    INTERNAL_ERROR(500, "服务器内部错误");;

    private Integer code;

    private String desc;
}
