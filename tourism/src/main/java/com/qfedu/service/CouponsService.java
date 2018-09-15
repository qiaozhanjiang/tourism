package com.qfedu.service;
// 优惠劵.
import org.springframework.stereotype.Service;

import com.qfedu.pojo.Users;

@Service
public interface CouponsService {
	/**
	 *  查询 优惠劵
	 */
	String selectCoupons(Users user);
	
}
