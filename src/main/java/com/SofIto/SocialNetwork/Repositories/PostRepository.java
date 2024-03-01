package com.SofIto.SocialNetwork.Repositories;

import com.SofIto.SocialNetwork.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
}
