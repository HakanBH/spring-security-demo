package com.epam.security.securitydemo.controller;

import com.epam.security.securitydemo.model.Tweet;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/tweets")
@CrossOrigin(origins = "*")
public class TweetController {

    private Map<String, Tweet> tweets;

    @PostConstruct
    public void init() {
        tweets = new HashMap<>();
        this.create(new Tweet(null, "Text, mext", "Hakan", new Date()));
        this.create(new Tweet(null, "Text, mext", "Hakan", new Date()));
        this.create(new Tweet(null, "Text, mext", "Hakan", new Date()));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Tweet> getAll() {
        return List.copyOf(tweets.values());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Tweet getById(@PathVariable String id) {
        return tweets.get(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Tweet delete(@PathVariable String id) {
        return tweets.remove(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Tweet create(@RequestBody Tweet tweet) {
        String id = UUID.randomUUID().toString();
        tweet.setId(id);
        return tweets.put(id, tweet);
    }
}
