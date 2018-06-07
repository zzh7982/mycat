package com.sbjava.cat.service;

import com.mongodb.client.result.DeleteResult;
import com.sbjava.cat.model.CatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * description: UserService
 *
 * @author ralf
 * @version [1.0, 2018/6/7]
 */
@Service
public class UserService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(CatUser user) {
        mongoTemplate.insert(user);
    }

    public CatUser getUserById(String id) {
        return mongoTemplate.findById(id, CatUser.class);
    }

    public void update(CatUser user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(user.getId()));
        Update update = Update.update("name", "Mr.wang");
        mongoTemplate.upsert(query, update, CatUser.class);
    }
    public DeleteResult delete(CatUser user) {
        return mongoTemplate.remove(user);
    }
}
