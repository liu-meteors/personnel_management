package com.meteor.service.impl;

import com.meteor.mapper.AdminMapper;
import com.meteor.pojo.Admin;
import com.meteor.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/1 15:18
 * @description：AdminService接口实现类
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    /**
     * @param username
     * @Description: 根据用户名获取管理员
     * @Param: * @Param: username
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public Admin getAdminByName(String username) {

        return adminMapper.getAdminByName(username);
    }

    /**
     * @param admin
     * @Description: 添加管理员
     * @Param: * @Param: admin
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    /**
     * @param id
     * @Description: 删除管理员
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int deleteAdminById(Integer id) {
        return adminMapper.deleteAdminById(id);
    }

    /**
     * @param admin
     * @Description: 修改管理员信息
     * @Param: * @Param: admin
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }
}
