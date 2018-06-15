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
     * @param pk pk
     * @return obj
     */
    T findOne(PK pk);

    /**
     * 查询
     *
     * @param map
     * @return
     */
    List<T> findByPro(Map<String, Object> map);

    /**
     * 查询所有
     *
     * @return list<obj>
     */
    List<T> findAll();

    /**
     * 更新
     *
     * @param t  obj
     * @param pk pk
     * @return UpdateResult
     */
    UpdateResult update(T t, PK pk);

    /**
     * 删除
     *
     * @param t obj
     * @return DeleteResult
     */
    DeleteResult delete(T t);
}
