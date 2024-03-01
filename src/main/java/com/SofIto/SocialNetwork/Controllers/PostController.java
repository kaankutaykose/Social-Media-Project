package com.SofIto.SocialNetwork.Controllers;

import com.SofIto.SocialNetwork.Responses.PostResponse;
import com.SofIto.SocialNetwork.Services.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.SofIto.SocialNetwork.Entities.Post;
import com.SofIto.SocialNetwork.Entities.User;
import com.SofIto.SocialNetwork.Requests.PostCreateRequest;
import com.SofIto.SocialNetwork.Requests.PostUpdateRequest;
import com.SofIto.SocialNetwork.Services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/posts")
public class PostController {
    PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

   // @GetMapping
   // public List<Post> getAllPosts(@RequestParam Optional<Long> userId){return postService.getAllPosts(userId);}

    /*
    @GetMapping()
    public String allPosts(Model model) {
        var result = (List<Post>) postService.getAllPosts(); // Enjekte edilen servis üstünden tüm kategori listesi
        // çekildi
        model.addAttribute("postList", result); // İlişkili model nesnesine attibute olarak ilgili liste eklendi
        return "posts";
    }

     */

    /*
    @GetMapping()
    public String allPosts(Model model) {
        var result = (List<Post>) postService.getAllPosts(); // Enjekte edilen servis üstünden tüm kategori listesi
        // çekildi
        //model.addAttribute("postList", result); // İlişkili model nesnesine attibute olarak ilgili liste eklendi
        return "/posts";
    }

     */

    @GetMapping
    public List<PostResponse> getAllPost(Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @PostMapping()
    public Post createPost(@RequestBody PostCreateRequest postCreateRequest){
        return postService.saveOnePost(postCreateRequest);
    }

    @GetMapping("/{postId}")
    public Post getOneUser(@PathVariable Long postId){
        return postService.getOnePost(postId);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.uptadeOnePost(postId,postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePost(postId);
    }
}
