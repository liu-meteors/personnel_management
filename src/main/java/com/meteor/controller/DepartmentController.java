package com.meteor.controller;

import com.meteor.pojo.Contract;
import com.meteor.pojo.Department;
import com.meteor.service.ContractService;
import com.meteor.service.DepartmentService;
import com.meteor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ContractService contractService;
    @GetMapping("getAll/{currPage}/{pageSize}")
    public  List<Department> getAll(@PathVariable("currPage")int currPage,
                                    @PathVariable("pageSize")int pageSize){
        List<Department> departments=departmentService.getAll(currPage,pageSize);
        for (int i=0;i<departments.size();i++){
            departments.get(i).setAaa(employeeService.getCount(departments.get(i).getId()));
        }
        return departments;
    }
    @GetMapping("/getAllDepartment")
    public  List<Department> getAll(){
      return   departmentService.getAll();
    }

    @GetMapping("/getCount")
    public int getCount(){
        return departmentService.getCount();
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@RequestBody Department department){
      int isSuccess=  departmentService.addDep(department);
      if (isSuccess>0){
          List<Department> departments=departmentService.getAll();
          for (int i=1;i<4;i++){
              Contract contract=new Contract();
              contract.setPosite(i);
              contract.setDepartment(departments.get(departments.size()-1).getId());
              contractService.addContract(contract);
          }
          return "success";
      }else {
          return "error";
      }
    }
    @DeleteMapping("/deleteDepartment/{id}")
    public void deleteDepartment(@PathVariable("id")Integer id){
        int isSuccess=departmentService.deleteDepById(id);
//        if (isSuccess>0){
//            return "success";
//        }else {
//            return "error";
//        }
    }
    @PostMapping("/updateDepartment/{id}/{name}")
    public String updateDepartment(@PathVariable("id") Integer id,@PathVariable("name")String name){
        Department department=departmentService.getDepById(id);
        department.setName(name);
        int isSuccess=departmentService.updataDep(department);
        if (isSuccess>0){
            return "success";
        }else {
            return "error";
        }
    }
}
