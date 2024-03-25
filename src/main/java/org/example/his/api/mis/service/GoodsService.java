package org.example.his.api.mis.service;

import org.example.his.api.common.PageUtils;
import org.example.his.api.db.pojo.GoodsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

public interface GoodsService {
    public PageUtils searchByPage(Map param);
    public String uploadImage(MultipartFile file);
    public int insert(GoodsEntity entity);
    public HashMap searchById(int id);
    public int update(GoodsEntity entity);
    public void updateCheckup(int id, MultipartFile file);
    public boolean updateStatus(Map param);
    public int deleteByIds(Integer[] ids);
}

