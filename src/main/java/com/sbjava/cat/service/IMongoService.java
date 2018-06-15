package com.sbjava.cat.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.List;
import java.util.Map;

/**
 * description: mongo接口
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
public interface IMongoService<T, PK> {
    /**
     * 新增
     *
     * @param t obj
     */
    void insert(T t);

    /**
     * 查询
     *
     * @param e  class
     * @param pk pk
     * @return obj
     */
    T findOne(Class<T> e, PK pk);

    /**
     * 查询
     *
     * @param e
     * @param map
     * @return
     */
    List<T> findByPro(Class<T> e, Map<String, Object> map);

    /**
     * 查询所有
     *
     * @param c class
     * @return list</       obj>
     */
    List<T> findAll(Class<T> c);

    /**
     * 更新
     *
     * @param t  obj
     * @param pk pk
     * @param c  class
     * @return UpdateResult
     */
    UpdateResult update(T t, PK pk, Class<T> c);

    /**
     * 删除
     *
     * @param t obj
     * @return DeleteResult
     */
    DeleteResult delete(T t);
}
