package com.qfedu.service.impl;

import java.util.List;

// 优惠劵
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qfedu.dao.BankMapper;
import com.qfedu.dao.CollectionMapper;
import com.qfedu.dao.CouponsMapper;
import com.qfedu.dao.OrderMapper;
import com.qfedu.dao.TitleMapper;
import com.qfedu.pojo.Coupons1;
import com.qfedu.pojo.JS;
import com.qfedu.pojo.Users;
import com.qfedu.service.CouponsService;

@Service
public class CouponsServiceImpl implements CouponsService {
	@Resource
	private CouponsMapper couponsMapper;


	@Override
	public String selectCoupons(Users user) {
		List<Coupons1> data = couponsMapper.selectAllCoupons(user);
		int code = 100;
		if (data.size() > 0) {
			code = 101;
		}
		JS<Coupons1> js = new JS<>(code, data);
		return JSON.toJSONString(js);
	}

}
