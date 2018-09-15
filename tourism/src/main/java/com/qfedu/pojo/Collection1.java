package com.qfedu.pojo;

// 
/**
 * 收藏/最近浏览 ,对应 类
 */
public class Collection1 {
	private Integer c_id; // 收藏编号
	private String c_type; // 分辨收藏(1)/最近浏览(0).
	private String c_time; // 收藏/浏览 ,时间
	private String uuid; // 用户的id.
	private Integer g_t_id; // 商品标题id.

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public String getC_type() {
		return c_type;
	}

	public void setC_type(String c_type) {
		this.c_type = c_type;
	}

	public String getC_time() {
		return c_time;
	}

	public void setC_time(String c_time) {
		this.c_time = c_time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getG_t_id() {
		return g_t_id;
	}

	public void setG_t_id(Integer g_t_id) {
		this.g_t_id = g_t_id;
	}

}
