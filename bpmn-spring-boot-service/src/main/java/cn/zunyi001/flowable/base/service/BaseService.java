package cn.zunyi001.flowable.base.service;

import cn.zunyi001.flowable.vo.QueryResult;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/6/5 8:31 下午
 */
public interface BaseService<T, ID> {

    int save(T entity);

    int count(T entity);

    int count(Example example);

    int batchSave(List<T> datalist);

    int remove(ID id);

    int modify(T entity);

    T findById(ID id);

    T selectOne(T entity);

    List<T> selectList(Example example);

    List<T> select(T t);

    QueryResult<T> pageQuery(Integer page, Integer pageSize);

    QueryResult<T> pageQuery(Integer page, Integer pageSize, T entity);

    QueryResult<T> pageQuery(Integer page, Integer pageSize, Example example);
}
