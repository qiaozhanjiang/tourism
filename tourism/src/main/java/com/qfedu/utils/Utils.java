package com.qfedu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.qfedu.config.FinalNumber;
import com.qfedu.pojo.Users;

public class Utils implements FinalNumber {
	// 这里 设置 数据库!!
	// private static String driver = "com.mysql.jdbc.Driver";
	// private static String url = "jdbc:mysql:///tourism";
	// private static String username = "root";
	// private static String password = "123";

	// 这里设置下载
	// 需要下载到的路径,格式为 "D:/photo/spider/"
	private static String address = "D:/photo/ctrip/";
	// 传入数据的编码集.
	private static String charset = "UTF-8";

	
	
	
	public static void main(String[] args) {
		
		Date d =new Date();
		System.out.println(d.getTime());
		System.out.println(System.currentTimeMillis());
	}
	
	
	
	
	
	/**
	 * 这里生成token
	 */
	public static String getToken(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	
	

	
	
	
	/**
	 * 下载一张图片,需要图片下载路径,返回图片名字
	 * 
	 * @return
	 */
	public static String downloadPicture(String urls) {
		String photoName = null;
		OutputStream os;
		InputStream is;

		try {
			URL url = new URL(urls);
			int last = urls.lastIndexOf(".");
			// 获取 字符串后缀
			String substr = urls.substring(last);
			// 为图片命名.
			photoName = (UUID.randomUUID().toString().replaceAll("-", "")) + substr;
			String path = address + photoName;
			File outFile = new File(path);
			os = new FileOutputStream(outFile);
			is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return photoName;

	}

	/**
	 * 爬取URL方法 参数 为 需要爬取的网址 返回保存当前界面的所有数据的字符串.
	 * 
	 * @throws IO
	 *             Exception
	 */
	public static String getDataByUrl(String httpWww) {
		URL url = null;
		BufferedReader br = null;
		// 创建 URL,然后打开一个流
		try {
			url = new URL(httpWww);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// 将读取的到的 数据,转码
		try { // 后面的参数是 编码 比如"utf-8"
			br = new BufferedReader(new InputStreamReader(url.openStream(), charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String temp = "";
		StringBuilder sb = new StringBuilder();
		try {
			while ((temp = br.readLine()) != null) {
				sb = new StringBuilder(sb + temp);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 增删改的方法
	 * 
	 * @param sql
	 *            增删改 语句.
	 * @param books
	 *            List<Object> 增删改的时候,需要用?填充,所以需要一个 集合 来保存 代替?的 数据.
	 * @return 失败返回-1,成功返回0+
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	// 增删改的封装，因为增删改主要是sql不同其他的jdbc都一样，一样部分就可以提取成一个方法，不一样的部分当做参数传入方法体执行
	// 第一个参数是需要执行的sql语句
	// 第二个参数sql语句中有变量，sql中变量都是用？代替，所以需要将?对应的真实的数值传入，?的个数和类型都不确定，所以用数组或者集合
	public static int update(String sql, ArrayList<String> books) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		/*
		 * String url = url; String username = username; String password = password;
		 */
		Connection con = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = con.prepareStatement(sql);
		// 防止sql语句中没有变量，那么传入的list就是null，需要进行判断防止空指针
		if (books != null && books.size() > 0) {
			// 循环遍历传入占位符对应的数据的集合，分别调用setObject方法把占位符替换成真实的值
			for (int i = 0; i < books.size(); i++) {
				ps.setObject(i + 1, books.get(i));
			}
		}
		int i = ps.executeUpdate();
		ps.close();
		con.close();
		return i;
	}

	/**
	 * 查询 语句.
	 * 
	 * @param sql
	 *            查询命令
	 * @param paramter
	 *            查询时,因为条件不同,需要用?填充, 如果没有? 则用null代替,如果有,则用 集合 传递
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// 1可以直接返回一个ResultSet，这样封装的代码简单，还需要调用者自己去解析读取数据
	// 2返回的数据类型最外层是一个List集合，一个集合对应着查询出来的结果是多条记录，list的泛型是一个HashMap，一个hashmap对应着一条记录，hashmap中的键值对对应着字段和字段的值
	public static List<HashMap<String, Object>> query(String sql, List<Object> paramter)
			throws ClassNotFoundException, SQLException {

		Class.forName(driver);

		Connection con = DriverManager.getConnection(url, username, password);

		PreparedStatement ps = con.prepareStatement(sql);

		// 防止sql语句中没有变量，那么传入的list就是null，需要进行判断防止空指针
		if (paramter != null && paramter.size() > 0) {
			// 循环遍历传入占位符对应的数据的集合，分别调用setObject方法把占位符替换成真实的值
			for (int i = 0; i < paramter.size(); i++) {

				ps.setObject(i + 1, paramter.get(i));

			}
		}

		ResultSet rs = ps.executeQuery();

		// 通过getMetaData获取到和结果集的数据相关的信息，比如可以获取总共有条数据，还可以获取每一个字段的字段名
		ResultSetMetaData rsmd = rs.getMetaData();
		// 获取结果集列的个数
		int ccount = rsmd.getColumnCount();

		// 因为我们设计的方法可以针对不同的表，那么意味着查询结果的列的个数，列名的名字，还有总共多少条数据都不确定

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		while (rs.next()) {

			// 我们需要想办法知道查询的当前的结果集有多少列

			// 把一条记录的所有数据全部封装到一个map
			HashMap<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < ccount; i++) {

				// 循环获取每一列的列名
				String cName = rsmd.getColumnName(i + 1);

				Object value = rs.getObject(cName);
				map.put(cName, value);
			}

			data.add(map);

		}

		return data;
	}

	/**
	 * 查询语句,返回一个<T> 泛型对象
	 * 
	 * @param sql
	 * @param paramter
	 * @param cs
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */

	// 指定返回的集合的泛型是T，还需要在参数中追加一个新的参数，是sql语句操作的表所对应的实体类的class对象，目的是可以获取到当前实体类的相关信息
	public static <T> List<T> selectT(String sql, ArrayList<Object> paramter, Class<T> cs)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		Class.forName(driver);
		/*
		 * String url = "jdbc:mysql:///803jdbc"; String username = "root"; String
		 * password = "123";
		 */

		Connection con = DriverManager.getConnection(url, username, password);

		PreparedStatement ps = con.prepareStatement(sql);

		// 防止sql语句中没有变量，那么传入的list就是null，需要进行判断防止空指针
		if (paramter != null && paramter.size() > 0) {
			// 循环遍历传入占位符对应的数据的集合，分别调用setObject方法把占位符替换成真实的值
			for (int i = 0; i < paramter.size(); i++) {

				ps.setObject(i + 1, paramter.get(i));

			}
		}

		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = ps.getMetaData();

		List<T> datas = new ArrayList<T>();

		while (rs.next()) {
			// 一层循环完成将一条记录存储到一个对象
			// 这里使用反射和泛型的目的是为了在代码运行过程中动态的获取当前操作的到底是user还是news或者其他

			// 通过反射创建对象
			T t = cs.newInstance();

			int count = rsmd.getColumnCount();
			for (int i = 0; i < count; i++) {
				// 获取列的名字，因为我们在创建实体类的时候按照查询结果集的字段进行命名
				String columnName = rsmd.getColumnName(i + 1);
				Field field = t.getClass().getDeclaredField(columnName);
				field.setAccessible(true);
				// 第一个参数是当前Field所属于的对象
				field.set(t, rs.getObject(columnName));
			}

			datas.add(t);

		}

		return datas;
	}

}
