package com.meteor.controller;

import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/2 14:37
 * @description：职位的Controller层
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class PositionController {
    @Autowired
    PositionMapper positionMapper;

    @GetMapping("/getAllPosition")
    public List<Position> getAllPosition(){
        return positionMapper.getAllPosition();
    }
}
