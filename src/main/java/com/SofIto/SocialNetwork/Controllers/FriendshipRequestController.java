package com.SofIto.SocialNetwork.Controllers;

import com.SofIto.SocialNetwork.Entities.FriendshipRequest;
import com.SofIto.SocialNetwork.Requests.FriendshipRequestCreateRequest;
import com.SofIto.SocialNetwork.Services.FriendshipRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friendship_requests")
public class FriendshipRequestController {
    FriendshipRequestService friendshipRequestService;

    public FriendshipRequestController(FriendshipRequestService friendshipRequestService) {this.friendshipRequestService = friendshipRequestService;}


    @GetMapping
    public List<FriendshipRequest> getAllRequest(@RequestParam Optional<Long> senderId,@RequestParam Optional<Long> receiveId){
        return friendshipRequestService.getAllRequests(senderId,receiveId);
    }

    @PostMapping
    public FriendshipRequest createOneRequest(@RequestBody FriendshipRequestCreateRequest request){
        return friendshipRequestService.createOneRequest(request);
    }

    @DeleteMapping
    public void deleteRequests(@RequestParam Optional<Long> requestId,@RequestParam Optional<Long> senderId,@RequestParam Optional<Long> receiveId){friendshipRequestService.deleteRequests(requestId,senderId,receiveId);
    }

}
