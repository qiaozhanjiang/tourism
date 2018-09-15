package com.qfedu.config;

public interface FinalNumber {
	/**
	 * 当前服务器ip以及端口配置
	 */
	// 服务器 39.104.127.63  //本机ip 10.8.163.31
	String str = "39.104.127.63";

	/**
	 * 为前端 提供服务器图片位置, 不需要改动!!!!
	 */
	//String IP_ADDRESS = "http://" + str + ":8080/tourism/img/";
	String IP_ADDRESS = "http://" + str + ":8080/img/";

	/**
	 * 本机图片储存位置
	 */
	//String PATH_File = "D:/photo/ctrip/";
	String PATH_File = "/img/";

	/**
	 * 收藏配置.表示最大 最近浏览数量最大收藏数量. 数据库 最多会储存当前设置的数量 + 10的数量,当达到极限时,一次删除10条.
	 * (如果等于这个数量,每次都要执行删除,太耗资源)
	 */
	Integer MAX_NUMBER = 20;

	// 数据库配置//jdbc:mysql://39.104.127.63:3306/tourism
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://39.104.127.63:3306/tourism";
	String username = "root";
	String password = "123456";

	/**
	 * 每个用户 注册送的优惠劵 金额:50
	 */
	Double COU_MONEY = 50D;

	/**
	 * 这个是设置 是否查询 goods.为0则不查询(因为 没数据...)则正常查询
	 */

	int YES_NO = 0;

}
