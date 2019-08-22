package com.codegym.news.service;

import com.codegym.news.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<Post> findAll(Pageable pageable);

    Post findById(Long id);

    void save(Post post);

    Iterable<Post> findTop3ByCategoryIdOrderByPostDateDesc(Long id);

    Page<Post> findAllByCategoryIdOrderByPostDateDesc(Long id, Pageable pageable);

    Post findFirstByOrderByPostDateDesc();

    void remove(Long id);

    Page<Post> findAllByTittleContaining(String title, Pageable pageable);
}
