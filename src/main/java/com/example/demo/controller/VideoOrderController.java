package com.example.demo.controller;

import com.example.demo.model.entity.VideoOrder;
import com.example.demo.model.request.VideoOrderRequest;
import com.example.demo.service.VideoOrderService;
import com.example.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * order
     * @return
     */
    @RequestMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute("user_id");
        System.out.println(userId);
        if (userId == null) {
            return JsonData.buildError("id not found");
        }
        int rows = videoOrderService.save(userId, videoOrderRequest.getVideoId());
        return rows == 0 ? JsonData.buildError("error") : JsonData.buildSuccess();
    }

    /**
     * list order
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");

        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);

        return JsonData.buildSuccess(videoOrderList);
    }
}
