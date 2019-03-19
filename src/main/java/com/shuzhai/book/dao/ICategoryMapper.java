package com.shuzhai.book.dao;

import com.shuzhai.book.common.bean.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ICategoryMapper {

    @Insert("insert into book_category(parent_id,name,status,sort_order,create_time,update_time) " +
            "values(#{parentId},#{name},#{status},#{sortOrder},#{createTime},#{updateTime})")
    int insertCategory(Category category);

    @Select("select * from book_category")
    List<Category> selectAll();
}
