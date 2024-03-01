package com.SofIto.SocialNetwork.Requests;

import lombok.Data;

@Data
public class FriendshipRequestCreateRequest {
    Long senderId;
    Long receiveId;
}
