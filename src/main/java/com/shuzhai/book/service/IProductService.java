package com.shuzhai.book.service;

import com.shuzhai.book.common.bean.Product;
import com.shuzhai.book.common.utils.ServerResponse;

import java.util.List;

public interface IProductService {

    ServerResponse<String> addProduct(Product product);

    ServerResponse<Product> findProductByName(String name);

    ServerResponse<Product> findProductById(Long id);

    ServerResponse<List<Product>> findProductByAuthor(String author);

    ServerResponse<List<Product>> findProductByStatus(Integer status);

    ServerResponse<List<Product>> findProductByCategory(Long categoryId);

    ServerResponse<List<Product>> findAllProduct();

    ServerResponse<List<String>> findAllAuthor();

    ServerResponse<List<String>> findAllBookName();
}
