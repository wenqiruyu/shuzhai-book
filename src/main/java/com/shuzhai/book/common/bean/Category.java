package com.shuzhai.book.common.bean;

import lombok.Data;

import java.util.Date;

/**
 * 分类表
 */
@Data
public class Category {

    private Long id;
    // 分类的父类id 0表示根分类
    private Long parentId;
    private String name;
    // 分类状态 0表示废弃 1表示使用状态 默认1
    private Integer status;
    // 类别编号排序 同类展示的排序
    private Integer sortOrder;
    private Date createTime;
    private Date updateTime;

    public Category(){}

    public Category(Long parentId, String name, Integer status, Integer sortOrder, Date createTime, Date updateTime) {
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
