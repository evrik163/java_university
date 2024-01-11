package com.example.unik;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
  @Query("SELECT p FROM Posts p WHERE p.topic LIKE %?1%") List<Posts> search_by_name(String keyword);

}