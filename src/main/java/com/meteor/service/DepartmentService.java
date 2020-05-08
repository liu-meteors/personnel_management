package com.meteor.service;

import com.meteor.pojo.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    List<Department> getAll(int currPage, int page);
    int getCount();
    int addDep(Department department);
    int updataDep(Department department);
    int deleteDepById(Integer id);
    Department getDepById(Integer id);
}
