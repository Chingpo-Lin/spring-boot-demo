package com.example.demo.mapper;

import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {

    /**
     * list all video
     */
    List<Video> listVideo();

    /**
     * 轮播图列表
     * @return
     */
    List<VideoBanner> listVideoBanner();

    /**
     * video id find detail
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("video_id") int videoId);

    /**
     * easy find
     * @param videoId
     * @return
     */
    Video findById(@Param("video_id") int videoId);
}
