package com.SofIto.SocialNetwork.Requests;

import lombok.Data;

@Data
public class PostCreateRequest {
    String text;
    String title;
    Long userId;
    String image_url;
    String video_url;
}
