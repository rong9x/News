package com.codegym.news.repository;

import com.codegym.news.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findFirstByEmail(String email);

}
