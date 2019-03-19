package com.shuzhai.book.service;

import com.shuzhai.book.common.bean.Category;
import com.shuzhai.book.common.utils.ServerResponse;

import java.util.List;

public interface ICategoryService {

    ServerResponse<List<Category>> findAllCategory();
}
