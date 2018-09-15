package com.qfedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qfedu.pojo.Bank;
import com.qfedu.pojo.Users;

@Repository
public interface BankMapper {

	/**
	 * 查询当前的银行卡 号是否存在.
	 */
	@Select("select bank_id from bank where bank_number = #{bank_number} ")
	Bank selectBank(Bank bank);

	/**
	 * 储存 银行卡.
	 */
	@Insert("insert into bank (uuid,bank_id,bank_number) values(#{uuid},null,#{bank_number})")
	int insertUser(Bank bank);

	/**
	 * 根据用户uuid 查询当前用户 拥有的 所有银行卡.
	 */
	@Select("select * from bank where uuid = #{uuid}")
	List<Bank> selectAllBank(Users user);

	/**
	 * 删除一张银行卡号
	 */
	@Delete("delete from bank where bank_id=#{bank_id}  and bank_number=#{bank_number}")
	int deleteOneBank(Bank bank);


//	///////////// 下面是优惠劵的
//	/**
//	 * 查询一个用户的全部优惠劵.
//	 */
//	@Select("select * from coupons where uuid=#{uuid}")
//	List<Coupons1> selectAllCoupons(Users user);
//
//	/**
//	 * 根据优惠劵的id 来查询 当前优惠劵是否存在
//	 */
//	@Select("select * from coupons where uuid=#{uuid} and cou_id=#{cou_id}")
//	Coupons1 selectCoupons(Coupons1 coupons1);
//
//	/**
//	 * 添加优惠劵
//	 */
//	@Insert("insert into coupons (cou_id,uuid,cou_money,cou_time) values(null,#{uuid},#{cou_money},null)")
//	int inserCoupons(Coupons1 coupons);

	///////////////// 下面是银行卡号的

	
	
	//////////////////// 下面是 订单类的.

//	/**
//	 * 将订单添加到 数据库
//	 */
//	@Insert("insert into 'order' (o_id,uuid,g_t_id,o_state,o_new_time) values(null,#{uuid},#{g_t_id},#{o_state},now())")
//	int addOrder(Order order);
//
//	/**
//	 * 根据参数 查询当前用户 订单
//	 */
//	@Select("select * from 'order' where uuid=#{uuid} and o_state = #{o_state} ")
//	List<Order> selectOrder(Order order);
//
//	/**
//	 * 根据订单id 查询订单
//	 */
//	@Select("select * from 'order' where o_id=#{o_id} ")
//	Order selectOrderByUuidAndO_id(Order order);

}
