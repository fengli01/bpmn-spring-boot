package cn.zunyi001.flowable.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/6/7 2:56 下午
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class QueryResult<T> implements Serializable {
    private List<T> list;
    private long total;
}
