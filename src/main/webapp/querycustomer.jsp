<%--
  Created by IntelliJ IDEA.
  User: leyi
  Date: 2019/5/5
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>查询客户信息</h2>
<form action="customerQueryService">
    客户编号:<input name="custId" type="text">
    客户名称:<input name="custName" type="text">
    <input type="hidden" name="opType" value="query">
    <button>查询</button>
</form>
<table>
    <thead>
        <tr>
            <th>客户编号</th>
            <th>存储编号</th>
            <th>姓名</th>
            <th>邮箱</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${CustDATA}">
        <tr>
            <td>${customer.customer_id}</td>
            <td>${customer.store_id}</td>
            <td>${customer.first_name}</td>
            <td>${customer.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<input type="text" value="${PAGE_INDEX}">
<a href="customerQueryService?opType=query&pageIndex=1">首页</a>
<a href="customerQueryService?opType=query&pageIndex=${PAGE_INDEX-1}">上一页</a>
<a href="customerQueryService?opType=query&pageIndex=${PAGE_INDEX+1}">下一页</a>
<a href="customerQueryService?opType=query&pageIndex=${T_PAGE}">末页</a>
</body>
</html>
