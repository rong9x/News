package com.codegym.news.controller;

import com.codegym.news.model.Category;
import com.codegym.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("admin/category")
    public ModelAndView showCategoriesList() {
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("admin/create-category")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("admin/create-category")
    public ModelAndView createCategory(@Validated @ModelAttribute("category") Category category,
                                       BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/admin/category/create");
            modelAndView.addObject("message", "Invalid input");
            return modelAndView;
        }
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/admin/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "Create new category successfully!");
        return modelAndView;
    }

    @GetMapping("admin/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Category category = categoryService.findById(id);

        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/category/edit");
            modelAndView.addObject("category", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/assets/error404");
            return modelAndView;
        }
    }

    @PostMapping("admin/edit-category")
    public ModelAndView editCategory(@Validated @ModelAttribute("category") Category category,
                                     BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/admin/category/edit");
            modelAndView.addObject("message", "Invalid input");
            return modelAndView;
        }
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/admin/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Update category successfully!");
        return modelAndView;
    }

    @GetMapping("admin/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Category category = categoryService.findById(id);

        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/assets/error404");
            return modelAndView;
        }
    }

    @PostMapping("admin/delete-category")
    public String deleteOrRestoreCategory(@ModelAttribute("category") Category category) {
        categoryService.remove(category.getId());
        return "redirect:/admin/category";
    }
}
