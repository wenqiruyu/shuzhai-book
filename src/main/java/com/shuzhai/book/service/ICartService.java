package com.shuzhai.book.service;

import com.shuzhai.book.common.bean.Cart;
import com.shuzhai.book.common.bean.Product;
import com.shuzhai.book.common.utils.ServerResponse;

import java.util.List;

public interface ICartService {

    ServerResponse<String> addCart(Cart cart);

    ServerResponse<List<Product>> findCartByUser(Long userId);

    ServerResponse<List<Cart>> findCartByUserId(Long userId);

    ServerResponse<String> findUserCartNum(Long userId);

    ServerResponse<String> updateCartNum(Long quantity, Long id);
}
