package com.qfedu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qfedu.pojo.Coupons1;
import com.qfedu.pojo.Demo;
import com.qfedu.pojo.Order;

@Service
public interface OrderService {
	
	/**
	 * 组合订单需要付款的 金额. 
	 */
	String payOrder(List<Order> data,Demo demo,Coupons1 coupons);
	
	/**
	 *  查询 待付款/已付款/历史     订单
	 */
	String selectOrder(Order order);
	
	/**
	 *   查询 待付款/已付款/历史     订单     对应的景点
	 */
	String selectTitle(Order order);
	
	/**
	 * 付款计算.
	 */
	double payOrder_No1(Order order);
	
	/**
	 * 添加 待付款商品.
	 */
	String addOrder(Order order);
	
	
	
	

}
