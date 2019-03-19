package com.shuzhai.book.service.impl;

import com.shuzhai.book.common.bean.Cart;
import com.shuzhai.book.common.bean.Product;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.dao.ICartMapper;
import com.shuzhai.book.dao.IProductMapper;
import com.shuzhai.book.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartMapper cartMapper;
    @Autowired
    private IProductMapper productMapper;

    @Override
    public ServerResponse<String> addCart(Cart cart) {
        if (cart != null) {
            // 查询用的购物车
            List<Cart> list = cartMapper.selectCartByUser(cart.getUserId());
            boolean flag = false;
            Long bookNum = null;
            for (Cart c : list) {
                if (c.getProductId() == cart.getProductId()) {
                    flag = true;
                    bookNum = c.getQuantity();
                }
            }
            // 如果该用户加入过相同的图书进购物车中
            if (flag) {
                cartMapper.updateCartQuantity(cart.getQuantity() + bookNum, cart.getUserId());
                return ServerResponse.createBySuccess("成功添加进购物车");
            } else {
                int result = cartMapper.insertCart(cart);
                if (result == 1) {
                    return ServerResponse.createBySuccess("成功添加进购物车");
                }
                return ServerResponse.createByErrorMessage("添加购物车失败，请重新操作");
            }
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @Override
    public ServerResponse<List<Product>> findCartByUser(Long userId) {
        List<Cart> list = cartMapper.selectCartByUser(userId);
        List<Product> proList = new ArrayList<>();
        if (list != null) {
            // 将商品具体信息查询出来
            for (Cart cart : list) {
                Product product = productMapper.selectProductById(cart.getProductId());
                proList.add(product);
            }
            return ServerResponse.createBySuccess(proList);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @Override
    public ServerResponse<List<Cart>> findCartByUserId(Long userId) {
        List<Cart> carts = cartMapper.selectCartById(userId);
        if (carts == null) {
            // 查询数据库没有数据，进行提示用户
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(carts);
    }

    @Override
    public ServerResponse<String> findUserCartNum(Long userId) {
        Long bookNum = cartMapper.selectCartNum(userId);
        return ServerResponse.createBySuccess(bookNum.toString());
    }

    @Override
    public ServerResponse<String> updateCartNum(Long quantity, Long id) {
        int i = cartMapper.updateCartQuantity(quantity, id);
        if (i == 1) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
