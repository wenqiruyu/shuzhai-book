package com.shuzhai.book.service.impl;

import com.shuzhai.book.common.bean.Address;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.dao.IAddressMapper;
import com.shuzhai.book.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressMapper addressMapper;

    @Override
    public ServerResponse<String> addAddress(Address address) {
        if(address != null){
            int i = addressMapper.insertAddress(address);
            if(i == 1){
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试！");
    }

    @Override
    public ServerResponse<List<Address>> queryAddress(Long userId) {
        List<Address> addresses = addressMapper.selectAllAddress(userId);
        if(addresses == null){
            // 未查到该用户地址
            return ServerResponse.createBySuccessMessage("请添加您的收货地址");
        }
        return ServerResponse.createBySuccess(addresses);
    }
}
