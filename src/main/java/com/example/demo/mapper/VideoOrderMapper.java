package com.example.demo.mapper;

import com.example.demo.model.entity.Video;
import com.example.demo.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderMapper {

    /**
     * see if user have bought it
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndState(@Param("user_id") int userId, @Param("video_id") int videoId, @Param("state") int state);

    /**
     * order
     * @param videoOrder
     * @return
     */
    int saveOrder(VideoOrder videoOrder);

    /**
     * video list
     * @param userId
     * @return
     */
    List<VideoOrder> listOrderByUserId(Integer userId);
}
