package com.codegym.news.repository;

import com.codegym.news.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Iterable<Post> findTop3ByCategoryIdOrderByPostDateDesc(Long id);

    Post findFirstByOrderByPostDateDesc();

    Page<Post> findAllByCategoryIdOrderByPostDateDesc(Long id, Pageable pageable);

    Page<Post> findAllByTittleContaining(String title, Pageable pageable);
}
