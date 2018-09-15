package com.qfedu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qfedu.pojo.JS;
import com.qfedu.pojo.Users;

@Service
public interface UsersService {

	/**
	 * 将图片储存,并返回图片的名字.
	 */

	String updatePhoto(MultipartFile files);

	/**
	 * 查询username是否存在, 存在返回1,不存在返回0
	 */
	int selectByUsername(Users us);

	/**
	 * 查询一个用户数据是否存在.不存在返回null 存在则返回这个用户的全部数据.
	 */
	Users selectUser(Users us);

	/**
	 * 查询(没有password 时),返回一个JS对象,code为 10表示当前账号存在;11表示不存在(可用); 创建(至少有账号和密码时)code为
	 * 100 表示创建失败; 101表示成功,并会在data里储存当前用户数据(不含账号和密码)
	 */

	JS<Users> save(Users user, MultipartFile files);

	/**
	 * 储存新建的账号,内部使用
	 */
	Users saveUser1(Users user);

	/**
	 * 修改资料,返回js字符串;
	 */
	String update(Users user, MultipartFile files);

	/**
	 * 找回账号密码.返回js字符串
	 */
	String getUserByUsernameAndTel(Users user);
	
	/**
	 * 修改token时间戳
	 */
	
	int updateToken(Users user);
	
	/**
	 * 查询users 的属性,内部使用.
	 */
	Users getUser(Users user);
}
