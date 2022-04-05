package com.example.test.controller;

import com.example.test.bean.News;
import com.example.test.mapper.NewsMapper;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    private List<News> cacheNews = new ArrayList<>();
    private final NewsMapper newsMapper;

    public NewsController(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @RequestMapping("/news")
    public String loadNewsPage(HttpSession session, HttpServletRequest request) {
        if (cacheNews.isEmpty()) {
            cacheNews = newsMapper.findAll();
        }
        request.setAttribute("newList", cacheNews);
        session.setAttribute("context", "");
        return "news";
    }

    @RequestMapping("/news/id={id}")
    public String viewNews(HttpSession session, HttpServletRequest request, @PathVariable("id") int id) {
        if (cacheNews.isEmpty()) {
            cacheNews = newsMapper.findAll();
        }
        request.setAttribute("newList", cacheNews);
        News news = newsMapper.selectById(id);
        if (news == null) {
            session.setAttribute("context", "找不到該新聞");
            return "news";
        }
        session.setAttribute("context", news.getContext());
        return "news";
    }
}
