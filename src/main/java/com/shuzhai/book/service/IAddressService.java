package com.shuzhai.book.service;

import com.shuzhai.book.common.bean.Address;
import com.shuzhai.book.common.utils.ServerResponse;

import java.util.List;

public interface IAddressService {

    ServerResponse<String> addAddress(Address address);

    ServerResponse<List<Address>> queryAddress(Long userId);
}
