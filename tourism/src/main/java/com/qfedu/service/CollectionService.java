package com.qfedu.service;

import org.springframework.stereotype.Service;

import com.qfedu.pojo.Collection1;

// 收藏.
@Service
public interface CollectionService {
	/**
	 * 删除一个 用户收藏
	 */
	String deleteOneCollection(Collection1 coll);

	/**
	 * 将商品添加到 最近浏览
	 */
	void addBrowse(Collection1 coll);

	/**
	 * 将商品 添加到 收藏
	 */
	String addCollection(Collection1 coll);

	/**
	 * 查询 收藏/最近浏览
	 */
	String selectAllCollection(Collection1 coll);

	/**
	 * 查询 收藏/最近浏览,在数据库中已存在的数量.
	 */
	int selectCollectionMaxNum(Collection1 coll);

}
