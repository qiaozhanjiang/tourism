package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qfedu.dao.BankMapper;
import com.qfedu.dao.CollectionMapper;
import com.qfedu.dao.CouponsMapper;
import com.qfedu.dao.OrderMapper;
import com.qfedu.dao.TitleMapper;
import com.qfedu.pojo.Coupons1;
import com.qfedu.pojo.Demo;
import com.qfedu.pojo.JS;
import com.qfedu.pojo.Order;
import com.qfedu.pojo.Title;
import com.qfedu.pojo.Users;
import com.qfedu.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private CouponsMapper couponsMapper;

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private TitleMapper titleMapper;

	@Override
	public String addOrder(Order order) {
		int code = 100;
		order.setO_state(0);//
		// 查询是否已 有订单!
		if (orderMapper.selectOrder(order).size() < 1) {
			// 如果订单数量为0,则改为1.
			if (order.getO_number() == 0) {
				order.setO_number(1);
			}
			code += orderMapper.addOrder(order);
		}
		JS<Order> js = new JS<>(code);
		return JSON.toJSONString(js);
	}

	@Override
	public String selectOrder(Order order) {

		int code = 100;
		List<Order> data = orderMapper.selectAllOrder(order);
		if (data.size() > 0) {
			code = 101;
		}
		JS<Order> js = new JS<>(code, data);
		return JSON.toJSONString(js);
	}

	@Override
	public String selectTitle(Order order) {
		int code = 100;
		List<Order> or = orderMapper.selectAllOrder(order);
		List<Title> data = new ArrayList<>();
		if (or != null) {
			code = 101;
			// 使用循环查询 订单对应的商品
			for (int i = 0; i < or.size(); i++) {
				// 根据 订单对应的旅游title id查询 对应的 旅游
				Title t = titleMapper.selectById(or.get(i).getG_t_id());
				data.add(t);
			}
		}
		JS<Title> js = new JS<>(code, data);
		return JSON.toJSONString(js);
	}

	// 接收前端传递的 数组进行计算
	@Override
	public String payOrder(List<Order> orders, Demo demo, Coupons1 coupons1) {
		List<Double> data = new ArrayList<Double>(); // 多个账单时,会把每个账单的价格储存于数组

		Double sumMoney = 0.0; // 储存结账总金额.
		for (int i = 0; i < orders.size(); i++) {
			 double d = payOrder_No1(orders.get(i));
			sumMoney += d;
			data.add(d);
		}
		// 查询优惠订单是否存在,存在则 在总价里 减去 优惠的部分
		if (coupons1 != null) {
			Coupons1 cou = couponsMapper.selectCoupons(coupons1);
			if (cou != null) {
				sumMoney -= cou.getCou_money();
			}
		}
		// ---------------下面进行付款判断----------------
		// 如果前端传递的 message参数不是null,表示用户有付款,查询数据库中是否有对应的付款数据
		if (demo.getMessage() != null) {
			int code = 100;
			Demo o = orderMapper.selectMessage(demo);
			// 这里拿数据库的message 和 前端传递的 消息 进行equals比较.数据库收到的钱和账单应付款的金额比较 来确定是否已付款
			if (null != o && o.getMessage().equals(demo.getMessage()) && o.getMessageMoney() == sumMoney) {
				code = 1001;
				if (null != coupons1) {
					couponsMapper.deleteCoupons(coupons1);
				}
				for (int i = 0; i < orders.size(); i++) {
					orderMapper.updateOrder(orders.get(i).getG_t_id());
				}
			}
			JS js = new JS(code);
			return JSON.toJSONString(js);
		}
		// ---------------付款判断到此结束------------------

		JS<Double> js = new JS<>(101, "" + sumMoney, data);
		return JSON.toJSONString(js);
	}

	@Override
	public double payOrder_No1(Order orders) {
		// Order order = orderMapper.selectOrderByUuidAndO_id(orders);
		double money1 = titleMapper.selectG_t_moneyById(orders.getG_t_id());
		int num = 0;
		if ((num = orders.getO_number()) < 1) {
			num = 1;
		}
		return money1 * num;
	}

}
