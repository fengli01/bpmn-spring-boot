package cn.zunyi001.flowable.vo.result;

import cn.zunyi001.flowable.enums.ResultEnum;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/7/24 11:34 下午
 */
public class Resp {
    public static JsonResult ok() {
        return ok(ResultEnum.OK.getDesc());
    }

    public static JsonResult ok(String msg) {
        return ok(null, msg);
    }

    public static <T> JsonResult ok(T t) {
        return ok(t, ResultEnum.OK.getDesc());
    }

    public static <T> JsonResult ok(T data, String msg) {
        return makeModelMap(ResultEnum.OK.getCode(), msg, data);
    }

    public static JsonResult error() {
        return error(ResultEnum.INTERNAL_ERROR.getDesc());
    }

    public static JsonResult error(String msg) {
        return error(null, msg);
    }

    public static <T> JsonResult error(T data, String msg) {
        return makeModelMap(ResultEnum.INTERNAL_ERROR.getCode(), msg, data);
    }

    public static <T> JsonResult error(Integer code, String msg, T data) {
        return makeModelMap(code, msg, data);
    }

    private static JsonResult makeModelMap(Integer code, String msg, Object data) {
        return JsonResult.builder().code(code).msg(msg).data(data).build();
    }
}
