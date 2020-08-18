package cn.zunyi001.flowable.vo;

import cn.zunyi001.flowable.enums.ResultEnum;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/14 11:38 上午
 */
public class Resp {

    public static <T> JsonResult<T> ok() {
        return ok(ResultEnum.OK.getCode(), "", null);
    }

    public static <T> JsonResult<T> ok(T data) {
        return ok(ResultEnum.OK.getCode(), "", data);
    }

    public static <T> JsonResult<T> ok(Integer code, T data) {
        return ok(code, "", data);
    }

    public static <T> JsonResult<T> ok(Integer code, String msg, T data) {
        return new JsonResult(code, msg, data);
    }
}
