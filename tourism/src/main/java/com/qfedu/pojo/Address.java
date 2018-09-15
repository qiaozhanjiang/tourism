package com.qfedu.pojo;
// 
/**
 * 为对应地址查询  特意创建的 类
 */
public class Address {
	private String g_t_address; // 要查询的 地址的 编号.
	private int page; // 要显示第几页?
	private int number; // 每页显示多少的数量?

	public String getG_t_address() {
		return g_t_address;
	}

	public void setG_t_address(String g_t_address) {
		this.g_t_address = g_t_address;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Address [g_t_address=" + g_t_address + ", number=" + number + ", page=" + page + "]";
	}
}
