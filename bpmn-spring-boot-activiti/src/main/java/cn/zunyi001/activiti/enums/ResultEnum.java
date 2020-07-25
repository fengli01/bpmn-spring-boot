package cn.zunyi001.activiti.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/7/24 11:42 下午
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    OK(200, "成功"),
    INTERNAL_ERROR(500, "服务器内部错误");

    private Integer code;
    private String desc;
}
