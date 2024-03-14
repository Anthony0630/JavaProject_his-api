package org.example.his.api.mis.service;

import org.example.his.api.common.PageUtils;

import java.util.Map;

public interface UserService {
    public Integer login(Map param);

    public int updatePassword(Map param);

    public PageUtils searchByPage(Map param);
}


