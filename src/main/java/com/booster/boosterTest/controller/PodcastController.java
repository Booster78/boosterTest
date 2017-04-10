package com.booster.boosterTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by sorin on 08/04/2017.
 */
@Controller
@RequestMapping(value = "/podcasts")
public class PodcastController {

    private PodcastRepository repository;

    @Autowired
    public PodcastController(PodcastRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Podcast> getPodCasts(OAuth2Authentication auth) {
        return repository.findByUserId(getClientId(auth));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Podcast getPodCasts(@PathVariable("id") String id, OAuth2Authentication auth) {
        return repository.findByUserIdAndId(getClientId(auth), Long.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody List<Podcast> postPodcast(@RequestBody Podcast podcast, OAuth2Authentication auth) {
        podcast.setUserId(getClientId(auth));
        repository.save(podcast);
        return getPodCasts(auth);
    }

    private String getClientId(OAuth2Authentication auth) {
        return ((LinkedHashMap<String, String>) auth.getUserAuthentication().getDetails()).get("sub");
    }
}
