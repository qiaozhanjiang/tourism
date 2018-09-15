<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br />
<br />这里只输入用户名,会判断账号是否存在,不存在则返回11,存在返回10
<br />1.注册:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/register.do?username=admin123&password=123</a>

<br /><br />2.登录:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/login.do?username=admin123&password=123</a>

<br /><br />3.修改资料:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/update.do?username=admin123&password=123&newPassword=456</a>

<br /><br />4.找回账号:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/getUserByUsernameAndTel.do?username=admin123&tel=13234566543</a>

<br /><br />5.退出:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/loginOut.do</a>

<br /><br />6.添加银行卡:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism//addBank.do?uuid=1234&bank_number=321456987</a>

<br /><br />7.查询所有银行卡:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectAllBank.do?uuid=1234</a>

<br /><br />8.删除一张银行卡:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/deleteOneBank.do?bank_id=5&bank_number=987</a>

<br /><br />9.主页查询接口:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectIndex.do</a>

<br /><br />10.根据旅游地点 id查询:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectByAddress.do?g_t_address=102&page=1&number=10</a>

<br /><br />11.根据标题id查询 标题信息:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectTitleById.do?g_t_id=10005</a>

<br /><br />12.根据g_t_id 查询 单一的标题:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectTitleById.do?g_t_id</a>

<br /><br />13.根据旅游标题id查询详细信息:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectGoodsByTitleId.do?g_t_id=10003</a>

<br /><br />14.收藏 商品 :<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/addCollection.do?uuid=1234&g_t_id=10005&c_type=1</a>

<br /><br />15.查询 收藏/最近浏览:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectAllCollection.do?uuid=1234&c_type=1</a> 最近浏览,下个版本再添加...c_type=0

<br /><br />16.将用户订单加入到数据库:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/addOrder.do?uuid=1234&g_t_id=1006</a>

<br /><br />17.查询  已付款/历史 订单 :<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectOrder.do?uuid=1234&o_state=0</a> 这里0表示历史购买记录,o_state=1 查询已付费的

<br /><br />18.查询 已付款/历史 订单 对应的景点:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectTitle.do?uuid=1234&o_state=0</a>

<br /><br />19.查询 拥有的优惠券:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectCoupons.do?uuid=1234</a>

<br /><br />20.付款信息提交全参数:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/payOrder.do?uuid=1234&g_t_id=10006,10102&o_number=1,1&message=258369</a>
<br />参数解释:uuid=1234只填1次即可,g_t_id有几个写几个; ","隔开.需要和后面的o_number的顺序对应;这3个参数必须有!!&cou_id=1是优惠劵,一次只能一张,可以没有!(测试时别带这个数据).. message是付款之后传递的信息,没有这个属性的话,是计算需要付款,有这个属性就表示付过款了,系统会判断付款是否成功.
失败返回100,成功返回<h1 >1001</h1>
<br /><br /><br />待补充:<a href="http://10.8.163.31:8080/tourism/demo.jsp">http://10.8.163.31:8080/tourism/selectIndex.do</a>

</body>
</html>