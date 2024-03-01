package com.SofIto.SocialNetwork.Services;

import com.SofIto.SocialNetwork.Entities.Comment;
import com.SofIto.SocialNetwork.Entities.Like;
import com.SofIto.SocialNetwork.Entities.Post;
import com.SofIto.SocialNetwork.Entities.User;
import com.SofIto.SocialNetwork.Repositories.LikeRepository;
import com.SofIto.SocialNetwork.Requests.LikeCreateRequest;
import com.SofIto.SocialNetwork.Responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> likeList;
        if(userId.isPresent()&&postId.isPresent()) {
            likeList = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if (postId.isPresent()){
            likeList = likeRepository.findByPostId(postId.get());
        }
        else if (userId.isPresent()) {
            likeList = likeRepository.findByUserId(userId.get());
        }
        else likeList = likeRepository.findAll();

        return likeList.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }
    public Like getOneLike(Long likeId) {
      return  likeRepository.findById(likeId).orElse(null);
    }


    public Like crateOneLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getOneUser(likeCreateRequest.getUserId());
        Post post = postService.getOnePost(likeCreateRequest.getPostId());

        if (user!=null&&post!=null){
            Like likeToSave = new Like();
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }
        else return null;
    }

    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
