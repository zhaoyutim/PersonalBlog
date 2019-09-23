package com.timcorp.personalblog.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.timcorp.personalblog.entities.Post;
import com.timcorp.personalblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private PostService postService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
    @GetMapping(value = "/posts")
    public List<Post> posts(){
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public void publishPost(@RequestBody Post post){
        if (post.getDateCreated()==null) {
            post.setDateCreated(new Date());
        }
        postService.insert(post);
    }
}
