package com.qfedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qfedu.pojo.Address;
import com.qfedu.pojo.Collection1;
import com.qfedu.pojo.Goods;
import com.qfedu.pojo.Title;
import com.qfedu.pojo.Users;

@Repository
public interface TitleMapper {

	/**
	 * 为主页查询数据,
	 */
	@Select("select * from title where g_t_id in(12036,12200,12450,13293,13032,13350,13250,10005,10250,10520,10981,11211,11535)")
	List<Title> selectIndex();

	/**
	 * 根据g_t_address 查询最大条目数
	 */
	@Select("select count(*) from title where g_t_address = #{g_t_address} ")
	int selectCountByAddress(String g_t_address);

	/**
	 * 根据 Address
	 */
	// select * from t limit 100,20 ;跳过100行之后，拿20行
	@Select("select * from title where g_t_address = #{g_t_address} limit #{page},#{number}")
	List<Title> selectByAddress(Address ads);

	/**
	 * 根据 用户id 和 商品的类型(收藏或者是最近浏览) 查询总数量.
	 */
	@Select("select count(*) from collection where uuid = #{uuid} and c_type = #{c_type}")
	int selectCollectionMaxNum(Collection1 coll);

	/**
	 * 根据商品标题id 查询当前商品,是否已被当前用户收藏/浏览;
	 */
	@Select("select * from title where g_t_id = #{g_t_id}")
	Title selectOneTitle(Title title);

	/**
	 * 根据Collection 查询所有的 收藏/最近浏览.
	 */
	@Select("select * from collection where uuid = #{uuid} and c_type = #{c_type} order by c_id desc LIMIT 20")
	List<Collection1> selectAllCollection(Collection1 coll);

	/**
	 * 根据标题id 查询 商品 详情.
	 */
	@Select("select * from goods where g_t_id = #{g_t_id}")
	Goods selectGoodsByTitleId(Title title);

	/**
	 * 根据Collection1查询 当前的最近浏览记录是否存在.
	 */
	@Select("select * from collection where g_t_id = #{g_t_id} and uuid = #{uuid} and c_type = #{c_type}")
	Title selectCollection(Collection1 coll);

	/**
	 * 修改最近浏览记录中的 最近浏览的时间.
	 */
	@Update("update collection set c_time = now() where g_t_id = #{g_t_id} and uuid = #{uuid} and c_type = #{c_type}")
	int updateTime(Collection1 coll);

	
	
	/**
	 * 删除一个 用户收藏
	 */
	@Delete("delete from collection where g_t_id=#{g_t_id} and uuid=#{uuid}")
	int deleteOneCollection(Collection1 coll);
	
	/**
	 * 删除 最近浏览中最早的10 条数据.
	 */
	// XXX 语句不会写,,回头在整 : delete from goods order by id limit 10;
	@Delete("")
	int deleteCollection(Collection1 coll);

	/**
	 * 将 当前浏览 / 收藏 添加到 数据库
	 */
	@Insert("insert into collection (c_type,c_time,uuid,g_t_id) values(#{c_type},now(),#{uuid},#{g_t_id})")
	int insertCollection(Collection1 coll);

	/**
	 * 根据标题id 查询 标题全部内容
	 */

	@Select("select * from title where g_t_id = #{g_t_id}")
	Title selectById(int g_t_id);

	/**
	 * 根据标题id 查询价格.
	 */
	@Select("select g_t_money from title where g_t_id = #{g_t_id}")
	double selectG_t_moneyById(int g_t_id);

}
