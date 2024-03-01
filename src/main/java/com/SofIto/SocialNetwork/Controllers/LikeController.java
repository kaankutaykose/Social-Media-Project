package com.SofIto.SocialNetwork.Controllers;

import com.SofIto.SocialNetwork.Entities.Comment;
import com.SofIto.SocialNetwork.Entities.Like;
import com.SofIto.SocialNetwork.Requests.CommentCreateRequest;
import com.SofIto.SocialNetwork.Requests.CommentUpdateRequest;
import com.SofIto.SocialNetwork.Requests.LikeCreateRequest;
import com.SofIto.SocialNetwork.Responses.LikeResponse;
import com.SofIto.SocialNetwork.Services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikes(userId,postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLike(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.crateOneLike(likeCreateRequest);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLike(likeId);
    }
}
