package com.kaduox.mapper;

import com.kaduox.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> empExprList);

    void deleteByEmpIds(List<Integer> empIds);
}
