package com.SofIto.SocialNetwork.Repositories;

import com.SofIto.SocialNetwork.Entities.Comment;
import com.SofIto.SocialNetwork.Entities.FriendshipRequest;
import com.SofIto.SocialNetwork.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {
    List<FriendshipRequest> findBySenderId(Long senderId);

    List<FriendshipRequest> findBySenderIdAndReceiveId(Optional<Long> senderId, Optional<Long> receiveId);

    List<FriendshipRequest> findByReceiveId(Long receiveId);

    void deleteBySenderIdAndReceiveId(Optional<Long> senderId, Optional<Long> receiveId);

    void deleteByReceiveId(Optional<Long> receiveId);

    void deleteBySenderId(Optional<Long> senderId);
}
