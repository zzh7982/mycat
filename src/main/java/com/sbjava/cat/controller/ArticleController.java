package com.sbjava.cat.controller;

import com.sbjava.cat.model.Article;
import com.sbjava.cat.service.impl.ArticleService;
import com.sbjava.cat.utils.RepObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * description: 时间轴
 *
 * @author ralf
 * @version [1.0, 2018/6/12]
 */
@Api(value = "/article", tags = "时间轴接口模块")
@RestController
@RequestMapping("/articles")
public class ArticleController extends AbstractController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "插入时间轴", notes = "发帖")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户id,不需要传", dataType = "String"),
//            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "nickname", value = "用户名昵称", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "birthday", value = "生日yyyy-mm-dd", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "score", value = "积分", dataType = "Integer"),
//            @ApiImplicitParam(name = "msgFlag", value = "设置开关 0关闭 1开启", dataType = "Integer")
    })
    @PostMapping
    public RepObj article(@Valid Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        articleService.insert(article);
        return success(article);
    }

    @ApiOperation(value = "根据id查询时间轴", notes = "查询时间轴")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    @GetMapping("/{id}")
    public RepObj getArticle(@PathVariable("id") String id) {
        return success(articleService.findOne(id));
    }

    @ApiOperation(value = "查询时间轴", notes = "查询时间轴")
    @GetMapping
    public RepObj getArticles(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return success(articleService.findAll());
        }
        return success(articleService.getArticleByUser(userId));
    }

    @ApiOperation(value = "更新时间轴", notes = "更新时间轴")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String"),
//            @ApiImplicitParam(name = "nickname", value = "用户名昵称", dataType = "String"),
//            @ApiImplicitParam(name = "birthday", value = "生日yyyy-mm-dd", dataType = "String"),
//            @ApiImplicitParam(name = "score", value = "积分", dataType = "Integer"),
//            @ApiImplicitParam(name = "msgFlag", value = "设置开关 0关闭 1开启", dataType = "Integer")
    })
    @PatchMapping
    public RepObj updateArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        return success(articleService.update(article, article.getId()));
    }

    @ApiOperation(value = "删除时间轴", notes = "删除时间轴")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    @DeleteMapping("/{id}")
    public RepObj delete(@PathVariable("id") String id) {
        Article article = new Article();
        article.setId(id);
        return success(articleService.delete(article));
    }

    @ApiOperation(value = "根据用户查询时间轴", notes = "根据用户查询时间轴")
    @GetMapping("/user/{userId}")
    public RepObj getArticleByUserId(@PathVariable("userId") String userId) {
        return success(articleService.getArticleByUser(userId));
    }

    @ApiOperation(value = "查询我的时间轴", notes = "查询我的时间轴")
    @GetMapping("/mine")
    public RepObj getArticleMine() {
        return success(articleService.getArticleByUser(getUserId()));
    }
}
