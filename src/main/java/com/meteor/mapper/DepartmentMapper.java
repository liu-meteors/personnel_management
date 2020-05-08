package com.meteor.mapper;

import com.meteor.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    List<Department> getAll();
    Department getDepById(Integer id);
    int addDep(Department department);
    int updataDep(Department department);
    int deleteDepById(Integer id);
}
