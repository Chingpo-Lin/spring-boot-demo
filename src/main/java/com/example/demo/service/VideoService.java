package com.example.demo.service;

import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
