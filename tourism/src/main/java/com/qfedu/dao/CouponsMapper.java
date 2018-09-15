package com.qfedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qfedu.pojo.Coupons1;
import com.qfedu.pojo.Users;

@Repository
public interface CouponsMapper {
	/**
	 * 查询一个用户的全部优惠劵.
	 */
	@Select("select * from coupons where uuid=#{uuid}")
	List<Coupons1> selectAllCoupons(Users user);

	/**
	 * 根据优惠劵的id 来查询 当前优惠劵是否存在
	 */
	@Select("select * from coupons where uuid=#{uuid} and cou_id=#{cou_id}")
	Coupons1 selectCoupons(Coupons1 coupons1);

	/**
	 * 添加优惠劵
	 */
	@Insert("insert into coupons (cou_id,uuid,cou_money,cou_time) values(null,#{uuid},#{cou_money},null)")
	int inserCoupons(Coupons1 coupons);
	/**
	 * 删除一张优惠劵
	 */
	@Delete("delete from coupons where cou_id = #{cou_id}")
	int deleteCoupons(Coupons1 coupons);

}
