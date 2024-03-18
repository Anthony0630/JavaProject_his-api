package org.example.his.api.db.dao;

import org.example.his.api.db.pojo.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* @author Anthony
* @description 针对表【tb_user(用户表)】的数据库操作Mapper
* @createDate 2024-03-07 18:52:17
* @Entity org.example.his.api.db.pojo.UserEntity
*/
public interface UserDao {
    public Set<String> searchUserPermissions(int userId);

    public Integer login(Map param);

    public String searchUsernameById(int userId);

    public int updatePassword(Map param);

    public ArrayList<HashMap> searchByPage(Map param);

    public long searchCount(Map param);

    public int insert(UserEntity user);

    public HashMap searchById(int userId);

    public int update(Map param);

    public int deleteByIds(Integer[] ids);

    public int dismiss(int userId);
}



