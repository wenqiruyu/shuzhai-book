package com.shuzhai.book.dao;

import com.shuzhai.book.common.bean.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IAddressMapper {

    @Insert("insert into book_address(user_id,consignee_name,consignee_phone,detail,status,create_time,update_time)" +
    " values(#{userId},#{consigneeName},#{consigneePhone},#{provinces},#{detail},#{status},#{createTime},#{updateTime})")
    int insertAddress(Address address);

    @Select("select * from book_address where user_id = #{userId} and status > -1")
    List<Address> selectAllAddress(Long userId);
}
