package com.example.listam.repository;

import com.example.listam.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
//    @Query(value = "SELECT * FROM comment WHERE item_id = ?1", nativeQuery = true)
//    List<Comment> findByItemId(int item_id);
    List<Comment> findAllByItemId(int itemId);
}
