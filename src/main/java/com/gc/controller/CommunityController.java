package com.gc.controller;

import com.gc.ViewModel.CommunityAll;
import com.gc.ViewModel.CommunityDetails;
import com.gc.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/community")
public class CommunityController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(value = "/all")
    private CommunityAll all() {

        CommunityAll communityAll = new CommunityAll(0);
        communityAll.add2Results(articleRepository.findAll());

        return communityAll;
    }

    @RequestMapping(value = "/details")
    private CommunityDetails details(@RequestParam(value = "id")Integer id) {

        CommunityDetails communityDetails = new CommunityDetails(0);


        return communityDetails;
    }

}
