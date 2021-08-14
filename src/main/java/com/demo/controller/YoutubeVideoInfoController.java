package com.demo.controller;

import com.demo.service.YoutubeResourceServiceImpl;
import com.demo.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/youtube")
public class YoutubeVideoInfoController implements AppConstants {

    @Autowired
    private YoutubeResourceServiceImpl youtubeResourceService;

    @GetMapping("/channel/{channelId}")
    public String getChannelVideo(@PathVariable String channelId) {
        if (StringUtils.isEmpty(channelId)) {
            channelId = DEFAULT_CHANNEL_ID;
        }
        return youtubeResourceService.getChannelVideo(channelId);
    }

    @GetMapping("/video/{videoId}")
    public String getVideoDetailByVideoId(@PathVariable String videoId) {
        if (StringUtils.isEmpty(videoId)) {
            videoId = DEFAULT_VIDEO_ID;
        }
        List<String> list = new ArrayList<>();
        list.add(videoId);
        return youtubeResourceService.getVideoDetailByVideoId(list);
    }

}
