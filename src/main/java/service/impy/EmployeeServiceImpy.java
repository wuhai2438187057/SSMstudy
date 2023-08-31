package service.impy;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Employee;
import service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpy implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmp() {
        List<Employee> allEmp = employeeMapper.getAllEmp();
        if (allEmp!=null){
            return allEmp;
        }
        return null;
    }

    @Override
    public List<Employee> getIdEmp(Integer id) {
        List<Employee> id1 = employeeMapper.getId(id);
        if (id1!=null){
            return id1;
        }
        return null;
    }

    @Override
    public List<Employee> getXxNameEmp(String name) {
        List<Employee> xxName = employeeMapper.getXxName(name);
        if (xxName!=null){
            return xxName;
        }
        return null;
    }

    @Override
    public boolean insertEmp(Employee employee) {
        int i = employeeMapper.insertEmp(employee);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmp(Integer id) {
        int i = employeeMapper.deleteIdEmp(id);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmp(Integer id, Employee employee) {
        int i = employeeMapper.updateIdEmp(id, employee);
      if (i>0){
          return true;
      }
        return false;
    }

    @Override
    public List<Employee> getNameId(Integer id, String name) {
        return  employeeMapper.getNameId(id, name);
    }

    @Override
    public boolean deleteBatchIdEmp(String ids) {
       if (employeeMapper.deleteBatchIdEmp(ids)==0){
           return false;
       }
        return true;
    }

    //实现分页
    @Override
    public PageInfo<Employee> getEmployeePage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 5);//5是每页显示的个数
        //查询所有的员工信息
        List<Employee> allEmp = employeeMapper.getAllEmp();
        //获取分页的相关数据
        PageInfo<Employee> pageInfo = new PageInfo<>(allEmp,5);//5是导航个数
        return pageInfo;
    }

    @Override
    public PageInfo<Employee> getEmployeeQueryPage(Integer pageNum,Integer id, String name) {
        //开启分页功能
        PageHelper.startPage(pageNum, 5);//5是每页显示的个数
        PageInfo<Employee> pageInfo=null;
        //开始区分是什么方式查询的
        if(id==null){
            List<Employee> xxName = employeeMapper.getXxName(name);
            pageInfo = new PageInfo<>(xxName,5);//5是导航个数
        }else if(name.equals("")){
            List<Employee> id1 = employeeMapper.getId(id);
           pageInfo = new PageInfo<>(id1,1);//5是导航个数

        }else {
            List<Employee> nameId = employeeMapper.getNameId(id, name);
            pageInfo = new PageInfo<>(nameId,1);//5是导航个数
        }
        return pageInfo;
    }



}
