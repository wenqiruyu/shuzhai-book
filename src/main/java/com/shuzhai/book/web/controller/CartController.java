package com.shuzhai.book.web.controller;

import com.shuzhai.book.common.bean.Cart;
import com.shuzhai.book.common.bean.Product;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.dao.ICartMapper;
import com.shuzhai.book.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping(value = "/getUserCart")
    @CrossOrigin
    public ServerResponse<List<Product>> getUserCart(@RequestParam(value = "userId", required = true)String userId){
        return cartService.findCartByUser(Long.parseLong(userId));
    }

    @GetMapping(value = "/cart/{id}")
    @CrossOrigin
    public ServerResponse<List<Cart>> getCart(@PathVariable(value = "id")Long id){
        return cartService.findCartByUserId(id);
    }

    /**
     * 添加购物车，当用户添加的商品在数据库中有相同用户的商品时，只进行商品书本加1
     * @param userId
     * @param bookId
     * @return
     */
    @GetMapping(value = "/addCart")
    @CrossOrigin
    public ServerResponse addUserCart(@RequestParam(value = "userId", required = true)String userId,@RequestParam(value = "bookId", required = true)String bookId,
                                      @RequestParam(value = "bookNum", required = true)String bookNum){
        return cartService.addCart(new Cart(Long.parseLong(userId),Long.parseLong(bookId),Long.parseLong(bookNum),0));
    }

    /**
     * 查询用户购物车数
     * @param userId
     * @return
     */
    @GetMapping(value = "/getUserCartNum/{userId}")
    @CrossOrigin
    public ServerResponse getUserCartNum(@PathVariable(value ="userId")String userId){
        return cartService.findUserCartNum(Long.parseLong(userId));
    }
}
