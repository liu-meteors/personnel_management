package com.meteor.mapper;

import com.meteor.pojo.YearBenefit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface YearBenefitMapper {
    /**
     * @Description: 添加年效益
     * @Param: * @Param: yearBenefit
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int addYearBenefit(YearBenefit yearBenefit);

    /**
     * @Description: 查询所有年效益
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<YearBenefit> getAllYearBenefit();

    /**
     * @Description: 查询部门年效益
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<YearBenefit> getDepYearBenefit(Integer dep);

    /**
     * @Description: 修改年效益
     * @Param: * @Param: yearBenefit
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int updateYearBenefit(YearBenefit yearBenefit);

    /**
     * @Description: 删除年效益
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int deleteYearBenefitById(Integer id);
}
