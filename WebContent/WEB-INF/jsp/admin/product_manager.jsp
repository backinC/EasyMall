<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>添加商品</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
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
	<table>
	<tr>
	<th>商品编号</th>
	<th>商品图片</th>
	<th>商品名称</th>
	<th>商品价格</th>
	<th>商品类别</th>
	<th>商品描述</th>
	<th>操作</th>
	</tr>
	<c:forEach items="${ products }" var="product">
		<tr>
		<td>${ product.id }</td>
		<td><img src="${pageContext.request.contextPath}${product.imgurl}" border="0" style="height:100px;width:100px"></img></td>
		<td>${ product.name }</td>
		<td>${ product.price }</td>
		<td>${ product.category }</td>
		<td>${ product.description }</td>
		<td><a href="${ pageContext.request.contextPath }/admin/editprod?id=${product.id}">修改</a>
		<a href="${ pageContext.request.contextPath }/admin/deleteprod?id=${product.id}">删除</a>
		</td>
		</tr>
	</c:forEach>
	</table>
	</div><!-- right结束 -->
	</div><!-- content结束 -->		
	
	
	</body>
</html>