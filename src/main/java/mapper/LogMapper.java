package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Log;

public interface LogMapper {
    //获取log表的用户和密码
    public Log getInfo(@Param("name") String name);

    //用户注册
    public  Integer  insertUser(Log log);

}
