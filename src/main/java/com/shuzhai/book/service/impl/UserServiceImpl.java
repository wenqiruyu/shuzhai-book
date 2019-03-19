package com.shuzhai.book.service.impl;

import com.shuzhai.book.common.bean.User;
import com.shuzhai.book.common.excption.UserException;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.dao.IUserMapper;
import com.shuzhai.book.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public ServerResponse<String> userRegister(User user){
        if(user != null){
            // 考虑采用MD5加密的方法，并加盐，将密码加密存储
            int resultCount = userMapper.insertUser(user);
            if(resultCount == 0){
                return ServerResponse.createByErrorMessage("注册失败");
            }
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    /**
     * 根据用户名进行验证
     * @param username
     * @return
     * @throws UserException
     */
    @Override
    public ServerResponse<Integer> userVerify(String username){
        if(!StringUtils.isBlank(username)){
            User user = userMapper.selectByName(username);
            // 判断查询用户名是否存在
            if(user == null){
                // 可以返回正确码，1
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<User> userLogin(String username, String password){
        if(!StringUtils.isBlank(username)){
            String pwd = userMapper.selectPwdByName(username);
            if(pwd == null){
                return ServerResponse.createByErrorMessage("用户名未注册");
            }else{
                if(!password.equals(pwd)){
                    return ServerResponse.createByErrorMessage("密码不正确");
                }
            }
        }
        User user = userMapper.selectByName(username);
        return ServerResponse.createBySuccess(new User(user.getId(),user.getUsername()));
    }

    @Override
    public ServerResponse<User> phoneVerify(String phone) {
        User user = userMapper.selectByPhone(phone);
        if(user == null){
            // 用于进行手机号验证
            return ServerResponse.createByErrorMessage("手机号不存在");
        }
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse<User> userInfoByName(String username) {
        User user = null;
        if(!StringUtils.isBlank(username)){
            // 根据用户名字查询
            user = userMapper.selectByName(username);
            if(user != null){
                return ServerResponse.createBySuccess(user);
            }
            return ServerResponse.createByErrorMessage("系统错误");
        }
        return ServerResponse.createByErrorMessage("系统错误");
    }
}
