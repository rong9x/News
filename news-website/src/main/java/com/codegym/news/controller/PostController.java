package com.codegym.news.controller;

import com.codegym.news.model.Category;
import com.codegym.news.model.Post;
import com.codegym.news.service.CategoryService;
import com.codegym.news.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("admin/post")
    public ModelAndView showAllPosts(@RequestParam("s") Optional<String> s, Pageable pageable) {
            Page<Post> posts;
            if (s.isPresent()) {
                posts = postService.findAllByTittleContaining(s.get(), pageable);
            } else {
                posts = postService.findAll(pageable);
            }
            ModelAndView modelAndView = new ModelAndView("/admin/post/list");
            modelAndView.addObject("posts", posts);
            return modelAndView;
    }

    @GetMapping("admin/create-post")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/post/create");
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }

    @PostMapping("admin/create-post")
    public ModelAndView createPost(@Validated @ModelAttribute("post") Post post, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/admin/post/create");
            modelAndView.addObject("message", "Invalid input");
            return modelAndView;
        }
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("/admin/post/create");
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("message", "Create new post successfully!");
        return modelAndView;
    }

    @GetMapping("admin/edit-post/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        Post post = postService.findById(id);

        if (post != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/post/edit");
            modelAndView.addObject("post", post);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("/assets/error404");
        return modelAndView;
    }

    @PostMapping("admin/edit-post")
    public ModelAndView updatePost(@Validated @ModelAttribute("post") Post post, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/admin/post/edit");
            modelAndView.addObject("message", "Invalid input");
            return modelAndView;
        }
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("/admin/post/edit");
        modelAndView.addObject("post", post);
        modelAndView.addObject("message", "Update post successfully!");
        return modelAndView;
    }

    @GetMapping("admin/delete-post/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Post post = postService.findById(id);

        if (post != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/post/delete");
            modelAndView.addObject("post", post);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("/assets/error404");
        return modelAndView;
    }

    @PostMapping("admin/delete-post")
    public String deletePost(@ModelAttribute("post") Post post) {
        postService.remove(post.getId());
        return "redirect:/admin/post";
    }

}
