package org.example.his.api.front.service;

import java.util.HashMap;

public interface CustomerService {
    public boolean sendSmsCode(String tel);
    public HashMap login(String tel, String code);
    public HashMap searchSummary(int id);
}

