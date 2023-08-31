package service;

import com.github.pagehelper.PageInfo;
import pojo.Employee;

import java.util.List;

public interface EmployeeService {
    //获取所有的员工
    public List<Employee> getAllEmp();
    //通过id得到某个员工的信息
    public List<Employee> getIdEmp(Integer id);

    //姓名模糊匹配
    public  List<Employee> getXxNameEmp(String name);
    //添加员工
    public  boolean insertEmp(Employee employee);
    // 根据id删除员工
    public  boolean  deleteEmp(Integer id);

    // 根据id修改员工信息
    public  boolean updateEmp(Integer id,Employee employee);

    //通过姓名和id共同查询
    public List<Employee> getNameId(Integer id,String name);

    //批量删除
    public boolean deleteBatchIdEmp(String ids);
    //实现分页
   public   PageInfo<Employee> getEmployeePage(Integer pageNum);

    //实现查询的分页
    public PageInfo<Employee> getEmployeeQueryPage(Integer pageNum,Integer id,String name);


}
