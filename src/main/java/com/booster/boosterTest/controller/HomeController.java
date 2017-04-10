package com.booster.boosterTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sorin on 08/04/2017.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private RecordRepository repository;

    @Autowired
    public HomeController(RecordRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody List<Podcast> getPodCasts(OAuth2Authentication auth) {
        String clientId = auth.getName();
        List<Podcast> podcasts = new ArrayList<Podcast>();
        //TODO créer requête JPA
        for (Podcast p : repository.findAll()) {
            if(clientId.equals(p.getUserId())) {
                podcasts.add(p);
            }
        }
        return podcasts;
    }

    @RequestMapping(method = RequestMethod.POST,  produces="application/json",consumes="application/json")
    public @ResponseBody
    List<Podcast> postPodcast(
                             @RequestBody Podcast podcast,
                              OAuth2Authentication auth) {
        String clientId = auth.getName();
        podcast.setUserId(clientId);
        System.out.print("clientId : " + clientId);

            repository.save(podcast);

        return getPodCasts(auth);
    }
}
