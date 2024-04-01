package org.example.his.api.db.dao;

import org.example.his.api.db.pojo.GoodsSnapshotEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class GoodsSnapshotDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public String hasGoodsSnapshot(String md5) {
        Criteria criteria = Criteria.where("md5").is(md5);
        Query query = new Query(criteria);
        query.skip(0);
        query.limit(1);
        GoodsSnapshotEntity entity = mongoTemplate.findOne(query, GoodsSnapshotEntity.class);
        return entity != null ? entity.get_id() : null;
    }

    public String insert(GoodsSnapshotEntity entity) {
        String _id = mongoTemplate.save(entity).get_id();
        return _id;
    }

    public GoodsSnapshotEntity searchById(String id) {
        GoodsSnapshotEntity entity = mongoTemplate.findById(id, GoodsSnapshotEntity.class);
        return entity;
    }
}

