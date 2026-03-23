package com.kaduox.service.impl;

import com.kaduox.mapper.EmpMapper;
import com.kaduox.mapper.StudentMapper;
import com.kaduox.pojo.Emp;
import com.kaduox.pojo.JobOption;
import com.kaduox.pojo.StudentOption;
import com.kaduox.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public StudentOption getStudentCountData() {
        List<Map> list = studentMapper.countStudentCountData();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("value")).toList();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("name")).toList();
        return new StudentOption(clazzList, dataList);
    }

}
