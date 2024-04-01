package org.example.his.api.mis.service.impl;

import cn.hutool.core.map.MapUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.db.dao.RuleDao;
import org.example.his.api.db.pojo.RuleEntity;
import org.example.his.api.mis.service.RuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class RuleServiceImpl implements RuleService {
    @Resource
    private RuleDao ruleDao;

    @Override
    public ArrayList<HashMap> searchAllRule() {
        ArrayList<HashMap> list = ruleDao.searchAllRule();
        return list;
    }

    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = new ArrayList<>();
        long count = ruleDao.searchCount(param);
        if (count > 0) {
            list = ruleDao.searchByPage(param);
        }
        int page = MapUtil.getInt(param, "page");
        int length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, page, length);
        return pageUtils;
    }

    @Override
    @Transactional
    public int insert(RuleEntity entity) {
        int rows = ruleDao.insert(entity);
        return rows;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = ruleDao.searchById(id);
        return map;
    }

    @Override
    @Transactional
    public int update(RuleEntity entity) {
        int rows = ruleDao.update(entity);
        return rows;
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        int rows = ruleDao.deleteById(id);
        return rows;
    }
}

