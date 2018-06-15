package com.sbjava.cat.service.impl;

import com.sbjava.cat.model.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.by;

/**
 * description: 时间轴service
 *
 * @author ralf
 * @version [1.0, 2018/6/12]
 */
@Service
public class ArticleService extends BaseMongoService<Article, String> {

    public List<Article> getArticleByUser(String userId) {
        return mongoTemplate.find(new Query(Criteria.where("userId").is(userId)), Article.class);
    }

    @Override
    public List<Article> findAll() {
        return mongoTemplate.find(new Query().with(by(Sort.Direction.DESC, "createTime")), getEntityClass());
    }
}
