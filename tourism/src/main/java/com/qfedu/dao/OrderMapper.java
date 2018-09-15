package com.qfedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qfedu.pojo.Demo;
import com.qfedu.pojo.Order;

@Repository
public interface OrderMapper {
	/**
	 * 将订单添加到 数据库
	 */
	@Insert("insert into `order` (o_id,uuid,g_t_id,o_state,o_number) values(null,#{uuid},#{g_t_id},#{o_state},#{o_number})")
	int addOrder(Order order);

	/**
	 * 根据参数 查询当前用户 订单
	 */
	@Select("select * from `order` where uuid=#{uuid} and o_state = #{o_state}  and g_t_id = #{g_t_id}")
	List<Order> selectOrder(Order order);
	/**
	 * 根据参数 查询当前用户所有订单信息
	 */
	@Select("select * from `order` where uuid=#{uuid} and o_state = #{o_state} ")
	List<Order> selectAllOrder(Order order);
	
	
	
	/**
	 * 根据message 查询当前订单付款信息.
	 */
	@Select("select * from `message` where message=#{message}")
	Demo selectMessage(Demo demo);

	/**
	 * 订单完成付款后,修改订单状态.
	 */
	@Update("update `order` set o_state= 1 ,o_pay_time=now() where g_t_id=#{g_t_id}")
	int updateOrder(int g_t_id);

	/**
	 * 根据订单id 查询订单
	 */
	@Select("select * from `order` where o_id=#{o_id} ")
	Order selectOrderByUuidAndO_id(Order order);

}
