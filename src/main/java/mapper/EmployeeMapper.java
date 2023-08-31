package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Employee;

import java.util.List;

public interface EmployeeMapper {
        //得到所有的员工信息
      public   List<Employee>  getAllEmp();

        //通过id得到某个员工的信息
    public   List<Employee> getId(@Param("id") Integer id);


    //姓名模糊匹配
      public  List<Employee> getXxName(@Param("name") String name);

      //通过id和姓名同时查询
       public  List<Employee> getNameId(@Param("id") Integer id,@Param("name") String name);

      //添加员工
    public  int insertEmp(Employee employee);

    //根据id删除员工
    public int deleteIdEmp(@Param("id") Integer id);

    //批量删除员工
    public  int deleteBatchIdEmp(@Param("ids") String ids);

    //根据id修改员工信息
      public  int  updateIdEmp(@Param("id") Integer id, @Param("employee")Employee employee);






}
