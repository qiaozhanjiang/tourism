package com.qfedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qfedu.pojo.Address;
import com.qfedu.pojo.Title;
import com.qfedu.pojo.Users;

@Repository
public interface UsersMapper {
	/**
	 * 储存token 到数据库
	 */
	@Update("update users set token = #{token} ,tokentime = unix_timestamp(now()) where uuid = #{uuid} ")
	int writerToken(Users user);
	/**
	 * 跟新token时间戳
	 */
	@Update("update users set tokentime = unix_timestamp(now()) where uuid = #{uuid} ")
	int updateToken(Users user);
	
	
	/**
	 * 查询token是否存在.
	 */
	@Select("select * from users where uuid=#{uuid} and token=#{token}")
	Users selectUserByToken(Users user);
	
	/**
	 * 根据 uuid 查询账号资料!内部方法,不查询账号和密码!
	 */
	@Select("select username,uuid,nickname,gender,id,country,tel,email,birth,photo from users where uuid = #{uuid}")
	Users selectUserById(Users user);

	/**
	 * 根据用户账号密码登录
	 * 
	 * @param us
	 * @return
	 */
	@Select("select * from users where username = #{username} and password = #{password}")
	Users selectUser(Users us);

	/**
	 * 根据用户名 判断用户是否存在.
	 * 
	 * @param us
	 * @return
	 */
	@Select("select * from users where username = #{username}")
	Users selectByUsername(Users us);

	/**
	 * 储存用户
	 */
	@Insert("insert into users (uuid,username,password,nickname,gender,id,country,tel,email,birth,photo) values(#{uuid},#{username},#{password},#{nickname},#{gender},#{id},#{country},#{tel},#{email},#{birth},#{photo})")
	int insertUser(Users us);

	/**
	 * 根据id修改用户资料
	 */
	// 命令在 UsersMapper.xml 里.
	int updateByUser(Users user);

	/**
	 * 根据 手机号 和 账号,找回账号密码.
	 */
	@Select("select * from users where tel = #{tel} and username=#{username}")
	Users getUserByUsernameAndTel(Users user);

	/**
	 * 查询所有用户.暂时无用.
	 */
	@Select("select * from users ")
	List<Users> selectAllPhoto();

}
