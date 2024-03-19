package org.example.his.api.mis.service;

import org.example.his.api.common.PageUtils;
import org.example.his.api.db.pojo.DeptEntity;

import java.util.ArrayList;
import java.util.HashMap;

public interface DeptService {
    public ArrayList<HashMap> searchAllDept();

    public PageUtils searchByPage(HashMap param);

    public int insert(DeptEntity dept);

    public HashMap searchById(int id);

    public int update(DeptEntity dept);

    public int deleteByIds(Integer[] ids);
}

