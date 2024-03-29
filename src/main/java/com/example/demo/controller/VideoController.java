package com.example.demo.controller;


import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoBanner;
import com.example.demo.service.VideoService;
import com.example.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// localhost:8080/video
@RestController
@RequestMapping("/api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("list_banner")
    public JsonData IndexBanner() {
        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }

    /**
     * 视频列表
     * @return
     */
    @RequestMapping("list")
    public JsonData listVideo() {
        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }

    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value="video_id", required = true) int videoId) {
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }
}
