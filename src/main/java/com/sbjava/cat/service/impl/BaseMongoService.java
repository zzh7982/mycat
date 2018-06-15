package com.sbjava.cat.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sbjava.cat.service.IMongoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * description: mongo查询抽象类
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
@Repository
public abstract class BaseMongoService<T, PK> implements IMongoService<T, PK> {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insert(T t) {
        mongoTemplate.insert(t);
    }

    @Override
    public T findOne(Class<T> c, PK pk) {
        return mongoTemplate.findById(pk, getEntityClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public UpdateResult update(T t, PK pk, Class<T> c) {
        Class clazz = t.getClass();
        // 获取实体类的所有属性信息，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(pk));
        Update update = new Update();
        Arrays.stream(fields).filter(x -> !"id".equals(x.getName())).forEach(g -> {
            String name = g.getName().substring(0, 1).toUpperCase() + g.getName().substring(1);
            try {
                Method m = clazz.getMethod("get" + name);
                Object value = m.invoke(t);
                if (value != null) {
                    update.set(g.getName(), value);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return mongoTemplate.upsert(query, update, c);
    }

    @Override
    public DeleteResult delete(T t) {
        return mongoTemplate.remove(t);
    }

    @Override
    public List<T> findAll(Class<T> c) {
        return mongoTemplate.find(new Query(), c);
    }

    @Override
    public List<T> findByPro(Class<T> c, Map<String, Object> map) {
        Query query = new Query();
        map.forEach((k, v) -> query.addCriteria(Criteria.where(k).is(v)));
        return mongoTemplate.find(query, c);
    }

    // 获取需要操作的实体类class
    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
