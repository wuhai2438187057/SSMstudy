package controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.Employee;
import service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/employeeManger/{pageNum}")
    public  String  employeeManger(Model model , @PathVariable("pageNum") Integer pageNum){
       //获取员工的分页信息
        PageInfo<Employee> pageInfo =employeeService.getEmployeePage(pageNum);
        //将分页数据共享到请求域中
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageName","员工管理");
        return "index";
    }

    @RequestMapping( value = "/employeeMangerOperate",method = {RequestMethod.GET})
    public  String employeeMangerOperateQuery(Integer employeeId,String employeeName,Model model){
        model.addAttribute("pageName","员工管理");
            if(employeeName==null){
                model.addAttribute("formInfo","(╯▔皿▔)╯");
                return "index";
            }
              if(employeeId==null&&employeeName.equals("")){
                  model.addAttribute("formInfo","无效查询");
                 return  "index";
              }

          if(employeeId==null){
              PageInfo<Employee> xxNameEmp = employeeService.getEmployeeQueryPage(1, employeeId, employeeName);
              model.addAttribute("pageInfo",xxNameEmp);
              model.addAttribute("formInfo","查询成功");
              return "index";
          } else if (employeeId<=0) {
              model.addAttribute("formInfo","(╯▔皿▔)╯");
              return "index";
          } else if (employeeName==null) {
              model.addAttribute("formInfo","(╯▔皿▔)╯");
              return "index";
          } else if(employeeName.equals("")) {
              PageInfo<Employee> idEmp = employeeService.getEmployeeQueryPage(1, employeeId, employeeName);
              model.addAttribute("pageInfo",idEmp);
              model.addAttribute("formInfo","查询成功");
              return "index";
          }else {
              PageInfo<Employee> nameId = employeeService.getEmployeeQueryPage(1, employeeId, employeeName);
              model.addAttribute("pageInfo",nameId);
              model.addAttribute("formInfo","查询成功");
              return "index";
          }
    }

  @RequestMapping(value = {"/employeeMangerOperate"},method = {RequestMethod.POST})
    public String  employeeMangerOperateInsert(Employee employee,Model model){
       if (employee==null||employee.getEmpName().equals("")||employee.getAge()==null||employee.getSex()==null||employee.getEmail().equals("")){
           model.addAttribute("formInfo","添加失败");
           return "forward:/employeeManger/1";
       }
       if(employeeService.insertEmp(employee)) {
           model.addAttribute("formInfo", "添加成功");
           return "forward:/employeeManger/1";
       }else {
           model.addAttribute("formInfo","服务器原因导致添加失败");
           return "forward:/employeeManger/1";
       }
  }

    @RequestMapping(value = {"/employeeMangerOPeratedelBatch/{ids}"})
    public  String employeeMangerOPerateBatch(@PathVariable("ids") String  ids, Model model){
        //这里之后会中再次涉及，目前没完全解决
        if(employeeService.deleteBatchIdEmp(ids)){
              return "forward:/employeeManger/1";//ajax请求这个return说不会跳转的
          }else {//之所以重定向也不知道为为啥，但是运行了，以后了解
            return "forward:/employeeManger/1";
          }
    }

    @RequestMapping(value = {"/employeeMangerOperate/{empId}"},method = {RequestMethod.DELETE})
    public  String  employeeMangerOperateDelete(@PathVariable("empId") Integer id,Model model){
        if(employeeService.deleteEmp(id)){
            model.addAttribute("formInfo","删除成功");
            return "forward:/employeeManger/1";
        }else {
            model.addAttribute("formInfo","删除失败");
            return "forward:/employeeManger/1";
        }
    }

    @RequestMapping("/employeeMangerOperateUpdatePage/{id}")
    public  String employeeMangerOperateUpdatePage(@PathVariable("id") Integer id,Model model){
        List<Employee> idEmp2 = employeeService.getIdEmp(id);
        Employee idEmp=idEmp2.get(0);
        model.addAttribute("emp",idEmp);
      return "employeeUpdate";
    }


    @RequestMapping(value = {"/employeeMangerOperate/{id}"},method = {RequestMethod.PUT})
    public  String employeeMangerOperateUpdate(Employee employee,Model model,@PathVariable("id") Integer id){
        if (employee==null||employee.getEmpName().equals("")||employee.getAge()==null||employee.getSex()==null||employee.getEmail().equals("")){
            return "forward:/employeeMangerOperateUpdatePage/{id}";
        }
        if(employeeService.updateEmp(id,employee)){
            model.addAttribute("formInfo","修改成功");
            return "forward:/employeeManger/1";
        }else {
            model.addAttribute("formInfo","服务器原因导致修改失败");
            return "forward:/employeeManger/1";
        }
        }



}
