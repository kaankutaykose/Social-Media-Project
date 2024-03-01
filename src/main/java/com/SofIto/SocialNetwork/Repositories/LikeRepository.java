package com.SofIto.SocialNetwork.Repositories;

import com.SofIto.SocialNetwork.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByPostId(Long postId);

    List<Like> findByUserId(Long userId);
}
