package org.example.his.api.db.dao;

import org.example.his.api.db.pojo.RuleEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author Anthony
* @description 针对表【tb_rule(规则表)】的数据库操作Mapper
* @createDate 2024-03-07 18:52:17
* @Entity org.example.his.api.db.pojo.RuleEntity
*/
public interface RuleDao {
    public ArrayList<HashMap> searchAllRule();
    public ArrayList<HashMap> searchByPage(Map param);
    public long searchCount(Map param);
    public int insert(RuleEntity entity);
    public HashMap searchById(int id);
    public int update(RuleEntity entity);
    public int deleteById(int id);
}





