package com.SofIto.SocialNetwork.Requests;

import lombok.Data;

@Data
public class FriendshipCreateRequest {

    Long senderId;
    Long receiveId;
}
