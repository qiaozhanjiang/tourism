 package com.qfedu.pojo;

import java.util.List;

//  
/**
 * 将查询到的信息封装为 对象.转为字符串 传给前端.的模板
 */
public class JS<T> {
	private Integer code; // 发送请求是否成功的代号
	private String msg; // 发送请求失败原因,成功则为空.需要分页查询的地方,代表分页
	private List<T> data; // 当请求成功后,请求所需要的数据

	
	
	
	
	
	public JS() {
	}

	public JS(Integer code) {
		this(code, null, null);
	}

	public JS(Integer code, List<T> data) {
		this(code, null, data);
	}

	public JS(Integer code, String msg, List<T> data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JS [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
