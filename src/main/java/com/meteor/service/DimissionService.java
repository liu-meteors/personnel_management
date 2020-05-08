package com.meteor.service;

import com.meteor.pojo.Dimission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/8 0:14
 * @description：离职登记的Service
 * @modified By：
 * @version: 0.0.1$
 */
public interface DimissionService {
    List<Dimission> getAllDimission();
    Dimission getDimissionById(Integer id);
    Integer updateDimission(Dimission dimission);
    Integer deleteDimission(Integer id);
    Integer addDimission(Dimission dimission);
}
