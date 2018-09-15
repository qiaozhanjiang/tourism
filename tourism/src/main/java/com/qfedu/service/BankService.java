package com.qfedu.service;
import org.springframework.stereotype.Service;

import com.qfedu.pojo.Bank;
import com.qfedu.pojo.Users;

@Service
public interface BankService {
	
	/**
	 * 删除一张银行卡 
	 */
	
	String deleteOneBank(Bank bank);
	
	/**
	 * 储存一张 银行卡号,返回js字符串
	 */
	String addBank(Bank bank);
	
	/**
	 * 根据用户uuid 查询当前用户的所有银行卡,返回js字符串
	 */
	String selectBankByUuid(Users user);
	
	
	
//	/**
//	 * 组合订单需要付款的 金额. 
//	 */
//	String payOrder(Order[] order,Coupons1 coupons);
	
	
//	/**
//	 *  查询 待付款/已付款/历史     订单
//	 */
//	String selectOrder(Order order);
	
	
//	/**
//	 *   查询 待付款/已付款/历史     订单     对应的景点
//	 */
//	String selectTitle(Order order);
	
//	/**
//	 * 付款计算.
//	 */
//	double payOrder_No1(Order order);
	
//	/**
//	 * 添加 待付款商品.
//	 */
//	String addOrder(Order order);

	
	
	
	
	
	
}
