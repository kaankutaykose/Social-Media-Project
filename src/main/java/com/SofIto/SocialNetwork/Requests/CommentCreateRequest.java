package com.SofIto.SocialNetwork.Requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long userId;
    Long postId;
    String text;


}
