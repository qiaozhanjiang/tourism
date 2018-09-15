package com.qfedu.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.qfedu.config.FinalNumber;
import com.qfedu.dao.BankMapper;
import com.qfedu.dao.CouponsMapper;
import com.qfedu.dao.UsersMapper;
import com.qfedu.pojo.Coupons1;
import com.qfedu.pojo.JS;
import com.qfedu.pojo.Title;
import com.qfedu.pojo.Users;
import com.qfedu.service.CouponsService;
import com.qfedu.service.UsersService;
import com.qfedu.utils.Utils;

@Service
public class UsersServiceImpl implements UsersService, FinalNumber {
	@Resource
	private UsersMapper usersMapper;
	@Resource
	private CouponsMapper couponsMapper;
	@Resource
	private BankMapper bankMapper;

	/**
	 * 将头像图片的路径 修改为 ip地址,供前端直接使用.
	 */
	public Users addIPUsers(Users u) {
		String st = IP_ADDRESS + u.getPhoto();
		u.setPhoto(st);
		return u;
	}

	public String updatePhoto(MultipartFile files) {
		String path = null;
		String fileName = files.getOriginalFilename();
		if (fileName != null && fileName.length() > 4) {
			int last = fileName.lastIndexOf(".");
			String str = fileName.substring(last);
			// 这里获取文件名,
			path = UUID.randomUUID().toString().replaceAll("-", "") + str;
			String pathFile = PATH_File + path;
			try {
				// 将图片储存到 硬盘 "D:/photo/ctripHeadPhoto/" 位置
				files.transferTo(new File(pathFile));

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	@Override
	public int selectByUsername(Users us) {
		int i = usersMapper.selectByUsername(us) == null ? 0 : 1;
		return i;
	}

	@Override
	public Users selectUser(Users us) {
		Users user = usersMapper.selectUser(us);
		if (user != null) {
			// 生成token
			String str = UUID.randomUUID().toString();
			str = str.replaceAll("-", "");
			// 将token 储存到数据库
			user.setToken(str);
			usersMapper.writerToken(user);
			System.out.println(user);
			// 将用户 密码 隐藏,并返回
			user.setPassword(null);
			user.setTokenTime(null);
			return user;
		}
		return null;
	}

	// 尝试将创建账号功能 放在Service层;
	@Override
	public JS<Users> save(Users user, MultipartFile files) {
		JS<Users> js = new JS<>();
		List<Users> data = new ArrayList<>();
		int code = 10;

		// 判断是否有密码,没有密码属性 ,就是 测试账号是否存在
		if (null == user.getPassword() || user.getPassword().length() < 1) {
			// 当用户名的长度不小于5.且 数据库中这个用户名不存在, 则返回101表示账号可用.否则返回100
			if (user.getUsername().length() >= 5 && selectByUsername(user) == 0) {
				code = 11;
			}
			js.setCode(code);
			return js;
			// 下面是执行 申请账号的步骤,要求账号长度大于4,并且密码长度大于2.
		} else if (user.getUsername().length() > 4 && user.getPassword().length() > 2) {
			code = 100;
			// 将图片储存并返回 图片 名字,如果图有问题,则返回null
			if (null != files) {
				String path = updatePhoto(files);
				user.setPhoto(path);
			}
			// 如果前端生日属性为空,则将对象属性改为空,否则数据库会 出错.
			if (user.getBirth() == null || user.getBirth().length() < 1) {
				user.setBirth(null);
			}
			// 如果 nick 为空,则为其随机取名.
			if (null == user.getNickname() || user.getNickname().length() < 1) {
				int name = (int) (Math.random() * 9000) + 1000;
				user.setNickname("驴友" + name);
			}

			if (saveUser1(user) != null) {
				addIPUsers(user);
				data.add(user);
				js.setData(data);
				code = 101;
			}
		}
		js.setCode(code);
		return js;
	}

	// 生成用户id 并将密码清空 ,返回当前账号全部信息.
	@Override
	public Users saveUser1(Users user) {
		if (usersMapper.selectByUsername(user) == null) {
			String str = UUID.randomUUID().toString();// 生成UUID,并转换为字符串
			str = str.replaceAll("-", "");// 将转换成的字符串中的"-"去除.
			user.setUuid(str.substring(0, 16));
			if (usersMapper.insertUser(user) > 0) {
				// 将用户密码清空,并返回
				user.setPassword(null);

				// 增送一张 价值 COU_MONEY 元的 优惠劵
				Coupons1 cou = new Coupons1();
				cou.setUuid(user.getUuid());
				cou.setCou_money(COU_MONEY);
				couponsMapper.inserCoupons(cou);
				return user;

			}

		}
		return null;
	}

	@Override
	public String update(Users user, MultipartFile files) {
		List<Users> data = new ArrayList<>();
		int code = 100;
		String path = null;
		// 将图片储存并返回 图片 名字
		if (files != null) {
			path = updatePhoto(files);
		}
		user.setPhoto(path);
		Users us = null;
		if (usersMapper.updateByUser(user) > 0) {
			us = usersMapper.selectUserById(user);
			data.add(us);
			code = 101;
		}
		JS<Users> js = new JS<>(code, data);
		return JSON.toJSONString(js);
	}

	@Override
	public String getUserByUsernameAndTel(Users user) {
		JS<Users> js = new JS<>();
		int code = 100;
		// 先判断数据长度
		if (user.getTel().length() > 4 || user.getUsername().length() > 2) {
			Users u = usersMapper.getUserByUsernameAndTel(user);
			if (u != null) {
				List<Users> data = new ArrayList<>();
				data.add(u);
				js.setData(data);
				code = 101;
			}
		}
		js.setCode(code);
		return JSON.toJSONString(js);
	}

	@Override
	public Users getUser(Users user) {
		return usersMapper.selectUserByToken(user);
	}

	@Override
	public int updateToken(Users user) {
		return usersMapper.writerToken(user);
	}

}
