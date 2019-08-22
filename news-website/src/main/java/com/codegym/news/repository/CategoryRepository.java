package com.codegym.news.repository;

import com.codegym.news.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {



    /*@Transactional
    @Modifying
    @Query(value = "update Category c set c.categoryName_EN = ?1 where c.id = ?2")
    void updateCategory(String name, Long catId);*/
}
