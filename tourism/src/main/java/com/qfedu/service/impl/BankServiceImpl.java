package com.qfedu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qfedu.dao.BankMapper;

import com.qfedu.pojo.Bank;
import com.qfedu.pojo.JS;
import com.qfedu.pojo.Users;
import com.qfedu.service.BankService;

@Service
public class BankServiceImpl implements BankService {
	@Resource
	private BankMapper bankMapper;

	@Override
	public String addBank(Bank bank) {
		int code = 100;
		if (bankMapper.selectBank(bank) == null) {
			bankMapper.insertUser(bank);
			code = 101;
		}
		JS<Bank> js = new JS<>(code, null, null);
		return JSON.toJSONString(js);
	}

	@Override
	public String selectBankByUuid(Users user) {
		int code = 100;
		List<Bank> data = bankMapper.selectAllBank(user);
		if (data.size() > 0) {
			code = 101;
		}
		JS<Bank> js = new JS<>(code, data);
		return JSON.toJSONString(js);
	}
	
	@Override
	public String deleteOneBank(Bank bank) {
		JS js = new JS<>(100 + bankMapper.deleteOneBank(bank));
		return JSON.toJSONString(js);
	}

//	// 接收前端传递的 数组进行计算
//	public String payOrder(Order[] order, Coupons1 coupons1) {
//		List<Double> data = null;
//		Double sumMoney = 0.0;
//		for (int i = 0; i < order.length; i++) {
//			Double d = payOrder_No1(order[i]);
//			data.add(d);
//			sumMoney += d;
//		}
//		// 查询优惠订单是否存在,存在则 在总价里 减去 优惠的部分
//		if (coupons1 != null) {
//			Coupons1 cou = bankMapper.selectCoupons(coupons1);
//			if (cou != null) {
//				sumMoney -= cou.getCou_money();
//			}
//		}
//		
//		JS<Double> js = new JS<>(101, "" + sumMoney, data);
//		return JSON.toJSONString(js);
//	}
	

	/*-	@Override
	//	public String selectOrder(Order order) {
	//		// 这里添加的数据是混乱的,不能使用泛型.
	//		// XXX 需要拆分修改.
	//		List tit = new ArrayList<>();
	//		int code = 100;
	//		String msg = "无数据";
	//		List<Order> data = bankMapper.selectOrder(order);
	//		if (data != null) {
	//			msg = null;
	//			code = 101;
	//			// XXX 有时间了,将这里的查询优化!!!
	//			for (int i = 0; i < data.size(); i++) {
	//				// 这里提取查询到的 标题id ,将标题信息全部查询
	//				// 然后把两个 查询的集合 组合为 一个 集合.
	//				// 0下标对应查询的标题信息,1对应的订单信息...2和3,4和5以此类推
	//				Title t = titleMapper.selectById(data.get(i).getG_t_id());
	//				tit.add(t);
	//				tit.add(data.get(i));
	//			}
	//		}
	//		JS js = new JS(code, msg, data);
	//		return JSON.toJSONString(js);
		}
	*/

//	@Override
//	public String selectOrder(Order order) {
//
//		int code = 100;
//		List<Order> data = bankMapper.selectOrder(order);
//		if (data != null) {
//			code = 101;
//		}
//		JS<Order> js = new JS<>(code, data);
//		return JSON.toJSONString(js);
//	}

//	@Override
//	public String selectTitle(Order order) {
//		int code = 100;
//		List<Order> or = bankMapper.selectOrder(order);
//		List<Title> data = new ArrayList<>();
//		if (or != null) {
//			code = 101;
//			// 使用循环查询 订单对应的商品
//			for (int i = 0; i < or.size(); i++) {
//				// 根据 订单对应的旅游title id查询 对应的 旅游
//				Title t = titleMapper.selectById(or.get(i).getG_t_id());
//				data.add(t);
//			}
//		}
//		JS<Title> js = new JS<>(code, data);
//		return JSON.toJSONString(js);
//	}


//	@Override
//	public String addOrder(Order order) {
//		int code = 100;
//		order.setO_state(0);//
//		// 先查询是否已 有订单! ==null则表示 当前商品 没有 未付款的订单
//		if (null == bankMapper.selectOrder(order)) {
//			code += bankMapper.addOrder(order);
//		}
//		JS<Order> js = new JS<>(code);
//		return JSON.toJSONString(js);
//	}
//	@Override
//	public double payOrder_No1(Order orders) {
//		Order order = bankMapper.selectOrderByUuidAndO_id(orders);
//		double money = 0;
//		if (null != order) {
//			int num = 0;
//			if ((num = (order.getO_number())) > 0) {
//				double money1 = titleMapper.selectG_t_moneyById(orders.getG_t_id());
//				money = money1 * num;
//			}
//		}
//		return money;
//	}

}
