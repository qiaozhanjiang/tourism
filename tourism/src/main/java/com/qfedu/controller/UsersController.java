package com.qfedu.controller;

//XXX  项目完成记得到 AuthorityFilter 文件里 修改过滤器!
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.qfedu.pojo.Address;
import com.qfedu.pojo.Bank;
import com.qfedu.pojo.Collection1;
import com.qfedu.pojo.Coupons1;
import com.qfedu.pojo.Demo;
import com.qfedu.pojo.JS;
import com.qfedu.pojo.Order;
import com.qfedu.pojo.Title;
import com.qfedu.pojo.Users;
import com.qfedu.service.BankService;
import com.qfedu.service.CollectionService;
import com.qfedu.service.CouponsService;
import com.qfedu.service.OrderService;
import com.qfedu.service.TitleService;
import com.qfedu.service.UsersService;
import com.qfedu.utils.Utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("")
public class UsersController {

	@Resource // @Autowired 俩基本一样
	private BankService bankService;
	@Resource
	private CollectionService collectionService;
	@Resource
	private CouponsService couponsService;
	@Resource
	private OrderService orderService;
	@Resource
	private UsersService usersService;
	@Resource
	private TitleService titleService;

	// 用户注册模块
	/**
	 * 如果不写密码 会 检查数据库 当前账号是否存在; 存在返回10;不存在返回11(表示可用);
	 * 如果账号密码都存在,会检查账号是否存在.存在则返回100;不存在则储存,并返回101(表示成功);
	 */
	@RequestMapping("/register.do")
	public void register(Users user, HttpServletRequest req, HttpServletResponse resp, MultipartFile files) {
		// 已在 过滤器中配置.
		// resp.setHeader("Access-Control-Allow-Origin","*");
		// resp.setCharacterEncoding("UTF-8");
		// resp.setContentType("text/html;charset=utf-8");

		JS<Users> js = usersService.save(user, files);
		String ret = JSON.toJSONString(js);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 登录
	@RequestMapping("/login.do")
	public void userLogin(Users user, HttpServletResponse resp, HttpServletRequest req, HttpSession session) {

		// 如果用户 登录成功,需要将用户储存于作用域,所以不能直接返回字符串
		List<Users> list = new ArrayList<Users>();
		JS<Users> js = new JS<>();
		Users us = usersService.selectUser(user);
		int code = 100;
		if (us != null) {
			
			
			
			code = 101;
			list.add(us);
			js.setData(list);
			req.getSession().setAttribute("us", us);
		}
		js.setCode(code);
		String ret = JSON.toJSONString(js);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 修改资料
	@RequestMapping("/update.do")
	public void update(Users user, MultipartFile files, HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.getWriter().print(usersService.update(user, files));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 找回账号 ,根据 账号 和电话
	@RequestMapping("/getUserByUsernameAndTel.do")
	public void getUserByUsernameAndTel(Users user, HttpServletResponse resp) {
		try {
			resp.getWriter().print(usersService.getUserByUsernameAndTel(user));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 退出
	@RequestMapping("/loginOut.do")
	public void userLoginOut(HttpSession session, HttpServletResponse resp) {
		session.invalidate();
		try {
			resp.getWriter().print(101);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 添加银行卡 到 数据库.
	@RequestMapping("/addBank.to")
	public void addBank(Bank bank, HttpServletResponse resp) {
		try {
			resp.getWriter().print(bankService.addBank(bank));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询银行卡 数据库.
	@RequestMapping("/selectAllBank.to")
	public void selectBank(Users user, HttpServletResponse resp) {
		try {
			resp.getWriter().print(bankService.selectBankByUuid(user));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 删除一张银行卡
	@RequestMapping("/deleteOneBank.to")
	public void deleteOneBank(Bank bank, HttpServletResponse resp) {
		String ret = bankService.deleteOneBank(bank);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 根据旅游地点 id查询 标题.
	@RequestMapping("/selectByAddress.do")
	public void selectByAddress(Address ads, HttpServletResponse resp) {

		String ret = titleService.selectByAddress(ads);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 根据旅游标题id查询旅游地点的详细信息.
	@RequestMapping("/selectGoodsByTitleId.do")
	public void selectGoodsByTitleId(Title title, String uuid, HttpServletResponse resp) {
		// 这里需要把查询的信息储存到最近浏览里面!!
		String ret = titleService.selectGoodsByTitleId(title, uuid);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 收藏 商品 ;
	@RequestMapping("/addCollection.to")
	public void addCollection(Collection1 coll, HttpServletResponse resp) {
		String ret = collectionService.addCollection(coll);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询 收藏/最近浏览.
	@RequestMapping("/selectAllCollection.to")
	public void selectAllCollection(Collection1 cool, HttpServletResponse resp) {
		String ret = collectionService.selectAllCollection(cool);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 删除一个用户收藏
	@RequestMapping("/deleteOneCollection.to")
	public void deleteOneCollection(Collection1 coll, HttpServletResponse resp) {
		String ret = collectionService.deleteOneCollection(coll);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 将用户订单加入到数据库
	@RequestMapping("/addOrder.to")
	public void addOrder(Order order, HttpServletResponse resp) {
		String ret = orderService.addOrder(order);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询 已付款/历史 订单 .
	@RequestMapping("/selectOrder.to")
	public void selectOrder(Order order, HttpServletResponse resp) {
		String ret = orderService.selectOrder(order);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询 已付款/历史 订单 对应的景点
	@RequestMapping("/selectTitle.to")
	public void selectTitle(Order order, HttpServletResponse resp) {
		String ret = orderService.selectTitle(order);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 为主页提供数据.
	@RequestMapping("/selectIndex.do")
	public void selectIndex(HttpServletResponse resp) {
		String ret = titleService.selectIndex();
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 根据g_t_id 查询 单一的title
	@RequestMapping("/selectTitleById.do")
	public void selectTitleById(HttpServletResponse resp, Title title) {
		String ret = titleService.selectTitleById(title);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询 拥有的优惠券
	@RequestMapping("/selectCoupons.to")
	public void selectCoupons(Users user, HttpServletResponse resp) {
		String ret = couponsService.selectCoupons(user);
		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 结账 查询系统
	// 这里可以选择使用优惠劵.
	@RequestMapping("/payOrder.to")
	public void payOrder(HttpServletResponse resp, Demo demo, Coupons1 coupons) {
		// demo.getUuid 储存前端提交的商品的uuid. (用户id)
		// demo.getG_t_id 储存前端提交的所有商品的g_t_id ","隔开.(购买的商品id)
		// demo.getO_number 储存前端提交的所有商品的o_number ","隔开.(购买数量)
		
//--------------------------------------------------------
		// 为了方便理解,这里的代码 不放在Service层.
		String str1 = demo.getUuid();
		String str2 = demo.getG_t_id();
		String str3 = demo.getO_number();

		String[] st2 = str2.split(",");
		String[] st3 = str3.split(",");
		List<Order> data = new ArrayList<>();

		for (int i = 0; i < st2.length; i++) {
			Order o = new Order();
			o.setUuid(str1);
			o.setG_t_id(Integer.valueOf(st2[i]));
			o.setO_number(Integer.valueOf(st3[i]));
			data.add(o);
		}
//-------------------------------------------------------------
		String ret = null;

		ret = orderService.payOrder(data, demo, coupons);

		try {
			resp.getWriter().print(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
