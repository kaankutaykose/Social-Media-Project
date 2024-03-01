package com.SofIto.SocialNetwork.Services;

import com.SofIto.SocialNetwork.Entities.Comment;
import com.SofIto.SocialNetwork.Entities.Like;
import com.SofIto.SocialNetwork.Entities.Post;
import com.SofIto.SocialNetwork.Entities.User;
import com.SofIto.SocialNetwork.Repositories.PostRepository;
import com.SofIto.SocialNetwork.Requests.PostCreateRequest;
import com.SofIto.SocialNetwork.Requests.PostUpdateRequest;
import com.SofIto.SocialNetwork.Responses.CommentResponse;
import com.SofIto.SocialNetwork.Responses.LikeResponse;
import com.SofIto.SocialNetwork.Responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    private LikeService likeService;
    private CommentService commentService;

    //@Autowired
    //Burada like service kendi içerisinde post servisi çağırıyor bu yüzden döngü oluşuyor.
    // spring ilk kimi yaratacağına karar veremiyor o yüzden @Lazy kullandım
    public PostService(PostRepository postRepository, UserService userService,@Lazy LikeService likeService,@Lazy CommentService commentService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeService = likeService;
        this.commentService = commentService;
    }

    /*
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

     */

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> postList;
        if (userId.isPresent()){
            postList = postRepository.findByUserId(userId.get());
        }
        postList = postRepository.findAll();

        return postList.stream().map(p -> {
            List<LikeResponse> likeList = likeService.getAllLikes(Optional.ofNullable(null),Optional.ofNullable(p.getId()));
            List<CommentResponse> commentList = commentService.getAllComments(Optional.ofNullable(null),Optional.ofNullable(p.getId()));
            return new PostResponse(p,likeList,commentList);
        }).collect(Collectors.toList());
    }

    public Post saveOnePost(PostCreateRequest postCreateRequest) {
        User user = userService.getOneUser(postCreateRequest.getUserId());
        if (user==null)return null;
        Post toSave = new Post();
        toSave.setText(postCreateRequest.getText());
        toSave.setTitle(postCreateRequest.getTitle());
        toSave.setImage_url(postCreateRequest.getImage_url());
        toSave.setVideo_url(postCreateRequest.getVideo_url());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post uptadeOnePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post foundPost = post.get();
            foundPost.setTitle(postUpdateRequest.getTitle());
            foundPost.setText(postUpdateRequest.getText());
            postRepository.save(foundPost);
            return foundPost;
        }else
            return null;
    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
