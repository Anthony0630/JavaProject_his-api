package org.example.his.api.mis.service;

import org.example.his.api.common.PageUtils;
import org.example.his.api.db.pojo.RoleEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface RoleService {
    public ArrayList<HashMap> searchAllRole();
    public PageUtils searchByPage(Map param);
    public int insert(RoleEntity role);
    public HashMap searchById(int id);
    public ArrayList<Integer> searchUserIdByRoleId(int roleId);
    public int update(RoleEntity role);
    public int deleteByIds(Integer[] ids);
}

