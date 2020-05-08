package com.meteor.service.impl;

import com.meteor.mapper.DepartmentMapper;
import com.meteor.pojo.Department;
import com.meteor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        List<Department> departments=departmentMapper.getAll();
        departments.remove(0);
        for (int i=0;i<departments.size();i++){
            departments.get(i).setText(departments.get(i).getName());
            departments.get(i).setValue(departments.get(i).getName());
        }
        Pattern pattern=Pattern.compile("");
        return departments;
    }

    @Override
    public List<Department> getAll(int currPage, int page) {
        List<Department> departments=departmentMapper.getAll();
        departments.remove(0);
        if (departments.size()/currPage<1){
            return departments;
        }else if (departments.size()<currPage*page){
            return departments.subList(currPage*(page-1),departments.size());
        }else {
            return departments.subList(currPage*(page-1),currPage*page);
        }

    }

    @Override
    public int getCount() {
        return departmentMapper.getAll().size();
    }

    @Override
    public int addDep(Department department) {
        return departmentMapper.addDep(department);
    }

    @Override
    public int updataDep(Department department) {

        return departmentMapper.updataDep(department);
    }

    @Override
    public int deleteDepById(Integer id) {
        return departmentMapper.deleteDepById(id);
    }

    @Override
    public Department getDepById(Integer id) {
        return departmentMapper.getDepById(id);
    }
}
