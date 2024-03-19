package org.example.his.api.mis.service.impl;

import cn.hutool.core.map.MapUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.db.dao.RoleDao;
import org.example.his.api.db.pojo.RoleEntity;
import org.example.his.api.exception.HisException;
import org.example.his.api.mis.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public ArrayList<HashMap> searchAllRole() {
        ArrayList<HashMap> list = roleDao.searchAllRole();
        return list;
    }

    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = new ArrayList<>();
        long count = roleDao.searchCount(param);
        if (count > 0) {
            list = roleDao.searchByPage(param);
        }
        int page = MapUtil.getInt(param, "page");
        int length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, page, length);
        return pageUtils;
    }

    @Override
    @Transactional
    public int insert(RoleEntity role) {
        int rows = roleDao.insert(role);
        return rows;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = roleDao.searchById(id);
        return map;
    }

    @Override
    public ArrayList<Integer> searchUserIdByRoleId(int roleId) {
        ArrayList<Integer> list = roleDao.searchUserIdByRoleId(roleId);
        return list;
    }

    @Override
    @Transactional
    public int update(RoleEntity role) {
        int rows = roleDao.update(role);
        return rows;
    }

    @Override
    @Transactional
    public int deleteByIds(Integer[] ids) {
        if (!roleDao.searchCanDelete(ids)) {
            throw new HisException("无法删除关联用户的角色");
        }
        int rows = roleDao.deleteByIds(ids);
        return rows;
    }
}