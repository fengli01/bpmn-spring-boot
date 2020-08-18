package cn.zunyi001.flowable.base.service.impl;

import cn.zunyi001.flowable.base.mapper.BaseMapper;
import cn.zunyi001.flowable.base.service.BaseService;
import cn.zunyi001.flowable.vo.QueryResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/14 3:43 下午
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T, ID> implements BaseService<T, ID> {

    @Autowired
    protected M baseMapper;

    @Override
    @Transactional
    public int save(T entity) {
        return baseMapper.insertSelective(entity);
    }

    @Override
    public int count(T entity) {
        return baseMapper.selectCount(entity);
    }

    @Override
    public int count(Example example) {
        return baseMapper.selectCountByExample(example);
    }

    @Override
    @Transactional
    public int batchSave(List<T> datalist) {
        return baseMapper.insertList(datalist);
    }

    @Override
    @Transactional
    public int remove(ID id) {
        T t = newInstance();
        Class<?> clazz = t.getClass();
        try {
            Field delFlag = clazz.getDeclaredField("delFlag");
            Field idFlag = clazz.getDeclaredField("id");
            if (delFlag != null) {
                delFlag.setAccessible(true);
                delFlag.set(t, true);
            }
            if (idFlag != null) {
                idFlag.setAccessible(true);
                idFlag.set(t, id);
            }
        } catch (Exception e) {
        }
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    @Transactional
    public int modify(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public T findById(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public T selectOne(T entity) {
        return baseMapper.selectOne(entity);
    }

    @Override
    public List<T> selectList(Example example) {
        return baseMapper.selectByExample(example);
    }

    @Override
    public List<T> select(T t) {
        return baseMapper.select(t);
    }

    @Override
    public QueryResult<T> pageQuery(Integer page, Integer pageSize) {
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(page, pageSize);
        T t = newInstance();
        Example example = new Example(t.getClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        example.orderBy("id").desc();
        List<T> datalist = baseMapper.selectByExample(example);
        PageInfo<T> pageInfo = new PageInfo<>(datalist);
        QueryResult<T> queryResult = new QueryResult(pageInfo.getList(), pageInfo.getTotal());
        return queryResult;
    }

    private T newInstance() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class<T>) parameterizedType.getActualTypeArguments()[1];
        T t = null;
        try {
            t = (T) clazz.newInstance();
        } catch (Exception e) {
        }
        return t;
    }

    @Override
    public QueryResult<T> pageQuery(Integer page, Integer pageSize, T entity) {
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(page, pageSize);
        List<T> datalist = baseMapper.select(entity);
        PageInfo<T> pageInfo = new PageInfo<>(datalist);
        QueryResult<T> queryResult = new QueryResult(pageInfo.getList(), pageInfo.getTotal());
        return queryResult;
    }

    @Override
    public QueryResult<T> pageQuery(Integer page, Integer pageSize, Example example) {
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(page, pageSize);
        List<T> datalist = baseMapper.selectByExample(example);
        PageInfo<T> pageInfo = new PageInfo<>(datalist);
        QueryResult<T> queryResult = new QueryResult(pageInfo.getList(), pageInfo.getTotal());
        return queryResult;
    }
}
