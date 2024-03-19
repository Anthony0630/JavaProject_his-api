package org.example.his.api.mis.service.impl;

import cn.hutool.core.map.MapUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.db.dao.DeptDao;
import org.example.his.api.db.pojo.DeptEntity;
import org.example.his.api.exception.HisException;
import org.example.his.api.mis.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    @Override
    public ArrayList<HashMap> searchAllDept() {
        ArrayList<HashMap> list = deptDao.searchAllDept();
        return list;
    }

    @Override
    public PageUtils searchByPage(HashMap param) {
        ArrayList<HashMap> list = new ArrayList<>();
        long count = deptDao.searchCount(param);
        if (count > 0) {
            list = deptDao.searchByPage(param);
        }
        int page = MapUtil.getInt(param, "page");
        int length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, page, length);

        return pageUtils;
    }

    @Override
    @Transactional
    public int insert(DeptEntity dept) {
        int rows = deptDao.insert(dept);
        return rows;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = deptDao.searchById(id);
        return map;
    }

    @Override
    @Transactional
    public int update(DeptEntity dept) {
        int rows = deptDao.update(dept);
        return rows;
    }

    @Override
    @Transactional
    public int deleteByIds(Integer[] ids) {
        if (!deptDao.searchCanDelete(ids)) {
            throw new HisException("无法删除关联用户的部门");
        }
        int rows = deptDao.deleteByIds(ids);
        return rows;
    }
}

