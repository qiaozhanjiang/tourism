package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qfedu.config.FinalNumber;
import com.qfedu.dao.BankMapper;
import com.qfedu.dao.CollectionMapper;
import com.qfedu.dao.CouponsMapper;
import com.qfedu.dao.OrderMapper;
import com.qfedu.dao.TitleMapper;
import com.qfedu.pojo.Collection1;
import com.qfedu.pojo.JS;
import com.qfedu.pojo.Title;
import com.qfedu.service.CollectionService;
@Service
public class CollectionServiceImpl implements CollectionService ,FinalNumber{
	@Resource
	private BankMapper bankMapper;
	
	@Resource
	private CollectionMapper collectionMapper;
	
	@Resource
	private CouponsMapper couponsMapper;
	
	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private TitleMapper titleMapper;
	

	/**
	 * 将图片的路径 修改为 ip地址,供前端直接使用.
	 */
	public Title addIPAddress(Title t) {
		String st = IP_ADDRESS + t.getG_t_photo();
		t.setG_t_photo(st);
		return t;
	}
	

	@Override
	public String deleteOneCollection(Collection1 coll) {
		titleMapper.deleteOneCollection(coll);
		return null;
	}

	@Override
	public void addBrowse(Collection1 coll) {
		if (titleMapper.selectCollection(coll) != null) {
			System.out.println(coll);
			// 不为空,则当前数据已存在,执行修改 浏览时间操作.
			titleMapper.updateTime(coll);
			return;
		} else
		// 查询当前 数据库储存量,是否已经达到了设定的最大数量;
		if (selectCollectionMaxNum(coll) == MAX_NUMBER + 10) {
			// 删除 最早的10条记录,然后将当前的数据添加到数据库;
			titleMapper.deleteCollection(coll);
		}
		titleMapper.insertCollection(coll);
	}

	@Override
	public String addCollection(Collection1 coll) {
		coll.setC_type("1");
		addBrowse(coll);
		JS js = new JS<>(101);
		return JSON.toJSONString(js);
	}

	@Override
	public String selectAllCollection(Collection1 coll) {
		List<Collection1> arr = new ArrayList<>();
		List<Title> data = new ArrayList<>();
		int code = 100;
		String msg = "没有记录";
		// 查询收藏的数据 总数.

		int count = titleMapper.selectCollectionMaxNum(coll);
		if (count > 0) {
			arr = titleMapper.selectAllCollection(coll);
			for (int i = 0; i < arr.size(); i++) {
				System.out.println(arr.get(i).getG_t_id());
				Title t = titleMapper.selectById(arr.get(i).getG_t_id());
				addIPAddress(t);
				data.add(t);
			}
			code = 101;
			msg = ("" + count);
		}
		JS<Title> js = new JS<>(code, msg, data);
		return JSON.toJSONString(js);
	}

	@Override
	public int selectCollectionMaxNum(Collection1 coll) {
		return titleMapper.selectCollectionMaxNum(coll);
	}

	
	
	
	

}
