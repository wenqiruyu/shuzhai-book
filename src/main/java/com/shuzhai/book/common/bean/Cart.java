package com.shuzhai.book.common.bean;

import lombok.Data;

/**
 * 购物车
 */
@Data
public class Cart {

    private Long id;
    // 所属用户id
    private Long userId;
    // 商品id
    private Long productId;
    // 商品数量
    private Long quantity;
    // 是否进行勾选 1表示勾选 0表示未勾选
    private Integer checked;

    public Product product;

    public Cart() {
    }

    public Cart(Long userId, Long productId, Long quantity, Integer checked) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
    }

    public Cart(Long userId, Long productId, Long quantity, Integer checked, Product product) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
        this.product = product;
    }
}
