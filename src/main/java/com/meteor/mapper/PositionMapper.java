package com.meteor.mapper;

import com.meteor.pojo.Position;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/2 13:34
 * @description：职位的mapper接口
 * @modified By：
 * @version: 0.0.1$
 */
@Mapper
@Repository
public interface PositionMapper {
    List<Position> getAllPosition();
    Position getPositionById(Integer id);
}
