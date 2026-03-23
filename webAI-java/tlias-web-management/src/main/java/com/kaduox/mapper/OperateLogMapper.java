package com.kaduox.mapper;

import com.kaduox.pojo.OperateLog;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface OperateLogMapper {

    @Insert("insert into operate_log(operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values(#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insert(OperateLog log);

    /**
     * 查询操作日志列表
     * 手动指定 Results 映射，确保 operateEmpName 和其他字段都能正确解析
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "operate_emp_id", property = "operateEmpId"),
            @Result(column = "operate_emp_name", property = "operateEmpName"),
            @Result(column = "operate_time", property = "operateTime"),
            @Result(column = "class_name", property = "className"),
            @Result(column = "method_name", property = "methodName"),
            @Result(column = "method_params", property = "methodParams"),
            @Result(column = "return_value", property = "returnValue"),
            @Result(column = "cost_time", property = "costTime")
    })
    @Select("select l.*, e.name as operate_emp_name " +
            "from operate_log l " +
            "left join emp e on l.operate_emp_id = e.id " +
            "order by l.operate_time desc")
    List<OperateLog> list();
}