package com.meteor.service;

import com.meteor.pojo.Admin;
import com.meteor.pojo.Employee;

public interface AdminService {
    /**
     * @Description: 根据用户名获取管理员
     * @Param:  * @Param: username
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Admin getAdminByName(String username);
    /**
     * @Description: 添加管理员
     * @Param:  * @Param: admin
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int addAdmin (Admin admin);
    /**
     * @Description: 删除管理员
     * @Param:  * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int deleteAdminById(Integer id);
    /**
     * @Description: 修改管理员信息
     * @Param:  * @Param: admin
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int updateAdmin(Admin admin);
    /**
            * @Description: 设置管理员信息
            * @Param:  * @Param: employee
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    Admin setAdmin(Employee employee);
}
