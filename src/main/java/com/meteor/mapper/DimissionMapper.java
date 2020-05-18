package com.meteor.mapper;

import com.meteor.pojo.Dimission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DimissionMapper {
    List<Dimission> getAllDimission();

    Dimission getDimissionById(Integer id);

    Integer updateDimission(Dimission dimission);

    Integer deleteDimission(Integer id);

    Integer addDimission(Dimission dimission);

    /**
     * @Description: 获取部门离职信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Dimission> getAllDepDimission(Integer dep);
}
