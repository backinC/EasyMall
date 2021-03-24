<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>添加商品</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
		<link href="${ pageContext.request.contextPath }/css/orderList.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div class="top">
		<h1>&nbsp;&nbsp;EasyMall商城管理后台</h1>
	</div>	
	<div class="content">
		<div class="left">			
			<%@ include file = "_left.jsp" %>
		</div>
	<div class="right">
	<div style="margin:auto">
	<table  cellspacing="10">
	<tr>
		<th>类别名称</th>
		<th>类别描述</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${ categorys }" var="category">
		<tr>
			<td>${category.name}</td>
			<td>${category.description }</td>
			<td><a href="${pageContext.request.contextPath}/admin/editcategory?id=${category.id}">编辑</a>
			&nbsp;|&nbsp;
			<a href="${pageContext.request.contextPath}/admin/deletecategory?id=${category.id}">删除</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}/admin/newcategory">新增商品类别</a>
	</div>
	</div><!-- right结束 -->
	</div><!-- content结束 -->		

	</body>
</html>