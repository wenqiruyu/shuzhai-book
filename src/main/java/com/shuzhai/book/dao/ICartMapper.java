package com.shuzhai.book.dao;

import com.shuzhai.book.common.bean.Cart;
import com.shuzhai.book.common.bean.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICartMapper {

    @Insert("insert into book_cart(user_id,product_id,quantity,checked) values(#{userId},#{productId},#{quantity},#{checked})")
    int insertCart(Cart cart);

    @Select("select * from book_cart where user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "checked", column = "checked"),
    })
    List<Cart> selectCartByUser(Long userId);

    @Select("select count(id) from book_cart where user_id = #{userId}")
    Long selectCartNum(Long userId);

    @Update("update book_cart set quantity=#{quantity} where id=#{id}")
    int updateCartQuantity(Long quantity,Long id);

    /*@Select("select id,name,subtitle,price,cart.product_id,cart.quantity " +
            "from book_product,(" +
            "select user_id,product_id,quantity " +
            "from book_cart " +
            "where user_id = #{id} " +
            ")cart " +
            "where book_product.id = cart.product_id")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Cart.class),
            @Result(property = "productId", column = "product_id", javaType = Cart.class),
            @Result(property = "quantity", column = "quantity", javaType = Cart.class)
    })*/
    @Select("select * from book_cart where user_id = #{userId}")
    @Results({
            @Result(property = "product", column = "product_id", one = @One(select = "com.shuzhai.book.dao.IProductMapper.selectProductById")),
    })
    List<Cart> selectCartById(Long id);
}
