package com.shuzhai.book.common.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

    private Long id;
    // 商品所属分类id
    private Long categoryId;
    private String name;
    // 书作者
    private String author;
    // 副标题
    private String subtitle;
    // 出版社
    private String press;
    // 出版日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    // 商品主图
    private String mainImg;
    // 商品图片 json格式存储
    private String subImg;
    // 商品subImg相对应的缩略图
    private String thumbnail;
    // 商品详情描述
    private String detail;
    // 商品定价
    private BigDecimal pricing;
    private BigDecimal price;
    // 商品数量
    private Long stock;
    // 商品评分 默认为0
    private Long grade;
    // 商品用户评论数 默认为0
    private Long commentNum;
    // 商品销量
    private Long sale;
    // 商品状态 1上架  2下架  3删除  4热卖
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Product() { }

    public Product(Long categoryId, String name, String author, String subtitle, String press, Date publishDate, String mainImg, String subImg, String thumbnail, String detail, BigDecimal pricing, BigDecimal price, Long stock, Long grade, Long commentNum, Long sale, Integer status, Date createTime, Date updateTime) {
        this.categoryId = categoryId;
        this.name = name;
        this.author = author;
        this.subtitle = subtitle;
        this.press = press;
        this.publishDate = publishDate;
        this.mainImg = mainImg;
        this.subImg = subImg;
        this.thumbnail = thumbnail;
        this.detail = detail;
        this.pricing = pricing;
        this.price = price;
        this.stock = stock;
        this.grade = grade;
        this.commentNum = commentNum;
        this.sale = sale;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Product(Long id,String name,String subtitle,BigDecimal price) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.price = price;
    }
}
