package com.example.demo.service;

import com.example.demo.repository.TweetRepository;
import com.example.demo.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    // Get all tweets
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    // Get a tweet by ID
    public Optional<Tweet> getTweetById(String id) {
        return tweetRepository.findById(id);
    }

    // Save a new tweet
    public Tweet saveTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    // Delete a tweet by ID
    public void deleteTweet(String id) {
        tweetRepository.deleteById(id);
    }
}
