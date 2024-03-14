package org.example.his.api.common;

import lombok.Data;

import java.util.List;

@Data
public class PageUtils {
    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 每页显示几条记录
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页数
     */
    private int pageIndex;

    /**
     * 分页数据
     */
    private List list;

    public PageUtils(List list, long totalCount, int pageIndex, int pageSize) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

}

