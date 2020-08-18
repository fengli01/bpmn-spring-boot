package cn.zunyi001.flowable.vo;

import cn.zunyi001.flowable.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/14 11:32 上午
 */
@Getter
@Setter
@ToString
public class JsonResult<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public JsonResult() {
    }

    public JsonResult(T data) {
        this.code = ResultEnum.OK.getCode();
        this.msg = "success";
        this.data = data;
    }

    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
