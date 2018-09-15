package com.qfedu.pojo;

//
/**
 * 标题 表 对应 类.
 */
public class Title {
	private Integer g_t_id; // 商品标题id
	private String g_t_as; // 对应地址编号
	private String g_t_address; // 对应地址编号
	private String g_t_content; // 对应标题内容
	private String g_t_photo; // 对应标题图片
	private String g_t_money; // 对应价位
	private String g_t_statr_time;// 出发时间.
	private String g_t_day; // 行程需要时间
	private String g_t_end_time;// 结束时间
	private String g_t_score; // 对应评分
	private String g_t_sellnumber; // 对应已购买人数.

	public String getG_t_as() {
		return g_t_as;
	}

	public void setG_t_as(String g_t_as) {
		this.g_t_as = g_t_as;
	}

	public String getG_t_statr_time() {
		return g_t_statr_time;
	}

	public void setG_t_statr_time(String g_t_statr_time) {
		this.g_t_statr_time = g_t_statr_time;
	}

	public String getG_t_day() {
		return g_t_day;
	}

	public void setG_t_day(String g_t_day) {
		this.g_t_day = g_t_day;
	}

	public String getG_t_end_time() {
		return g_t_end_time;
	}

	public void setG_t_end_time(String g_t_end_time) {
		this.g_t_end_time = g_t_end_time;
	}

	public Integer getG_t_id() {
		return g_t_id;
	}

	public void setG_t_id(Integer g_t_id) {
		this.g_t_id = g_t_id;
	}

	public String getG_t_address() {
		return g_t_address;
	}

	public void setG_t_address(String g_t_address) {
		this.g_t_address = g_t_address;
	}

	public String getG_t_content() {
		return g_t_content;
	}

	public void setG_t_content(String g_t_content) {
		this.g_t_content = g_t_content;
	}

	public String getG_t_photo() {
		return g_t_photo;
	}

	public void setG_t_photo(String g_t_photo) {
		this.g_t_photo = g_t_photo;
	}

	public String getG_t_money() {
		return g_t_money;
	}

	public void setG_t_money(String g_t_money) {
		this.g_t_money = g_t_money;
	}

	public String getG_t_score() {
		return g_t_score;
	}

	public void setG_t_score(String g_t_score) {
		this.g_t_score = g_t_score;
	}

	public String getG_t_sellnumber() {
		return g_t_sellnumber;
	}

	public void setG_t_sellnumber(String g_t_sellnumber) {
		this.g_t_sellnumber = g_t_sellnumber;
	}

	@Override
	public String toString() {
		return "Title [g_t_id=" + g_t_id + ", g_t_address=" + g_t_address + ", g_t_content=" + g_t_content
				+ ", g_t_photo=" + g_t_photo + ", g_t_money=" + g_t_money + ", g_t_statr_time=" + g_t_statr_time
				+ ", g_t_day=" + g_t_day + ", g_t_end_time=" + g_t_end_time + ", g_t_score=" + g_t_score
				+ ", g_t_sellnumber=" + g_t_sellnumber + "]";
	}

}
