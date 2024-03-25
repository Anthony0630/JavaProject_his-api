package org.example.his.api.front.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.his.api.db.dao.GoodsDao;
import org.example.his.api.front.service.GoodsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("FrontGoodsServiceImpl")
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Override
    @Cacheable(cacheNames = "goods", key = "#id")
    public HashMap searchById(int id) {
        Map param = new HashMap() {{
            put("id", id);
            put("status", true);
        }};
        HashMap map = goodsDao.searchById(param);
        if (map != null) {
            for (String key : new String[]{"tag", "checkup_1", "checkup_2", "checkup_3", "checkup_4"}) {
                String temp = MapUtil.getStr(map, key);
                JSONArray array = JSONUtil.parseArray(temp);
                map.replace(key, array);
            }
            return map;
        }
        return null;
    }
}

