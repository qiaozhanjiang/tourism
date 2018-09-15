package com.qfedu.service;


import org.springframework.stereotype.Service;

import com.qfedu.pojo.Address;
import com.qfedu.pojo.Title;

@Service
public interface TitleService {

	
	
	
	/**
	 * 根据地址对应的id查询数据,依据 number 和 page.返回js字符串.
	 */
	String selectByAddress(Address ads);
	/**
	 * 根据g_t_id查询 单条数据
	 */
	String selectTitleById(Title title);
	
	/**
	 * 为主界面提供数据.
	 */
	String selectIndex();

	/**
	 * 查询商品详情,根据标题id ; 然后将当前id 传递给 收藏商品的方法,直接储存到最近浏览(暂时设置 最大最近浏览数量为20.)
	 */
	String selectGoodsByTitleId(Title title, String uuid);

//	/**
//	 * 删除一个 用户收藏
//	 */
//	String deleteOneCollection(Collection1 coll);
	
//	/**
//	 * 将商品添加到 最近浏览
//	 */
//	void addBrowse(Collection1 coll);

//	/**
//	 * 将商品 添加到 收藏
//	 */
//	String addCollection(Collection1 coll);


//	/**
//	 * 查询 收藏/最近浏览
//	 */
//	String selectAllCollection(Collection1 coll);

//	/**
//	 * 查询 收藏/最近浏览,在数据库中已存在的数量.
//	 */
//	int selectCollectionMaxNum(Collection1 coll);

}
