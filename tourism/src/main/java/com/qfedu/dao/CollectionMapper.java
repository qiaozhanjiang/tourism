package com.qfedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qfedu.pojo.Collection1;
import com.qfedu.pojo.Title;
@Repository
public interface CollectionMapper {
	
	
	/**
	 * 根据 用户id 和 商品的类型(收藏或者是最近浏览) 查询总数量.
	 */
	@Select("select count(*) from collection where uuid = #{uuid} and c_type = #{c_type}")
	int selectCollectionMaxNum(Collection1 coll);
	
	/**
	 * 根据Collection 查询所有的 收藏/最近浏览.
	 */
	@Select("select * from collection where uuid = #{uuid} and c_type = #{c_type} order by c_id desc LIMIT 20")
	List<Collection1> selectAllCollection(Collection1 coll);

	
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
	 * 删除 最近浏览中最早的10 条数据.//方法未启用
	 */
	// XXX 语句不会写,,回头在整 : delete from goods order by id limit 10;
	@Delete("")
	int deleteCollection(Collection1 coll);

	/**
	 * 将 当前浏览 / 收藏 添加到 数据库
	 */
	@Insert("insert into collection (c_type,c_time,uuid,g_t_id) values(#{c_type},now(),#{uuid},#{g_t_id})")
	int insertCollection(Collection1 coll);

	
	
	
	
	
	

}
