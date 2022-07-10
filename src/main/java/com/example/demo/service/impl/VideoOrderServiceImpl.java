package com.example.demo.service.impl;


import com.example.demo.exception.XDException;
import com.example.demo.mapper.*;
import com.example.demo.model.entity.Episode;
import com.example.demo.model.entity.PlayRecord;
import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoOrder;
import com.example.demo.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    /**
     * order
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int userId, int videoId) {
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndState(userId, videoId, 1);
        if (videoOrder != null) {
            return 0;
        }

        Video video = videoMapper.findById(videoId);
        System.out.println(video);
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setUserId(userId);
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFree(video.getPrice());

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        if (rows == 1) {
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if (episode == null) {
                throw new XDException(-1, "no video episode, please check");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }
}
