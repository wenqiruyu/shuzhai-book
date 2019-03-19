package com.shuzhai.book.web.controller;

import com.shuzhai.book.common.bean.Address;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping(value = "/address")
    public ServerResponse<String> addAddress(){
        return null;
    }

    @GetMapping(value = "/address/{userId}")
    @CrossOrigin
    public ServerResponse<List<Address>> getAddress(@PathVariable(value = "userId", required = true)Long userId){
        return addressService.queryAddress(userId);
    }
}
