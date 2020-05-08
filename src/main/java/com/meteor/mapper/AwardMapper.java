package com.meteor.mapper;

import com.meteor.pojo.Award;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AwardMapper {
    /**
            * @Description: 获取所有奖惩信息
            * @Param:  * @Param:
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    List<Award> getAllAward();
    /**
            * @Description: 根据id查询奖惩信息
            * @Param:  * @Param: id
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    Award getAwardById(Integer id);
    /**
            * @Description: 根据员工查询奖惩信息
            * @Param:  * @Param:
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    List<Award> getAwardByEmpId(Integer empId);
    /**
            * @Description: 根据类型查询奖惩信息
            * @Param:  * @Param:
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    List<Award> getAwardByType(Integer type);
    /**
            * @Description: 修改
            * @Param:  * @Param: award
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int updateAward(Award award);
    /**
            * @Description: 根据id删除信息
            * @Param:  * @Param: id
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int deleteAwardById(Integer id);
    /**
            * @Description: 根据员工id删除信息
            * @Param:  * @Param: id
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int deleteAwardByEmpId(Integer id);
    /**
            * @Description: 添加信息
            * @Param:  * @Param: award
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int addAward(Award award);
    List<Award> getAllAwardByEmpNow(Integer empId);
}
