package org.example.his.api.mis.service;

import org.example.his.api.common.PageUtils;
import org.example.his.api.db.pojo.RuleEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface RuleService {
    public ArrayList<HashMap> searchAllRule();
    public PageUtils searchByPage(Map param);
    public int insert(RuleEntity entity);
    public HashMap searchById(int id);
    public int update(RuleEntity entity);
    public int deleteById(int id);
}

