package com.shuzhai.book.dao;

import com.shuzhai.book.common.bean.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductMapper {

    @Insert("insert into book_product(category_id,name,author,subtitle,press,publish_date,main_img,sub_img,detail,pricing,price,stock,grade,comment_num,sale,status,create_time,update_time) " +
            "values(#{categoryId},#{name},#{author},#{subtitle},#{press},#{publishDate},#{mainImg},#{subImg},#{detail},#{pricing},#{price},#{stock},#{grade},#{commentNum},#{sale},#{status},#{createTime},#{updateTime})")
    int insertProduct(Product product);

    @Select("select * from book_product where author = #{name}")
    Product selectProductByName(String name);

    @Select("select * from book_product where id = #{id}")
    Product selectProductById(Long id);

    @Select("select * from book_product where author = #{author}")
    List<Product> selectProductByAuthor(String author);

    @Select("select * from book_product")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "author", column = "author"),
            @Result(property = "subtitle", column = "subtitle"),
            @Result(property = "press", column = "press"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "mainImg", column = "main_img"),
            @Result(property = "subImg", column = "sub_img"),
            @Result(property = "detail", column = "detail"),
            @Result(property = "pricing", column = "pricing"),
            @Result(property = "price", column = "price"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "grade", column = "grade"),
            @Result(property = "commentNum", column = "comment_num"),
            @Result(property = "sale", column = "sale"),
            @Result(property = "status", column = "status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    List<Product> selectAllProduct();

    @Select("select * from book_product where status = #{status}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "author", column = "author"),
            @Result(property = "subtitle", column = "subtitle"),
            @Result(property = "press", column = "press"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "mainImg", column = "main_img"),
            @Result(property = "subImg", column = "sub_img"),
            @Result(property = "detail", column = "detail"),
            @Result(property = "pricing", column = "pricing"),
            @Result(property = "price", column = "price"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "grade", column = "grade"),
            @Result(property = "commentNum", column = "comment_num"),
            @Result(property = "sale", column = "sale"),
            @Result(property = "status", column = "status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    List<Product> selectProductByStatus(Integer status);

    /**
     * 需要对sql进行改进，按照出售的本数进行排序
     * @return
     */
    @Select("select * from book_product where category_id = #{categoryId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "author", column = "author"),
            @Result(property = "subtitle", column = "subtitle"),
            @Result(property = "press", column = "press"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "mainImg", column = "main_img"),
            @Result(property = "subImg", column = "sub_img"),
            @Result(property = "detail", column = "detail"),
            @Result(property = "pricing", column = "pricing"),
            @Result(property = "price", column = "price"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "grade", column = "grade"),
            @Result(property = "commentNum", column = "comment_num"),
            @Result(property = "sale", column = "sale"),
            @Result(property = "status", column = "status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    List<Product> selectProductByCategory(Long categoryId);

    @Select("select distinct author from book_product")
    List<String> selectAllAuthor();

    @Select("select distinct name from book_product")
    List<String> selectAllBookName();
}
