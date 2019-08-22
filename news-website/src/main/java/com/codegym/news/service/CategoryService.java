package com.codegym.news.service;

import com.codegym.news.model.Category;

public interface CategoryService {

    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);

}
