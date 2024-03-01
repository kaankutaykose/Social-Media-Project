package com.SofIto.SocialNetwork.Controllers;

import com.SofIto.SocialNetwork.Entities.Friendship;
import com.SofIto.SocialNetwork.Requests.FriendshipCreateRequest;
import com.SofIto.SocialNetwork.Services.FriendshipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    FriendshipService service;

    public FriendshipController(FriendshipService service) {this.service = service;}

    @GetMapping("{userId}")
    public List<Friendship> getAllFriendships(@PathVariable Long userId ){
        return service.getAllFriends(userId);
    }

    @PostMapping
    public Friendship createFriendship(@RequestBody FriendshipCreateRequest request){
        return service.createFriendship(request);
    }

    @DeleteMapping
    public void deleteFriendship(@PathVariable Long friendshipId){service.deleteFriendship(friendshipId);}
}
