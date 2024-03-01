package com.SofIto.SocialNetwork.Responses;

import com.SofIto.SocialNetwork.Entities.Comment;
import com.SofIto.SocialNetwork.Entities.Like;
import com.SofIto.SocialNetwork.Entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;

    String image_url;

    String video_url;
    List<LikeResponse> likeList;

    List<CommentResponse> commentList;


    public PostResponse(Post entity, List<LikeResponse> likeList,List<CommentResponse> commentList) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.image_url = entity.getImage_url();
        this.video_url = entity.getVideo_url();
        this.likeList = likeList;
        this.commentList = commentList;
    }
}
