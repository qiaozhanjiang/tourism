package com.qfedu.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mysql.jdbc.Util;
import com.qfedu.pojo.Users;
import com.qfedu.service.UsersService;
import com.qfedu.service.impl.UsersServiceImpl;
import com.qfedu.utils.Utils;

public class AuthorityFilter implements Filter {
	private static UsersService userService;
	private FilterConfig config;

	// 实现初始化方法
	public void init(FilterConfig config) {
		this.config = config;
	}

	// 实现销毁方法
	public void destroy() {
		this.config = null;
	}

	// 执行过滤的核心方法
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 这里配置跨域 和 字符集
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");

		// 获取该Filter的配置参数
		// ----这里表示,如果没有 登录,只可以 打开(或者发送请求)给下面的界面.
		String encoding = config.getInitParameter("encoding");

		// 设置request编码用的字符集
		request.setCharacterEncoding(encoding); //
		HttpServletRequest requ = (HttpServletRequest) request;

		WebApplicationContext context = WebApplicationContextUtils.findWebApplicationContext(requ.getServletContext());
		userService = context.getBean(UsersService.class);

		// 获取客户请求的页面
		// String requestPath = requ.getServletPath();

		// 这里 把请求头里的 用户token 和 id 组合.
		String uuid = requ.getParameter("uuid");
		String token = requ.getParameter("token");
		Users user = new Users(uuid, token);

		Users us = null;
		if (null != token) {

			us = userService.getUser(user);
			System.out.println(us);
			if (null != us && (System.currentTimeMillis() / 1000 - Integer.valueOf(us.getTokenTime())) < 600) {
				// 不等于null,且上次更新在10分钟会内.确定已经登录,直接 在数据库更新 时间戳,并允许通过.
				userService.updateToken(user);
				chain.doFilter(request, response);
				System.out.println("进入null判断");
				return;
			}
		}

		if (requ.getRequestURL().toString().indexOf(".") > 0) {
			int last = requ.getRequestURL().toString().lastIndexOf(".");
			String str = requ.getRequestURL().toString().substring(last);
			if (".js".equals(str) || ".jpg".equals(str) || ".png".equals(str) || ".do".equals(str) || ".jsp".equals(str)
					|| ".jpeg".equals(str)) {
				chain.doFilter(request, response);
			} else 			//if (
//			// 这里判断token时间超时;
//			null == us || (System.currentTimeMillis() / 1000 - Integer.valueOf(us.getTokenTime())) > 600) {
				resp.getWriter().print(10000);
				return;
			}
//			else {
//				userService.updateToken(user);
//				chain.doFilter(request, response);
//			}
//		}
	}
}
