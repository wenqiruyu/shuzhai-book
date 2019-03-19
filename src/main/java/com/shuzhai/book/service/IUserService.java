package com.shuzhai.book.service;


import com.shuzhai.book.common.bean.User;
import com.shuzhai.book.common.utils.ServerResponse;

public interface IUserService {

    ServerResponse<String> userRegister(User user);

    /**
     * 0表示用户名存在，1表示用户名不存在
     * @param username
     * @return
     */
    ServerResponse<Integer> userVerify(String username);

    ServerResponse<User> userLogin(String username, String password);

    ServerResponse<User> phoneVerify(String phone);

    ServerResponse<User> userInfoByName(String username);
}
