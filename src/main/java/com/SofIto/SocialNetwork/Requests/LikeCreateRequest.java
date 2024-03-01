package com.SofIto.SocialNetwork.Requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
    Long userId;
    Long postId;
}
