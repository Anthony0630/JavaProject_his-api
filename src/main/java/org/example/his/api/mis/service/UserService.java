package org.example.his.api.mis.service;

import org.example.his.api.common.PageUtils;
import org.example.his.api.db.pojo.UserEntity;

import java.util.HashMap;
import java.util.Map;

public interface UserService {
    public Integer login(Map param);

    public int updatePassword(Map param);

    public PageUtils searchByPage(Map param);

    public int insert(UserEntity user);

    public HashMap searchById(int userId);

    public int update(Map param);

    public int deleteByIds(Integer[] ids);

    public int dismiss(int userId);
}


