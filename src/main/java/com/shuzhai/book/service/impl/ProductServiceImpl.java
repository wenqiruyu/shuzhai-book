package com.shuzhai.book.service.impl;

import com.shuzhai.book.common.bean.Product;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.dao.IProductMapper;
import com.shuzhai.book.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    @Override
    public ServerResponse<String> addProduct(Product product) {
        if(product != null){
            int result = productMapper.insertProduct(product);
            if(result == 1){
                return ServerResponse.createBySuccess();
            }else{
                return ServerResponse.createByErrorMessage("新增图书失败，请稍后重试");
            }
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<Product> findProductByName(String name) {
        if(!StringUtils.isBlank(name)){
            Product product = productMapper.selectProductByName(name);
            if(product != null){
                return ServerResponse.createBySuccess(product);
            }
            return ServerResponse.createByErrorMessage("图书信息未查到");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<Product> findProductById(Long id) {
        Product product = productMapper.selectProductById(id);
        if(product != null){
            return ServerResponse.createBySuccess(product);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<List<Product>> findProductByAuthor(String author) {
        if(!StringUtils.isBlank(author)){
            List<Product> product = productMapper.selectProductByAuthor(author);
            if(product != null){
                return ServerResponse.createBySuccess(product);
            }
            return ServerResponse.createByErrorMessage("图书信息未查到");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<List<Product>> findProductByStatus(Integer status) {
        List<Product> list = productMapper.selectProductByStatus(status);
        if(list != null){
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<List<Product>> findProductByCategory(Long categoryId) {
        List<Product> list = productMapper.selectProductByCategory(categoryId);
        if(list != null){
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<List<Product>> findAllProduct() {
        List<Product> list = productMapper.selectAllProduct();
        if(list != null){
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<List<String>> findAllAuthor() {
        List<String> list = productMapper.selectAllAuthor();
        if(list != null){
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse<List<String>> findAllBookName() {
        List<String> list = productMapper.selectAllBookName();
        if(list != null){
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }
}
