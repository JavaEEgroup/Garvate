package com.gc.controller;

import com.gc.ViewModel.Tag.TagsAll;
import com.gc.model.Tag;
import com.gc.repository.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/tag")
@RestController
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public TagsAll getALL() {
        try {
            List<Tag> tagList = tagRepository.findAll();
            return new TagsAll(0, tagList);
        } catch (Exception exception) {
            return new TagsAll(2);
        }
    }

}
