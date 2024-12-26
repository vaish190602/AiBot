package com.example.demo.controller;

import com.example.demo.model.Tweet;
import com.example.demo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tweets") // Base path for all Tweet-related APIs
public class TweetController {

    @Autowired
    private TweetService tweetService;

    // Get all tweets
    @GetMapping
    public ResponseEntity<List<Tweet>> getAllTweets() {
        List<Tweet> tweets = tweetService.getAllTweets();
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    // Get a tweet by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable String id) {
        Optional<Tweet> tweet = tweetService.getTweetById(id);
        return tweet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new tweet
    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestBody Tweet tweet) {
        Tweet savedTweet = tweetService.saveTweet(tweet);
        return new ResponseEntity<>(savedTweet, HttpStatus.CREATED);
    }

    // Update a tweet
    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable String id, @RequestBody Tweet tweet) {
        Optional<Tweet> existingTweet = tweetService.getTweetById(id);
        if (existingTweet.isPresent()) {
            tweet.setId(id); // Ensure the ID remains the same
            Tweet updatedTweet = tweetService.saveTweet(tweet);
            return new ResponseEntity<>(updatedTweet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a tweet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable String id) {
        tweetService.deleteTweet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

