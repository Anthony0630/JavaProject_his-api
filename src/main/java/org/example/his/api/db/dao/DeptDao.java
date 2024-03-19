package org.example.his.api.db.dao;

import org.example.his.api.db.pojo.DeptEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author Anthony
* @description 针对表【tb_dept(部门表)】的数据库操作Mapper
* @createDate 2024-03-07 18:52:17
* @Entity org.example.his.api.db.pojo.DeptEntity
*/
public interface DeptDao {
    public ArrayList<HashMap> searchAllDept();
    public ArrayList<HashMap> searchByPage(Map param);
    public long searchCount(Map param);
    public int insert(DeptEntity dept);
    public HashMap searchById(int id);
    public int update(DeptEntity dept);
    public boolean searchCanDelete(Integer[] ids);
    public int deleteByIds(Integer[] ids);
}




