package com.SofIto.SocialNetwork.Services;

import com.SofIto.SocialNetwork.Entities.Comment;
import com.SofIto.SocialNetwork.Entities.Post;
import com.SofIto.SocialNetwork.Entities.User;
import com.SofIto.SocialNetwork.Repositories.CommentRepository;
import com.SofIto.SocialNetwork.Requests.CommentCreateRequest;
import com.SofIto.SocialNetwork.Requests.CommentUpdateRequest;
import com.SofIto.SocialNetwork.Responses.CommentResponse;
import com.SofIto.SocialNetwork.Responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<CommentResponse> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> commentList;
        if(userId.isPresent()&&postId.isPresent()) {
            commentList = commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if (postId.isPresent()){
            commentList = commentRepository.findByPostId(postId.get());
        }
        else if (userId.isPresent()) {
            commentList = commentRepository.findByUserId(userId.get());
        }
        else commentList = commentRepository.findAll();

        return commentList.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }


    public Comment getOneComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUser(commentCreateRequest.getUserId());
        Post post = postService.getOnePost(commentCreateRequest.getPostId());

        if (user!=null&&post!=null){
            Comment commentToSave = new Comment();
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(commentCreateRequest.getText());
            return commentRepository.save(commentToSave);
        }
        else return null;

    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment commentToUpdate= comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return  commentRepository.save(commentToUpdate);
        }

        else return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
