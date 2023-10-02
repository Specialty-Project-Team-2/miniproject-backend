package com.sparta.miniproject.repository;

import com.sparta.miniproject.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByCompany_Id(Long companyId, Pageable pageable);
}
