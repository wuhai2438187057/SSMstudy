package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class DayAfter {

    @RequestMapping("/dayafter")
    public String dayAfter(Model model){
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 创建固定日期
        LocalDate fixedDate = LocalDate.of(2023, 8, 30);
        // 计算日期差
        Period period = Period.between(fixedDate, currentDate);
        // 获取天数差
        int days = period.getDays();
        model.addAttribute("days",days) ;
        return "dayAfter";
    }

}
