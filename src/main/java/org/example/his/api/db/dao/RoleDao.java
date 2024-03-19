package org.example.his.api.db.dao;

import org.example.his.api.db.pojo.RoleEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author Anthony
* @description 针对表【tb_role(角色表)】的数据库操作Mapper
* @createDate 2024-03-07 18:52:17
* @Entity org.example.his.api.db.pojo.RoleEntity
*/
public interface RoleDao {
    public ArrayList<HashMap> searchAllRole();
    public ArrayList<HashMap> searchByPage(Map param);
    public long searchCount(Map param);
    public int insert(RoleEntity role);
    public HashMap searchById(int id);
    public ArrayList<Integer> searchUserIdByRoleId(int roleId);
    public int update(RoleEntity role);
    public boolean searchCanDelete(Integer[] ids);
    public int deleteByIds(Integer[] ids);
}




