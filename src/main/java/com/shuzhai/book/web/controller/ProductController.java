package com.shuzhai.book.web.controller;

import com.shuzhai.book.common.bean.Product;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 获取热卖商品，用于首页展示
     * @return
     */
    @GetMapping(value = "/getHot")
    @CrossOrigin
    public ServerResponse<List<Product>> getHotProduct(){
        // 热卖状态是 4
        return productService.findProductByStatus(4);
    }

    /**
     * 获取所有图书
     */
    @GetMapping(value = "/getAllProduct")
    @CrossOrigin
    public ServerResponse<List<Product>> getAllProduct(){
        return productService.findAllProduct();
    }

    /**
     * 根据分类id查询商品
     */
    @GetMapping(value = "getBooksByCategory")
    @CrossOrigin
    public ServerResponse<List<Product>> getBooksByCategory(@RequestParam(value = "categoryId", required = true)String categoryId){
        return productService.findProductByCategory(Long.parseLong(categoryId));
    }

    /**
     * 根据商品id查询商品信息
     */
    @GetMapping(value = "getBookById")
    @CrossOrigin
    public ServerResponse<Product> getBookById(@RequestParam(value = "bookId", required = true)String bookId){
        return productService.findProductById(Long.parseLong(bookId));
    }
}
