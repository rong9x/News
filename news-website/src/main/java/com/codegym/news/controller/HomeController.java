package com.codegym.news.controller;

import com.codegym.news.model.Category;
import com.codegym.news.model.Post;
import com.codegym.news.service.CategoryService;
import com.codegym.news.service.PostService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class HomeController {
    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }
    

    @GetMapping("/home")
    public ModelAndView home() {
        Post newestPost = postService.findFirstByOrderByPostDateDesc();
        Iterable<Category> categoryIterable = categoryService.findAll();
        List<Post> rtList = new ArrayList<>();
        for (Category c : categoryIterable) {
            rtList.addAll(ImmutableList.copyOf(postService.findTop3ByCategoryIdOrderByPostDateDesc(c.getId())));
        }
        if (rtList != null) {
            ModelAndView modelAndView = new ModelAndView("/assets/index");
            modelAndView.addObject("posts", rtList);
            modelAndView.addObject("newestPost", newestPost);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/assets/error404");
            return modelAndView;
        }
    }

    @GetMapping("/{category_id}/ByCategory")
    public ModelAndView technology(@RequestParam("s") Optional<String> s,
                                   @PathVariable Long category_id,
                                   Pageable pageable) {
        Page<Post> posts;
        if (s.isPresent()) {
            pageable = new PageRequest(pageable.getPageNumber(), 20);
            posts = postService.findAllByTittleContaining(s.get(), pageable);
        } else {
            pageable = new PageRequest(pageable.getPageNumber(), 3);
            posts = postService.findAllByCategoryIdOrderByPostDateDesc(category_id, pageable);
        }

        if (posts != null) {
            ModelAndView modelAndView = new ModelAndView("assets/postByCategory");
            modelAndView.addObject("posts", posts);
            modelAndView.addObject("category_id", category_id);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/assets/error404");
            return modelAndView;
        }
    }

    @GetMapping("/view-post/{id}")
    public ModelAndView postDetail(@RequestParam("s") Optional<String> s,
                                   @PathVariable Long id,
                                   @PageableDefault(size = 20) Pageable pageable) {
        if (s.isPresent()) {
            Page<Post> resultList = postService.findAllByTittleContaining(s.get(), pageable);
            ModelAndView modelAndView = new ModelAndView("assets/search");
            modelAndView.addObject("posts", resultList);
            return modelAndView;
        } else {
            Post post = postService.findById(id);
            if (post == null) {
                ModelAndView modelAndView = new ModelAndView("/assets/error404");
                return modelAndView;
            }
            ModelAndView modelAndView = new ModelAndView("/assets/post");
            modelAndView.addObject("post", post);
            return modelAndView;
        }
    }
}
