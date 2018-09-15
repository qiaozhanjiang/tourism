package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qfedu.config.FinalNumber;
import com.qfedu.dao.TitleMapper;
import com.qfedu.pojo.Address;
import com.qfedu.pojo.Collection1;
import com.qfedu.pojo.Goods;
import com.qfedu.pojo.JS;
import com.qfedu.pojo.Title;
import com.qfedu.pojo.Users;
import com.qfedu.service.TitleService;

@Service
public class TitleServiceImpl implements TitleService, FinalNumber {
	@Resource
	private TitleMapper titleMapper;
	
	private CollectionServiceImpl collctionService;

	/**
	 * 将图片的路径 修改为 ip地址,供前端直接使用.
	 */
	public Title addIPAddress(Title t) {
		String st = IP_ADDRESS + t.getG_t_photo();
		t.setG_t_photo(st);
		return t;
	}
	
	

	@Override
	public String selectByAddress(Address ads) {
		JS<Title> js = new JS<>();
		List<Title> data = new ArrayList<>();

		if (ads.getG_t_address() == null || ads.getG_t_address().length() < 1) {
			ads.setG_t_address("102");// 如果参数输入有误,则查询 河南周边.
		}
		// 这里查询数据库 里一共有多少条数据,并赋值给cont.
		int count = titleMapper.selectCountByAddress(ads.getG_t_address());
		if (ads.getNumber() < 1 || ads.getNumber() > 20) {
			ads.setNumber(10);
		}
		// 根据查询到的数据总数,和每页的显示数量,计算出一共可以分 numPage 页.
		int num1 = ads.getNumber();
		int numPage = (count % num1) == 0 ? count / num1 : count / num1 + 1;
		// 如果要显示的页码 小于 1,则改为1,如果大于最大页码,则改为最大页码数.
		int in = ads.getPage() < 1 ? 1 : ads.getPage() > numPage ? numPage : ads.getPage();
		ads.setPage(in);
		// 根据偏移量计算出需要显示的数据
		ads.setPage((ads.getPage() - 1) * ads.getNumber());
		data = titleMapper.selectByAddress(ads);
		for (int i = 0; i < data.size(); i++) {
			addIPAddress(data.get(i));
		}

		js.setCode(101);
		js.setMsg("" + numPage);// 把总page页数储存
		js.setData(data);
		return JSON.toJSONString(js);
	}


	@Override
	public String selectGoodsByTitleId(Title title, String uuid) {
		JS<Goods> js = new JS<>();
		List<Goods> data = new ArrayList<>();
		int code = 100;
		if (0 == YES_NO) {
			title.setG_t_id(1);
		}
		Goods goods = titleMapper.selectGoodsByTitleId(title);
		if (goods != null) {
			data.add(goods);
			js.setData(data);
			code = 101;
			if (uuid != null) {
				// 到这里,查询的请求已完成,下面执行 将本条信息,添加到最近浏览.
				Collection1 c = new Collection1();
				c.setC_type("0");
				c.setG_t_id(title.getG_t_id());
				c.setUuid(uuid);
				collctionService.addBrowse(c);
			}
		}
		js.setCode(code);
		return JSON.toJSONString(js);
	}
	
	
	
//	@Override
//	public String deleteOneCollection(Collection1 coll) {
//		titleMapper.deleteOneCollection(coll);
//		return null;
//	}

//	@Override
//	public void addBrowse(Collection1 coll) {
//		if (titleMapper.selectCollection(coll) != null) {
//			System.out.println(coll);
//			// 不为空,则当前数据已存在,执行修改 浏览时间操作.
//			titleMapper.updateTime(coll);
//			return;
//		} else
//		// 查询当前 数据库储存量,是否已经达到了设定的最大数量;
//		if (selectCollectionMaxNum(coll) == MAX_NUMBER + 10) {
//			// 删除 最早的10条记录,然后将当前的数据添加到数据库;
//			titleMapper.deleteCollection(coll);
//		}
//		titleMapper.insertCollection(coll);
//	}

//	@Override
//	public int selectCollectionMaxNum(Collection1 coll) {
//		return titleMapper.selectCollectionMaxNum(coll);
//	}
//	@Override
//	public String selectAllCollection(Collection1 coll) {
//		List<Collection1> arr = new ArrayList<>();
//		List<Title> data = new ArrayList<>();
//		int code = 100;
//		String msg = "没有记录";
//		// 查询收藏的数据 总数.
//
//		int count = titleMapper.selectCollectionMaxNum(coll);
//		if (count > 0) {
//			arr = titleMapper.selectAllCollection(coll);
//			for (int i = 0; i < arr.size(); i++) {
//				System.out.println(arr.get(i).getG_t_id());
//				Title t = titleMapper.selectById(arr.get(i).getG_t_id());
//				addIPAddress(t);
//				data.add(t);
//			}
//			code = 101;
//			msg = ("" + count);
//		}
//		JS<Title> js = new JS<>(code, msg, data);
//		return JSON.toJSONString(js);
//	}

//	@Override
//	public String addCollection(Collection1 coll) {
//		coll.setC_type("1");
//		addBrowse(coll);
//		JS js = new JS<>(101);
//		return JSON.toJSONString(js);
//	}

	@Override
	public String selectIndex() {
		List<Title> data = titleMapper.selectIndex();
		JS<Title> js = new JS<>(101, data);
		for (int i = 0; i < data.size(); i++) {
			// 为前端提供图片路径.
			addIPAddress(data.get(i));
		}
		return JSON.toJSONString(js);
	}

	@Override
	public String selectTitleById(Title title) {
		List<Title> data = new ArrayList<>();
		Title t = titleMapper.selectById(title.getG_t_id());
		// 为前端提供图片路径.
		addIPAddress(t);
		data.add(t);
		JS<Title> js = new JS<>(101, data);
		return JSON.toJSONString(js);
	}



}
