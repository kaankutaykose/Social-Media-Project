package com.SofIto.SocialNetwork.Services;

import com.SofIto.SocialNetwork.Entities.Friendship;
import com.SofIto.SocialNetwork.Entities.User;
import com.SofIto.SocialNetwork.Repositories.FriendshipRepository;
import com.SofIto.SocialNetwork.Requests.FriendshipCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipService {
    FriendshipRepository repository;
    private UserService userService;

    public FriendshipService(FriendshipRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public List<Friendship> getAllFriends(Long userId) {
        User user = userService.getOneUser(userId);
        List<Friendship> list=new ArrayList<>();
        for (Friendship friendship : repository.findAll()){
            if (friendship.getSenderId()==user.getId()||friendship.getReceiveId()==user.getId()){
                list.add(friendship);
            }
        }
        return list;
    }

    public Friendship createFriendship(FriendshipCreateRequest request) {
        Friendship _friendship = new Friendship();
        _friendship.setSenderId(request.getSenderId());
        _friendship.setReceiveId(request.getReceiveId());
        repository.save(_friendship);
        return _friendship;
    }

    public void deleteFriendship(Long friendshipId) {repository.deleteById(friendshipId);}
}
