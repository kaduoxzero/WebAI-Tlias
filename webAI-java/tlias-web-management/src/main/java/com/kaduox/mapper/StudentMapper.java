package com.kaduox.mapper;

import com.kaduox.pojo.Student;
import com.kaduox.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    // 这里的参数 studentQueryParam 会被 XML 内部自动解析其属性（如 name, no）
    List<Student> list(StudentQueryParam studentQueryParam);

    void insert(Student student);

    void update(Student student);

    void deleteByIds(@Param("ids") List<Integer> ids);

    Student getById(Integer id);

    void handleViolation(Student student);

    @MapKey("pos")
    List<Map> countStudentCountData();

    @MapKey("pos")
    List<Map> countStudentDegreeData();
}