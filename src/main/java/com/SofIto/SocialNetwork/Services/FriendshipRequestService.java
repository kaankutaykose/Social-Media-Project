package com.SofIto.SocialNetwork.Services;

import com.SofIto.SocialNetwork.Entities.FriendshipRequest;
import com.SofIto.SocialNetwork.Entities.User;
import com.SofIto.SocialNetwork.Repositories.FriendshipRequestRepository;
import com.SofIto.SocialNetwork.Requests.FriendshipRequestCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class FriendshipRequestService {

    private FriendshipRequestRepository friendshipRequestRepository;
    private UserService userService;

    @Autowired
    public FriendshipRequestService(FriendshipRequestRepository friendshipRequestRepository, UserService userService) {
        this.friendshipRequestRepository = friendshipRequestRepository;
        this.userService = userService;
    }


    public List<FriendshipRequest> getAllRequests(Optional<Long> senderId, Optional<Long> receiveId) {
        if(senderId.isPresent()&&receiveId.isPresent()){
            return friendshipRequestRepository.findBySenderIdAndReceiveId(senderId,receiveId);
        }
        else if (senderId.isPresent())
            return friendshipRequestRepository.findBySenderId(senderId.get());
        else if (receiveId.isPresent())
            return friendshipRequestRepository.findByReceiveId(receiveId.get());
        else return friendshipRequestRepository.findAll();}

    public FriendshipRequest getOneRequest(Long friendshipRequestId) {return friendshipRequestRepository.findById(friendshipRequestId).orElse(null);}
    public FriendshipRequest createOneRequest(FriendshipRequestCreateRequest request) {

        User sender_user = userService.getOneUser(request.getSenderId());
        User receive_user = userService.getOneUser(request.getReceiveId());
        if (sender_user!=null&&receive_user!=null){
            FriendshipRequest requestToSave = new FriendshipRequest();
            requestToSave.setSenderId(request.getSenderId());
            requestToSave.setReceiveId(request.getReceiveId());
            return friendshipRequestRepository.save(requestToSave);
        }
        else return null;
    }


    public void deleteRequests(Optional <Long> requestId, Optional<Long> senderId, Optional<Long> receiveId) {

        if (requestId.isPresent()&&senderId.isPresent()&&receiveId.isPresent()){
            friendshipRequestRepository.deleteById(requestId.get());

        }else if (receiveId.isPresent()&&senderId.isPresent()){
            friendshipRequestRepository.deleteBySenderIdAndReceiveId(senderId,receiveId);

        } else if (receiveId.isPresent()) {
            friendshipRequestRepository.deleteByReceiveId(receiveId);

        } else if (senderId.isPresent()) {
            friendshipRequestRepository.deleteBySenderId(senderId);

        } else if (requestId.isPresent())friendshipRequestRepository.deleteById(requestId.get());}
}
