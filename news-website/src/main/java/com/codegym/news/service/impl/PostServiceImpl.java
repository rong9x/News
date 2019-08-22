package com.codegym.news.service.impl;

import com.codegym.news.model.Post;
import com.codegym.news.repository.PostRepository;
import com.codegym.news.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findOne(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Iterable<Post> findTop3ByCategoryIdOrderByPostDateDesc(Long id) {
        return postRepository.findTop3ByCategoryIdOrderByPostDateDesc(id);
    }

    @Override
    public Page<Post> findAllByCategoryIdOrderByPostDateDesc(Long id, Pageable pageable) {
        return postRepository.findAllByCategoryIdOrderByPostDateDesc(id, pageable);
    }

    @Override
    public Post findFirstByOrderByPostDateDesc() {
        return postRepository.findFirstByOrderByPostDateDesc();
    }

    @Override
    public void remove(Long id) {
        postRepository.delete(id);
    }

    @Override
    public Page<Post> findAllByTittleContaining(String title, Pageable pageable) {
        return postRepository.findAllByTittleContaining(title, pageable);
    }
}
