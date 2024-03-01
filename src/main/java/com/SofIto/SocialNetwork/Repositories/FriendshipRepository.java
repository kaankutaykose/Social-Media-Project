package com.SofIto.SocialNetwork.Repositories;

import com.SofIto.SocialNetwork.Entities.Comment;
import com.SofIto.SocialNetwork.Entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository  extends JpaRepository<Friendship,Long> {
}
