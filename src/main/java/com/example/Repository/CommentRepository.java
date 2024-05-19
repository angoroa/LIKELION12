package com.example.Repository;

import com.example.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
// Comment 클래스의 기본 키는 id 필드이며, 이 필드의 타입은 Long


}
